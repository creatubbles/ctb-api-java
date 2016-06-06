package com.creatubbles.api.core;

import java.lang.reflect.Type;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.annotations.SerializedName;

@ToString(callSuper = true)
@EqualsAndHashCode
@Builder
@Getter
public class Gallery {

    private int id;
    private String name, description;

    @SerializedName("type")
    private String type;
    @SerializedName("created_at")
    private String createdDate;
    private Image banner;

    @SerializedName("open_for_all")
    private boolean openForAll;

    @SerializedName("owner_id")
    private int ownerId;
    @SerializedName("owner_type")
    private String ownerType;

    public static class Serializer implements JsonSerializer<Gallery> {

        @Override
        public JsonElement serialize(Gallery src, Type typeOfSrc, JsonSerializationContext context) {

            JsonObject root = new JsonObject();

            JsonObject data = new JsonObject();
            data.addProperty("type", src.type);

            JsonObject attributes = new JsonObject();
            attributes.addProperty("name", src.name);
            attributes.addProperty("descrtiption", src.description);
            attributes.addProperty("open_for_all", src.openForAll ? 1 : 0);

            JsonObject relationships = new JsonObject();

            JsonObject owner = new JsonObject();
            JsonObject ownerdata = new JsonObject();
            ownerdata.addProperty("type", src.ownerType);
            ownerdata.addProperty("id", src.ownerId);
            owner.add("data", ownerdata);

            relationships.add("owner", owner);

            data.add("attributes", attributes);
            data.add("relationships", relationships);

            root.add("data", data);
            return root;
        }
    }

}
