package com.creatubbles.api;

import com.creatubbles.api.response.SignUpResponse;
import com.creatubbles.api.response.UserProfileResponse;
import com.creatubbles.api.util.EndPoints;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.client.JerseyClient;
import org.glassfish.jersey.client.JerseyClientBuilder;

public class CreatubblesAPI {
    public final static Gson GSON = new GsonBuilder()
            .registerTypeAdapter(SignUpResponse.class, new SignUpResponse())
            .registerTypeAdapter(UserProfileResponse.class, new UserProfileResponse())
            .create();

    public final static JerseyClient CLIENT = JerseyClientBuilder
            .createClient()
            .register(SignUpResponse.class)
            .property(ClientProperties.CONNECT_TIMEOUT, 5000)
            .property(ClientProperties.READ_TIMEOUT, 5000);

    public static String buildURL(final String endPoint) {
        return EndPoints.URL_BASE + endPoint;
    }
}
