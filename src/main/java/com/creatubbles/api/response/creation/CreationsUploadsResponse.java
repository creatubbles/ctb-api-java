package com.creatubbles.api.response.creation;

import lombok.Getter;

import com.creatubbles.api.core.CreatubblesResponse;
import com.google.gson.annotations.SerializedName;

@Getter
public class CreationsUploadsResponse extends CreatubblesResponse {

    private String url;
    @SerializedName("content_type")
    private String type;
    @SerializedName("ping_url")
    private String pingUrl;
}
