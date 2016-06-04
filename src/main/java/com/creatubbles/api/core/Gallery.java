package com.creatubbles.api.core;

import java.lang.reflect.Type;

import lombok.Builder;
import lombok.Value;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.annotations.SerializedName;

@Value
@Builder
public class Gallery {
    
    int id;
    String name, description;
    
    @SerializedName("type")
    String type;
    @SerializedName("created_at")
    String createdDate;
    Image banner;
    
    @SerializedName("open_for_all")
    public boolean openForAll;
    
    @SerializedName("owner_id")
    public int ownerId;
    @SerializedName("owner_type")
    public String ownerType;
    
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
