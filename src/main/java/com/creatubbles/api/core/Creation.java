package com.creatubbles.api.core;

import java.util.HashMap;

import jersey.repackaged.com.google.common.collect.Maps;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.ToString;
import lombok.Value;
import lombok.experimental.NonFinal;

import com.google.gson.annotations.SerializedName;

@Value
@ToString(callSuper = true)
@NonFinal
public class Creation extends CreatubblesObject {
  
    String name;
    @SerializedName("created_at")
    String createdDate;
    @SerializedName("created_at_age")
    String createdAge;
    
    @Getter(value = AccessLevel.NONE)
    @SerializedName("created_at_age_per_creator")
    HashMap<String, String> creatorToAgeMap = Maps.newHashMap();
    
    public String getCreatedAge(String creatorId) {
        return creatorToAgeMap.get(creatorId);
    }
    
    public String getCreatedAge(User creator) {
        return getCreatedAge(creator.getId());
    }
    
    @SerializedName("created_at_month")
    int createdMonth;
    @SerializedName("created_at_year")
    int createdYear;
    @SerializedName("comments_count")
    int commentCount;
    @SerializedName("bubble_count")
    int bubbleCount;
    @SerializedName("views_count")
    int viewCount;
    
    @SerializedName("last_bubbled_at")
    String lastBubbleDate;
    @SerializedName("last_commented_at")
    String lastCommentDate;
    @SerializedName("last_submitted_at")
    String lastSubmitDate;
    
    Image image;
    
    @SerializedName("short_url")
    String shortUrl;
    
    boolean approved;
}
