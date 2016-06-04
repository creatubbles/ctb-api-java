package com.creatubbles.api.response.gallery;

import lombok.Getter;

import com.creatubbles.api.APIVersion;
import com.creatubbles.api.core.CreatubblesResponse;
import com.creatubbles.api.core.Gallery;
import com.google.gson.annotations.SerializedName;

@APIVersion(2)
@Getter
public class CreateGalleryResponse extends CreatubblesResponse {
    
    @SerializedName("attributes")
    private Gallery gallery;
}
