package com.creatubbles.api.response.landingurls;

import com.creatubbles.api.APIVersion;
import com.creatubbles.api.core.CreatubblesResponse;
import com.creatubbles.api.core.LandingUrl;
import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * Created by Jevgeni on 10.03.2016.
 */
@APIVersion(2)
public class GetSpecificLandingUrlResponse extends CreatubblesResponse implements JsonDeserializer<GetSpecificLandingUrlResponse> {

    public LandingUrl url;

    @Override
    public GetSpecificLandingUrlResponse deserialize(JsonElement jsonElement, Type typeOfT, JsonDeserializationContext context) throws
            JsonParseException {
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
                url = new LandingUrl();
                url.type = LandingUrl.LandingUrlType.from(idE.getAsString());
                url.url = attributes.get("url").getAsString();
            }
        }
    }
}
