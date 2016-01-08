package com.creatubbles.api.response.creator;

import java.lang.reflect.Type;
import java.util.List;

import jersey.repackaged.com.google.common.collect.Lists;

import com.creatubbles.api.APIVersion;
import com.creatubbles.api.CreatubblesAPI;
import com.creatubbles.api.core.CreatubblesResponse;
import com.creatubbles.api.core.User;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

@APIVersion(2)
public class GetCreatorsResponse extends CreatubblesResponse implements JsonDeserializer<GetCreatorsResponse> {

    public List<User> creators;

    @Override
    public GetCreatorsResponse deserialize(JsonElement jsonElement, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (!jsonElement.isJsonPrimitive()) {
            JsonObject jsonObject = (JsonObject) jsonElement;
            JsonArray data = jsonObject.getAsJsonArray("data");
            if (data != null) {
                creators = Lists.newArrayList();
                for (int i = 0; i < data.size(); i++) {
                    JsonElement elem = data.get(i);
                    if (!elem.isJsonPrimitive()) {
                        JsonObject obj = (JsonObject) elem;
                        JsonObject attributes = obj.getAsJsonObject("attributes");
                        JsonElement idE = obj.get("id");
                        if (idE != null && attributes != null) {
                            User creator = CreatubblesAPI.GSON.fromJson(attributes, User.class);
                            creator.id = idE.getAsString();
                            creators.add(creator);
                        }
                    }
                }
            }
        }
        return this;
    }
}
