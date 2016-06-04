package com.creatubbles.api;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.util.Map.Entry;

import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.client.JerseyClient;
import org.glassfish.jersey.client.JerseyClientBuilder;

import com.creatubbles.api.core.LandingUrl;
import com.creatubbles.api.request.amazon.UploadS3FileRequest;
import com.creatubbles.api.request.creation.CreateCreationRequest;
import com.creatubbles.api.request.creation.CreationsUploadsRequest;
import com.creatubbles.api.request.creation.PingCreationsUploadsRequest;
import com.creatubbles.api.request.landingurls.GetLandingUrlsRequest;
import com.creatubbles.api.request.landingurls.GetSpecificLandingUrlRequest;
import com.creatubbles.api.response.creation.CreateCreationResponse;
import com.creatubbles.api.response.creation.CreationsUploadsResponse;
import com.creatubbles.api.response.landingurls.GetLandingUrlsResponse;
import com.creatubbles.api.response.landingurls.GetSpecificLandingUrlResponse;
import com.creatubbles.api.util.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;


public class CreatubblesAPI {


    public static final String URL_BASE = "https://api.creatubbles.com/v2/";
    public static final String URL_BASE_STAGING = "https://api.staging.creatubbles.com/v2/";
    
    public final static Gson GSON = new GsonBuilder()
            .registerTypeAdapter(String.class, new StringAdapter())
            .registerTypeAdapterFactory(new JsonHackx())
            .create();

    public final static JerseyClient CLIENT = JerseyClientBuilder
            .createClient()
            .property(ClientProperties.CONNECT_TIMEOUT, 5000)
            .property(ClientProperties.READ_TIMEOUT, 5000)
            .property(ClientProperties.SUPPRESS_HTTP_COMPLIANCE_VALIDATION, Boolean.TRUE);

    public static String buildURL(final Object end) {
        String endPoint = end.toString();
        if (endPoint.startsWith("https://")) {
            return endPoint;
        }
        String base = staging ? URL_BASE_STAGING : URL_BASE;
        return base.concat(endPoint);
    }

    private static boolean staging = false;

    public static void setStagingMode(boolean staging) {
        CreatubblesAPI.staging = staging;
    }

    public static boolean stagingModeEnabled() {
        return staging;
    }

    public static void main(String[] args) throws IOException {
        // Additional examples can be found in the JUnit test files

        CreatubblesAPI.setStagingMode(true);
        String accessToken = ""; // TODO commit tests AuthTests.getAuthToken();

        CreateCreationRequest createCreation = new CreateCreationRequest(accessToken);
        CreateCreationResponse createCreationResponse = createCreation.execute().getResponse();
        System.out.println(createCreationResponse.getCreation().getId());

        File file = new File("C:/dev/1.png");
        String extension = HttpUtil.getExtension(file.getPath());

        CreationsUploadsRequest creationsUploads = new CreationsUploadsRequest(createCreationResponse.getCreation().getId(), extension, accessToken);
        CreationsUploadsResponse creationsUploadsResponse = creationsUploads.execute().getResponse();
        System.out.println(creationsUploadsResponse.getUrl());
        System.out.println(creationsUploadsResponse.getId());

        GetLandingUrlsRequest getLandingUrls = new GetLandingUrlsRequest(accessToken);
        for (GetLandingUrlsResponse landingUrl : getLandingUrls.execute().getResponseList()) {
            System.out.println(landingUrl.getUrl().getType() + ":" + landingUrl.getUrl().getUrl());
        }

        GetSpecificLandingUrlRequest getSpecificLandingUrl = new GetSpecificLandingUrlRequest(accessToken, LandingUrl.LandingUrlType.CTB_USER_PROFILE);
        GetSpecificLandingUrlResponse getSpecificLandingUrlResponse = getSpecificLandingUrl.execute().getResponse();
        LandingUrl url = getSpecificLandingUrlResponse.getUrl();
        System.out.println("specific url - " + url.getType() + ":" + url.getUrl());

        byte[] data = Files.readAllBytes(file.toPath());

        UploadS3FileRequest uploadS3Image = new UploadS3FileRequest(data, creationsUploadsResponse.getUrl(), creationsUploadsResponse.getType());
        uploadS3Image.execute().getResponse();

        PingCreationsUploadsRequest pingCreationsUploads = new PingCreationsUploadsRequest(creationsUploadsResponse.getPingUrl(), accessToken);
        pingCreationsUploads.setData("");
        pingCreationsUploads.execute().getResponse();
        System.out.println("-Finish-");
    }

    private static class StringAdapter implements JsonDeserializer<String> {

        public String deserialize(JsonElement json, Type typeOfT,
                                  JsonDeserializationContext context)
                throws JsonParseException {
            String asString = json.getAsJsonPrimitive().getAsString();
            return asString != null && asString.isEmpty() ? null : asString;
        }
    }
    
    private static class JsonHackx implements TypeAdapterFactory {

        @Override
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {

            final TypeAdapter<T> adapter = gson.getDelegateAdapter(this, type);
            final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);

            TypeAdapter<T> result = new TypeAdapter<T>() {

                @Override
                public void write(JsonWriter out, T value) throws IOException {
                    JsonObject object = adapter.toJsonTree(value).getAsJsonObject();
                    elementAdapter.write(out, object);
                }

                @Override public T read(JsonReader in) throws IOException {
                    JsonElement e = elementAdapter.read(in);
                    
                    // Enter into all "data" fields immediately
                    if (e.isJsonObject() && e.getAsJsonObject().has("data")) {
                        e = e.getAsJsonObject().get("data");
                    }
                    
                    // Add all "attributes" fields to the parent object so they are accessible without indirection
                    // Yeah this is a massive hack but it is necessary for things like landing URLs which only
                    // serve a single string inside the attributes
                    if (e.isJsonObject()) {
                        JsonObject obj = e.getAsJsonObject();
                        if (obj.has("attributes")) {
                            JsonObject attr = obj.get("attributes").getAsJsonObject();
                            for (Entry<String, JsonElement> entry : attr.entrySet()) {
                                obj.add(entry.getKey(), entry.getValue());
                            }
                        }
                    }
                    
                    return adapter.fromJsonTree(e);
                }
            }.nullSafe(); // so we don't have to check for null on the stream

            return result;
        }
    }
}
