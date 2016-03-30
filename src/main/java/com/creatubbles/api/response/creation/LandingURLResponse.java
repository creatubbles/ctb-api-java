package com.creatubbles.api.response.creation;

import java.lang.reflect.Type;

import com.creatubbles.api.core.CreatubblesResponse;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;


public class LandingURLResponse extends CreatubblesResponse implements JsonDeserializer<LandingURLResponse> {
    public String url;
    
    @Override
    public LandingURLResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        url = json.getAsJsonObject().getAsJsonObject("data").getAsJsonObject("attributes").get("url").getAsString();
        return this;
    }
}
