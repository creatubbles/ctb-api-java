package com.creatubbles.api.response.landingurls;

import java.lang.reflect.Type;

import com.creatubbles.api.core.CreatubblesResponse;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;


public class GetCreationLandingUrlResponse extends CreatubblesResponse implements JsonDeserializer<GetCreationLandingUrlResponse> {
    public String url;
    
    @Override
    public GetCreationLandingUrlResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        url = json.getAsJsonObject().getAsJsonObject("data").getAsJsonObject("attributes").get("url").getAsString();
        return this;
    }
}
