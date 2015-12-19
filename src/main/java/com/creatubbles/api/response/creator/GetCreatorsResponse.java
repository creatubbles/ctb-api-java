package com.creatubbles.api.response.creator;

import com.creatubbles.api.APIVersion;
import com.creatubbles.api.CreatubblesAPI;
import com.creatubbles.api.core.Creator;
import com.creatubbles.api.core.CreatubblesResponse;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@APIVersion(2)
public class GetCreatorsResponse extends CreatubblesResponse implements JsonDeserializer<GetCreatorsResponse> {

    public List<Creator> creators = new ArrayList<Creator>();

    @Override
    public GetCreatorsResponse deserialize(JsonElement jsonElement, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (!jsonElement.isJsonPrimitive()) {
            JsonObject jsonObject = (JsonObject) jsonElement;
            JsonArray data = jsonObject.getAsJsonArray("data");
            if (data != null) {
                for (int i = 0; i < data.size(); i++) {
                    JsonElement elem = data.get(i);
                    if (!elem.isJsonPrimitive()) {
                        JsonObject obj = (JsonObject) jsonElement;
                        JsonObject attributes = obj.getAsJsonObject("attributes");
                        JsonElement idE = obj.get("id");
                        if (idE != null && attributes != null) {
                            Creator creator = CreatubblesAPI.GSON.fromJson(attributes, Creator.class);
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
