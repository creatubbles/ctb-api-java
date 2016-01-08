package com.creatubbles.api.response.creation;

import java.lang.reflect.Type;
import java.util.List;

import jersey.repackaged.com.google.common.collect.Lists;

import com.creatubbles.api.APIVersion;
import com.creatubbles.api.CreatubblesAPI;
import com.creatubbles.api.core.Creation;
import com.creatubbles.api.core.CreatubblesResponse;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

@APIVersion(2)
public class GetCreationsResponse extends CreatubblesResponse implements JsonDeserializer<GetCreationsResponse> {
    public int total_pages, total_count;
    public List<Creation> creations;

    @Override
    public GetCreationsResponse deserialize(JsonElement jsonElement, Type typeOfT, JsonDeserializationContext context) throws
            JsonParseException {
        if (!jsonElement.isJsonPrimitive()) {
            JsonObject jsonObject = (JsonObject) jsonElement;
            deserializeMeta(jsonObject);
            deserializeData(jsonObject);
        }
        return this;
    }

    private void deserializeData(JsonObject jsonObject) {
        JsonArray data = jsonObject.getAsJsonArray("data");
        if (data != null) {
            creations = Lists.newArrayList();
            for (int i = 0; i < data.size(); i++) {
                JsonElement elem = data.get(i);
                if (!elem.isJsonPrimitive()) {
                    JsonObject obj = (JsonObject) elem;
                    JsonObject attributes = obj.getAsJsonObject("attributes");
                    JsonElement idE = obj.get("id");
                    if (idE != null && attributes != null) {
                        Creation creation = CreatubblesAPI.GSON.fromJson(attributes, Creation.class);
                        creation.id = idE.getAsString();
                        
                        JsonObject relationships = obj.getAsJsonObject("relationships");
                        JsonObject user = relationships.getAsJsonObject("user");
                        creation.user_id = user.getAsJsonObject("data").getAsJsonPrimitive("id").getAsString();
                        JsonArray creators = relationships.getAsJsonObject("creators").getAsJsonArray("data");
                        creation.creator_ids = new String[creators.size()];
                        for (int j = 0; j < creators.size(); j++) {
                            creation.creator_ids[j] = creators.get(j).getAsJsonObject().getAsJsonPrimitive("id").getAsString();
                        }

                        creations.add(creation);
                    }
                }
            }
        }
    }

    private void deserializeMeta(JsonObject jsonObject) {
        JsonObject meta = jsonObject.getAsJsonObject("meta");
        if (meta != null) {
            JsonElement totalPagesE = meta.get("total_pages");
            if (totalPagesE != null) {
                total_pages = totalPagesE.getAsInt();
            }
            JsonElement totalCountE = meta.get("total_count");
            if (totalCountE != null) {
                total_count = totalCountE.getAsInt();
            }
        }
    }
}