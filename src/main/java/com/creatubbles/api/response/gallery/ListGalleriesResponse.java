package com.creatubbles.api.response.gallery;

import com.creatubbles.api.APIVersion;
import com.creatubbles.api.core.CreatubblesResponse;
import com.creatubbles.api.core.Gallery;
import com.creatubbles.api.response.ArrayResponse;
import com.google.gson.annotations.SerializedName;

@APIVersion(2)
@ArrayResponse
public class ListGalleriesResponse extends CreatubblesResponse {

    @SerializedName("attributes")
    private Gallery galleries;
}
