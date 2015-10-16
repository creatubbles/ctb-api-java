package com.creatubbles.api.response.creator;

import com.creatubbles.api.CreatubblesAPI;
import com.creatubbles.api.core.Creator;
import com.creatubbles.api.core.CreatubblesResponse;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.List;

public class CreateCreatorResponse extends CreatubblesResponse implements JsonDeserializer<CreateCreatorResponse> {
    public Creator creator;
    public CreateCreatorError errors;

    @Override
    public CreateCreatorResponse deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        CreateCreatorResponse createCreatorResponse = new CreateCreatorResponse();

        if (!jsonElement.isJsonPrimitive()) {
            JsonObject jsonObject = (JsonObject) jsonElement;

            if (jsonObject.get("creator") != null && jsonObject.get("creator").isJsonObject()) {
                JsonObject creatorObject = (JsonObject) jsonObject.get("creator");

                if (creatorObject.get("id") != null) {
                    createCreatorResponse.creator = CreatubblesAPI.GSON.fromJson(jsonElement, Creator.class);
                    return createCreatorResponse;
                } else if (creatorObject.get("errors") != null) {
                    JsonObject jsonErrorObject = (JsonObject) jsonObject.get("errors");
                    createCreatorResponse.errors = CreatubblesAPI.GSON.fromJson(jsonErrorObject, CreateCreatorError.class);
                    return createCreatorResponse;
                }
            }
        }

        return createCreatorResponse;
    }

    public static class CreateCreatorError {
        public List<String> gender, name, birth_year, base;
    }
}
