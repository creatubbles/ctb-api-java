package com.creatubbles.api.response.gallery;

import com.creatubbles.api.APIVersion;
import com.creatubbles.api.CreatubblesAPI;
import com.creatubbles.api.core.CreatubblesResponse;
import com.creatubbles.api.core.Gallery;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.List;

@APIVersion(1)
public class CreateUserGalleryResponse extends CreatubblesResponse implements JsonDeserializer<CreateUserGalleryResponse> {
    public Gallery gallery;
    public CreateUserGalleryError errors;

    @Override
    public CreateUserGalleryResponse deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        CreateUserGalleryResponse createUserGalleryResponse = new CreateUserGalleryResponse();

        if (!jsonElement.isJsonPrimitive()) {
            JsonObject jsonObject = (JsonObject) jsonElement;

            if (jsonObject.get("gallery") != null && jsonObject.get("gallery").isJsonObject()) {
                JsonObject galleryObject = (JsonObject) jsonObject.get("gallery");

                if (galleryObject.get("id") != null) {
                    createUserGalleryResponse.gallery = CreatubblesAPI.GSON.fromJson(jsonElement, Gallery.class);
                    return createUserGalleryResponse;
                } else if (galleryObject.get("errors") != null) {
                    JsonObject jsonErrorObject = (JsonObject) jsonObject.get("errors");
                    createUserGalleryResponse.errors = CreatubblesAPI.GSON.fromJson(jsonErrorObject, CreateUserGalleryError.class);
                    return createUserGalleryResponse;
                }
            }
        }

        return createUserGalleryResponse;
    }

    public static class CreateUserGalleryError {
        public List<String> name;
    }
}
