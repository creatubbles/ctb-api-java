package com.creatubbles.api.response.creation;

import lombok.Getter;
import lombok.ToString;

import com.creatubbles.api.APIVersion;
import com.creatubbles.api.core.Creation;
import com.creatubbles.api.core.CreatubblesResponse;
import com.creatubbles.api.response.ArrayResponse;
import com.creatubbles.api.response.relationships.Relationships;
import com.google.gson.annotations.SerializedName;

@APIVersion(2)
@ArrayResponse
@Getter
@ToString(callSuper = true)
public class GetCreationsResponse extends CreatubblesResponse {
    
    @SerializedName("attributes")
    private Creation creation;

    private Relationships relationships;

    @Override
    public void handleId(String id) {
        creation.setId(id);
    }
}