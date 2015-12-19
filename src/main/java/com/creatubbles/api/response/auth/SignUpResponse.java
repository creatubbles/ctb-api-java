package com.creatubbles.api.response.auth;

import com.creatubbles.api.APIVersion;
import com.creatubbles.api.CreatubblesAPI;
import com.creatubbles.api.core.CreatubblesResponse;
import com.creatubbles.api.core.User;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.List;

@APIVersion(1)
public class SignUpResponse extends CreatubblesResponse implements JsonDeserializer<SignUpResponse> {
    public User user;
    public SignUpError errors;

    public SignUpResponse() {

    }

    @Override
    public SignUpResponse deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        SignUpResponse signUpResponse = new SignUpResponse();

        if (!jsonElement.isJsonPrimitive()) {
            JsonObject jsonObject = (JsonObject) jsonElement;

            if (jsonObject.get("id") != null) {
                signUpResponse.user = CreatubblesAPI.GSON.fromJson(jsonElement, User.class);
                return signUpResponse;
            } else if (jsonObject.get("errors").isJsonObject()) {
                JsonObject jsonErrorObject = (JsonObject) jsonObject.get("errors");
                signUpResponse.errors = CreatubblesAPI.GSON.fromJson(jsonErrorObject, SignUpError.class);
                return signUpResponse;
            }
        }

        return signUpResponse;
    }

    public static class SignUpError {
        public List<String> email, password, username;
    }
}
