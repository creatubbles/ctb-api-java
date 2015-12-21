package com.creatubbles.api.response.creation;

import com.creatubbles.api.APIVersion;
import com.creatubbles.api.CreatubblesAPI;
import com.creatubbles.api.core.Creation;
import com.creatubbles.api.core.CreatubblesResponse;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@APIVersion(2)
public class GetCreationsResponse extends CreatubblesResponse implements JsonDeserializer<GetCreationsResponse> {
    public int total_pages, total_count;
    public List<Creation> creations = new ArrayList<Creation>();

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
            for (int i = 0; i < data.size(); i++) {
                JsonElement elem = data.get(i);
                if (!elem.isJsonPrimitive()) {
                    JsonObject obj = (JsonObject) elem;
                    JsonObject attributes = obj.getAsJsonObject("attributes");
                    JsonElement idE = obj.get("id");
                    if (idE != null && attributes != null) {
                        Creation creation = CreatubblesAPI.GSON.fromJson(attributes, Creation.class);
                        creation.id = idE.getAsString();
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