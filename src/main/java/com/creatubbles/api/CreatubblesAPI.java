package com.creatubbles.api;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;

import com.google.gson.*;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.client.JerseyClient;
import org.glassfish.jersey.client.JerseyClientBuilder;

import com.creatubbles.api.core.Gallery;
import com.creatubbles.api.request.amazon.UploadS3ImageRequest;
import com.creatubbles.api.request.creation.CreateCreationRequest;
import com.creatubbles.api.request.creation.CreationsUploadsRequest;
import com.creatubbles.api.request.creation.PingCreationsUploadsRequest;
import com.creatubbles.api.response.auth.SignUpResponse;
import com.creatubbles.api.response.creation.CreateCreationResponse;
import com.creatubbles.api.response.creation.CreationsUploadsResponse;
import com.creatubbles.api.response.creation.GetCreationsResponse;
import com.creatubbles.api.response.creator.CreateCreatorResponse;
import com.creatubbles.api.response.creator.GetCreatorsResponse;
import com.creatubbles.api.response.gallery.CreateUserGalleryResponse;
import com.creatubbles.api.response.user.UserProfileResponse;
import com.creatubbles.api.util.EndPoints;


@SuppressWarnings("deprecation")
public class CreatubblesAPI {
    public final static Gson GSON = new GsonBuilder()
            .registerTypeAdapter(SignUpResponse.class, new SignUpResponse())
            .registerTypeAdapter(UserProfileResponse.class, new UserProfileResponse())
            .registerTypeAdapter(CreateCreatorResponse.class, new CreateCreatorResponse())
            .registerTypeAdapter(CreateUserGalleryResponse.class, new CreateUserGalleryResponse())
            .registerTypeAdapter(CreateUserGalleryResponse.class, new CreateUserGalleryResponse())
            .registerTypeAdapter(Gallery.class, new Gallery())
            .registerTypeAdapter(GetCreatorsResponse.class, new GetCreatorsResponse())
            .registerTypeAdapter(GetCreationsResponse.class, new GetCreationsResponse())
            .registerTypeAdapter(CreateCreationResponse.class, new CreateCreationResponse())
            .registerTypeAdapter(CreationsUploadsResponse.class, new CreationsUploadsResponse())
            .registerTypeAdapter(String.class, new StringAdapter())
            .create();

    public final static JerseyClient CLIENT = JerseyClientBuilder
            .createClient()
            .property(ClientProperties.CONNECT_TIMEOUT, 5000)
            .property(ClientProperties.READ_TIMEOUT, 5000)
            .property(ClientProperties.SUPPRESS_HTTP_COMPLIANCE_VALIDATION, Boolean.TRUE);

    public static String buildURL(final String endPoint) {
        String base = staging ? EndPoints.URL_BASE_STAGING : EndPoints.URL_BASE;
        return base.concat(endPoint);
    }

    private static boolean staging = false;

    public static void setStagingMode(boolean staging) {
        CreatubblesAPI.staging = staging;
    }

    public static void main(String[] args) throws IOException {
        // Additional examples can be found in the JUnit test files
        
        CreatubblesAPI.setStagingMode(true);
        String accessToken = ""; // TODO commit tests AuthTests.getAuthToken();

        CreateCreationRequest createCreation = new CreateCreationRequest(accessToken);
        CreateCreationResponse createCreationResponse = createCreation.execute().getResponse();
        System.out.println(createCreationResponse.creation.id);

        CreationsUploadsRequest creationsUploads = new CreationsUploadsRequest(createCreationResponse.creation.id, accessToken);
        CreationsUploadsResponse creationsUploadsResponse = creationsUploads.execute().getResponse();
        System.out.println(creationsUploadsResponse.url);
        System.out.println(creationsUploadsResponse.id);

        File file = new File("C:/dev/1.png");
        byte[] data = Files.readAllBytes(file.toPath());
        UploadS3ImageRequest uploadS3Image = new UploadS3ImageRequest(data, creationsUploadsResponse.url);
        uploadS3Image.execute().getResponse();

        PingCreationsUploadsRequest pingCreationsUploads = new PingCreationsUploadsRequest(creationsUploadsResponse.id, accessToken);
        pingCreationsUploads.execute().getResponse();
        System.out.println("-Finish-");
    }

    static class StringAdapter implements JsonDeserializer<String> {

        public String deserialize(JsonElement json, Type typeOfT,
                                  JsonDeserializationContext context)
                throws JsonParseException {
            String asString = json.getAsJsonPrimitive().getAsString();
            return asString != null && asString.isEmpty() ? null : asString;
        }
    }

}
