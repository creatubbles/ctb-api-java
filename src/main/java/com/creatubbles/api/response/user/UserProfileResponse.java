package com.creatubbles.api.response.user;

import com.creatubbles.api.CreatubblesAPI;
import com.creatubbles.api.core.CreatubblesResponse;
import com.creatubbles.api.core.User;
import com.google.gson.*;

import java.lang.reflect.Type;

public class UserProfileResponse extends CreatubblesResponse implements JsonDeserializer<UserProfileResponse> {
    public User user;

    public UserProfileResponse() {

    }

    @Override
    public UserProfileResponse deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        UserProfileResponse userProfileResponse = new UserProfileResponse();

        if (!jsonElement.isJsonPrimitive()) {
            JsonObject jsonObject = (JsonObject) jsonElement;

            if (jsonObject.get("id") != null) {
                userProfileResponse.user = CreatubblesAPI.GSON.fromJson(jsonElement, User.class);
                return userProfileResponse;
            }
        }

        return userProfileResponse;
    }
}
