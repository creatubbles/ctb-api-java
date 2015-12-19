package com.creatubbles.api;

import com.creatubbles.api.core.Gallery;
import com.creatubbles.api.request.amazon.UploadS3ImageRequest;
import com.creatubbles.api.request.auth.OAuthAccessTokenRequest;
import com.creatubbles.api.request.creation.CreateCreationRequest;
import com.creatubbles.api.request.creation.CreationsUploadsRequest;
import com.creatubbles.api.request.creation.GetCreationsRequest;
import com.creatubbles.api.request.creator.GetCreatorsRequest;
import com.creatubbles.api.request.user.UserProfileRequest;
import com.creatubbles.api.response.auth.OAuthAccessTokenResponse;
import com.creatubbles.api.response.auth.SignUpResponse;
import com.creatubbles.api.response.creation.CreateCreationResponse;
import com.creatubbles.api.response.creation.CreationsUploadsResponse;
import com.creatubbles.api.response.creation.GetCreationsResponse;
import com.creatubbles.api.response.creator.CreateCreatorResponse;
import com.creatubbles.api.response.creator.GetCreatorsResponse;
import com.creatubbles.api.response.gallery.CreateUserGalleryResponse;
import com.creatubbles.api.response.user.UserProfileResponse;
import com.creatubbles.api.util.EndPoints;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.client.JerseyClient;
import org.glassfish.jersey.client.JerseyClientBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


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
            .create();

    public final static JerseyClient CLIENT = JerseyClientBuilder
            .createClient()
            .property(ClientProperties.CONNECT_TIMEOUT, 5000)
            .property(ClientProperties.READ_TIMEOUT, 5000);

    public static String buildURL(final String endPoint) {
        String base = staging ? EndPoints.URL_BASE_STAGING : EndPoints.URL_BASE;
        return base.concat(endPoint);
    }

    private static boolean staging = false;

    public static void setStagingMode(boolean staging) {
        CreatubblesAPI.staging = staging;
    }

    public static void main(String[] args) throws IOException {
        CreatubblesAPI.setStagingMode(true);
        OAuthAccessTokenRequest request = new OAuthAccessTokenRequest("jevgeni.koltsin@gmail.com", "ccttbb");
        OAuthAccessTokenResponse response = request.execute().getResponse();
        String accessToken = response.access_token;
        System.out.println(accessToken);

        UserProfileRequest userProfileRequest = new UserProfileRequest(accessToken);
        UserProfileResponse userProfileResponse = userProfileRequest.execute().getResponse();
        System.out.println(userProfileResponse.user.id);

        GetCreatorsRequest getCreators = new GetCreatorsRequest(userProfileResponse.user.id, accessToken);
        GetCreatorsResponse getCreatorsResponse = getCreators.execute().getResponse();
        System.out.println(getCreatorsResponse.creators != null);

        GetCreationsRequest getCreations = new GetCreationsRequest(userProfileResponse.user.id, accessToken);
        GetCreationsResponse getCreationsResponse = getCreations.execute().getResponse();
        System.out.println(getCreationsResponse.total_count);

        CreateCreationRequest createCreation = new CreateCreationRequest(accessToken);
        CreateCreationResponse createCreationResponse = createCreation.execute().getResponse();
        System.out.println(createCreationResponse.creation.id);

        CreationsUploadsRequest creationsUploads = new CreationsUploadsRequest(createCreationResponse.creation.id, accessToken);
        CreationsUploadsResponse creationsUploadsResponse = creationsUploads.execute().getResponse();
        System.out.println(creationsUploadsResponse.url);

        File file = new File("C:/dev/1.png");
        byte[] data = Files.readAllBytes(file.toPath());
        String fileName = System.currentTimeMillis() + "creation.png";
        UploadS3ImageRequest uploadS3Image = new UploadS3ImageRequest(data, fileName, creationsUploadsResponse.url);
        uploadS3Image.execute().getResponse();
        System.out.println("-Finish-");
    }

}
