package com.creatubbles.api.core;

import java.util.HashMap;

import jersey.repackaged.com.google.common.collect.Maps;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import com.google.gson.annotations.SerializedName;

@ToString(callSuper = true)
@EqualsAndHashCode
@Getter
public class Creation extends CreatubblesObject {
  
    private String name;
    @SerializedName("created_at")
    private String createdDate;
    @SerializedName("created_at_age")
    private String createdAge;
    
    @Getter(value = AccessLevel.NONE)
    @SerializedName("created_at_age_per_creator")
    private HashMap<String, String> creatorToAgeMap = Maps.newHashMap();
    
    public String getCreatedAge(String creatorId) {
        return creatorToAgeMap.get(creatorId);
    }
    
    public String getCreatedAge(User creator) {
        return getCreatedAge(creator.getId());
    }
    
    @SerializedName("created_at_month")
    private int createdMonth;
    @SerializedName("created_at_year")
    private int createdYear;
    @SerializedName("comments_count")
    private int commentCount;
    @SerializedName("bubble_count")
    private int bubbleCount;
    @SerializedName("views_count")
    private int viewCount;
    
    @SerializedName("last_bubbled_at")
    private String lastBubbleDate;
    @SerializedName("last_commented_at")
    private String lastCommentDate;
    @SerializedName("last_submitted_at")
    private String lastSubmitDate;
    
    private Image image;
    
    @SerializedName("short_url")
    private String shortUrl;
    
    private boolean approved;
}
