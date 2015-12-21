package com.creatubbles.api.response.creation;

import com.creatubbles.api.APIVersion;
import com.creatubbles.api.core.CreatubblesResponse;
import com.google.gson.*;

import java.lang.reflect.Type;

@APIVersion(2)
public class CreationsUploadsResponse extends CreatubblesResponse implements JsonDeserializer<CreationsUploadsResponse> {

    public int id;
    public String url;

    @Override
    public CreationsUploadsResponse deserialize(JsonElement jsonElement, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (!jsonElement.isJsonPrimitive()) {
            JsonObject jsonObject = (JsonObject) jsonElement;
            deserializeData(jsonObject);
        }
        return this;
    }

    private void deserializeData(JsonObject jsonObject) {
        JsonObject data = jsonObject.getAsJsonObject("data");
        if (data != null) {
            JsonObject attributes = data.getAsJsonObject("attributes");
            JsonElement idE = data.get("id");
            if (idE != null && attributes != null) {
                url = attributes.get("url").getAsString();
                id = idE.getAsInt();
            }
        }
    }
}
