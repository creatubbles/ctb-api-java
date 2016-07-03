package com.creatubbles.api.response.gallery;

import com.creatubbles.api.core.CreatubblesResponse;
import com.creatubbles.api.core.Gallery;
import com.creatubbles.api.response.ArrayResponse;
import com.google.gson.annotations.SerializedName;

@ArrayResponse
public class ListGalleriesResponse extends CreatubblesResponse {

    @SerializedName("attributes")
    private Gallery galleries;
}
