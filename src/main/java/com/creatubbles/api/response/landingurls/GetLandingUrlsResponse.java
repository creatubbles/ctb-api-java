package com.creatubbles.api.response.landingurls;

import com.creatubbles.api.APIVersion;
import com.creatubbles.api.core.CreatubblesResponse;
import com.creatubbles.api.core.LandingUrl;
import com.google.gson.*;
import jersey.repackaged.com.google.common.collect.Lists;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Jevgeni on 10.03.2016.
 */
@APIVersion(2)
public class GetLandingUrlsResponse extends CreatubblesResponse implements JsonDeserializer<GetLandingUrlsResponse> {

    public List<LandingUrl> urls;

    @Override
    public GetLandingUrlsResponse deserialize(JsonElement jsonElement, Type typeOfT, JsonDeserializationContext context) throws
            JsonParseException {
        if (!jsonElement.isJsonPrimitive()) {
            JsonObject jsonObject = (JsonObject) jsonElement;
            deserializeData(jsonObject);
        }
        return this;
    }

    private void deserializeData(JsonObject jsonObject) {
        JsonArray data = jsonObject.getAsJsonArray("data");
        if (data != null) {
            urls = Lists.newArrayList();
            for (int i = 0; i < data.size(); i++) {
                JsonElement elem = data.get(i);
                if (!elem.isJsonPrimitive()) {
                    JsonObject obj = (JsonObject) elem;
                    JsonObject attributes = obj.getAsJsonObject("attributes");
                    JsonElement idE = obj.get("id");
                    if (idE != null && attributes != null) {
                        LandingUrl landingUrl = new LandingUrl();
                        landingUrl.type = LandingUrl.LandingUrlType.from(idE.getAsString());
                        landingUrl.url = attributes.get("url").getAsString();
                        urls.add(landingUrl);
                    }
                }
            }
        }
    }
}