package com.creatubbles.api.response.creation;

import lombok.Getter;

import com.creatubbles.api.core.Creation;
import com.creatubbles.api.core.CreatubblesResponse;
import com.google.gson.annotations.SerializedName;

@Getter
public class CreateCreationResponse extends CreatubblesResponse {

    @SerializedName("attributes")
    private Creation creation;

}
