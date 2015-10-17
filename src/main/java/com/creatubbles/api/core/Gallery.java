package com.creatubbles.api.core;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class Gallery implements JsonSerializer<Gallery> {
    public int id;
    public String name, description, gallery_type, created_at, banner;
    public boolean is_open_for_all;
    public int owner_id;
    public String owner_type;

    @Override
    public JsonElement serialize(Gallery gallery, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("id", id);
        jsonObject.addProperty("name", name);
        jsonObject.addProperty("description", description);
        jsonObject.addProperty("gallery_type", gallery_type);
        jsonObject.addProperty("created_at", created_at);
        jsonObject.addProperty("banner", banner);
        if (is_open_for_all) {
            jsonObject.addProperty("open_for_all", 1);
        } else {
            jsonObject.addProperty("open_for_all", 0);
        }
        jsonObject.addProperty("owner_id", owner_id);
        jsonObject.addProperty("owner_type", owner_type);

        return jsonObject;
    }
}
