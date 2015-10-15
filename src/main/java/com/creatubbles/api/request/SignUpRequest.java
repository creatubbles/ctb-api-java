package com.creatubbles.api.request;

import com.creatubbles.api.CreatubblesAPI;
import com.creatubbles.api.core.CreatubblesRequest;
import com.creatubbles.api.core.User;
import com.creatubbles.api.util.EndPoints;
import com.creatubbles.api.util.HttpMethod;
import com.google.gson.JsonObject;

public class SignUpRequest extends CreatubblesRequest {

    public SignUpRequest(String email, String password, String country) {
        super(EndPoints.SIGN_UP, HttpMethod.POST);
        if (email != null && password != null & country != null) {
            User user = new User();
            user.email = email;
            user.password = password;
            user.country = country;
            JsonObject jsonObject = new JsonObject();
            jsonObject.add("user", CreatubblesAPI.GSON.toJsonTree(user));
            setData(jsonObject.toString());
        }
    }

    public SignUpRequest(User user) {
        super(EndPoints.SIGN_UP, HttpMethod.POST);
        if (user != null) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.add("user", CreatubblesAPI.GSON.toJsonTree(user));
            setData(jsonObject.toString());
        }
    }

}
