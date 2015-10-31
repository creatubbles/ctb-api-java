package com.creatubbles.api;

import com.creatubbles.api.core.Creation;
import com.creatubbles.api.core.Gallery;
import com.creatubbles.api.core.Image;
import com.creatubbles.api.request.amazon.UploadS3ImageRequest;
import com.creatubbles.api.request.auth.SignInRequest;
import com.creatubbles.api.request.creation.UpdateCreationRequest;
import com.creatubbles.api.request.creation.UploadCreationRequest;
import com.creatubbles.api.response.auth.SignInResponse;
import com.creatubbles.api.response.auth.SignUpResponse;
import com.creatubbles.api.response.creation.UpdateCreationResponse;
import com.creatubbles.api.response.creation.UploadCreationResponse;
import com.creatubbles.api.response.creator.CreateCreatorResponse;
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
            .create();

    public final static JerseyClient CLIENT = JerseyClientBuilder
            .createClient()
            .property(ClientProperties.CONNECT_TIMEOUT, 5000)
            .property(ClientProperties.READ_TIMEOUT, 5000);

    public static String buildURL(final String endPoint) {
        return EndPoints.URL_BASE + endPoint;
    }

    public static void main(String[] args) throws IOException {
        SignInResponse response = new SignInRequest("jevgeni.koltsin@gmail.com", "ccttbb").execute().getResponse();
        System.out.println(response.access_token);
        UploadCreationResponse uploadResponse = new UploadCreationRequest(response.access_token).execute().getResponse();
        //TODO: last screenshot
        File file = new File("C:/dev/1.png");
        byte[] data = Files.readAllBytes(file.toPath());
        String fileName = System.currentTimeMillis() + "creation.png";
        Creation creation = uploadResponse.creation;
        String relativePath = creation.store_dir + "/" + fileName;
        new UploadS3ImageRequest(data, relativePath).execute().getResponse();
        Image img = new Image();
        img.url = relativePath;
        UpdateCreationResponse updateResponse = new UpdateCreationRequest(response.access_token, creation).execute().getResponse();
        System.exit(0);
    }
}
