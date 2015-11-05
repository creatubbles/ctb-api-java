package com.creatubbles.api;

import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.client.JerseyClient;
import org.glassfish.jersey.client.JerseyClientBuilder;

import com.creatubbles.api.core.Gallery;
import com.creatubbles.api.response.auth.SignUpResponse;
import com.creatubbles.api.response.creator.CreateCreatorResponse;
import com.creatubbles.api.response.gallery.CreateUserGalleryResponse;
import com.creatubbles.api.response.user.UserProfileResponse;
import com.creatubbles.api.util.EndPoints;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
        String base = staging ? EndPoints.URL_BASE_STAGING : EndPoints.URL_BASE;
        return base.concat(endPoint);
    }

    private static boolean staging = false;

    public static void setStagingMode(boolean staging) {
        CreatubblesAPI.staging = staging;
    }

}
