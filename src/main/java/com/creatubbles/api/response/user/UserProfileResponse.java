package com.creatubbles.api.response.user;

import com.creatubbles.api.APIVersion;
import com.creatubbles.api.CreatubblesAPI;
import com.creatubbles.api.core.CreatubblesResponse;
import com.creatubbles.api.core.User;
import com.google.gson.*;

import java.lang.reflect.Type;

@APIVersion(2)
public class UserProfileResponse extends CreatubblesResponse implements JsonDeserializer<UserProfileResponse> {
    public User user;

    @Override
    public UserProfileResponse deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        UserProfileResponse userProfileResponse = new UserProfileResponse();
        if (!jsonElement.isJsonPrimitive()) {
            JsonObject jsonObject = (JsonObject) jsonElement;
            JsonObject data = jsonObject.getAsJsonObject("data");
            if (data != null) {
                JsonObject attributes = data.getAsJsonObject("attributes");
                JsonElement idE = data.get("id");
                if (attributes != null && idE != null) {
                    String id = idE.getAsString();
                    userProfileResponse.user = CreatubblesAPI.GSON.fromJson(attributes, User.class);
                    userProfileResponse.user.id = id;
                }
            }
        }
        return userProfileResponse;
    }
}
