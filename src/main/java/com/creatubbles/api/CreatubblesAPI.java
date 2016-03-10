package com.creatubbles.api;

import com.creatubbles.api.core.Gallery;
import com.creatubbles.api.core.LandingUrl;
import com.creatubbles.api.request.amazon.UploadS3FileRequest;
import com.creatubbles.api.request.creation.CreateCreationRequest;
import com.creatubbles.api.request.creation.CreationsUploadsRequest;
import com.creatubbles.api.request.creation.PingCreationsUploadsRequest;
import com.creatubbles.api.request.landingurls.GetLandingUrlsRequest;
import com.creatubbles.api.request.landingurls.GetSpecificLandingUrlRequest;
import com.creatubbles.api.response.auth.SignUpResponse;
import com.creatubbles.api.response.creation.CreateCreationResponse;
import com.creatubbles.api.response.creation.CreationsUploadsResponse;
import com.creatubbles.api.response.creation.GetCreationsResponse;
import com.creatubbles.api.response.creator.CreateCreatorResponse;
import com.creatubbles.api.response.creator.GetCreatorsResponse;
import com.creatubbles.api.response.gallery.CreateUserGalleryResponse;
import com.creatubbles.api.response.landingurls.GetLandingUrlsResponse;
import com.creatubbles.api.response.landingurls.GetSpecificLandingUrlResponse;
import com.creatubbles.api.response.user.UserProfileResponse;
import com.creatubbles.api.util.EndPoints;
import com.creatubbles.api.util.HttpUtil;
import com.google.gson.*;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.client.JerseyClient;
import org.glassfish.jersey.client.JerseyClientBuilder;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;


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
            .registerTypeAdapter(GetLandingUrlsResponse.class, new GetLandingUrlsResponse())
            .registerTypeAdapter(GetSpecificLandingUrlResponse.class, new GetSpecificLandingUrlResponse())
            .registerTypeAdapter(String.class, new StringAdapter())
            .create();

    public final static JerseyClient CLIENT = JerseyClientBuilder
            .createClient()
            .property(ClientProperties.CONNECT_TIMEOUT, 5000)
            .property(ClientProperties.READ_TIMEOUT, 5000)
            .property(ClientProperties.SUPPRESS_HTTP_COMPLIANCE_VALIDATION, Boolean.TRUE);

    public static String buildURL(final String endPoint) {
        if (endPoint.startsWith("https://")) {
            return endPoint;
        }
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

        File file = new File("C:/dev/1.png");
        String extension = HttpUtil.getExtension(file.getPath());

        CreationsUploadsRequest creationsUploads = new CreationsUploadsRequest(createCreationResponse.creation.id, extension, accessToken);
        CreationsUploadsResponse creationsUploadsResponse = creationsUploads.execute().getResponse();
        System.out.println(creationsUploadsResponse.url);
        System.out.println(creationsUploadsResponse.id);

        GetLandingUrlsRequest getLandingUrls = new GetLandingUrlsRequest(accessToken);
        for (LandingUrl landingUrl : getLandingUrls.execute().getResponse().urls) {
            System.out.println(landingUrl.type + ":" + landingUrl.url);
        }

        GetSpecificLandingUrlRequest getSpecificLandingUrl = new GetSpecificLandingUrlRequest(accessToken, LandingUrl.LandingUrlType.CTB_USER_PROFILE);
        GetSpecificLandingUrlResponse getSpecificLandingUrlResponse = getSpecificLandingUrl.execute().getResponse();
        LandingUrl url = getSpecificLandingUrlResponse.url;
        System.out.println("specific url - " + url.type + ":" + url.url);

        byte[] data = Files.readAllBytes(file.toPath());

        UploadS3FileRequest uploadS3Image = new UploadS3FileRequest(data, creationsUploadsResponse.url, creationsUploadsResponse.content_type);
        uploadS3Image.execute().getResponse();

        PingCreationsUploadsRequest pingCreationsUploads = new PingCreationsUploadsRequest(creationsUploadsResponse.ping_url, accessToken);
        pingCreationsUploads.setData("");
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
