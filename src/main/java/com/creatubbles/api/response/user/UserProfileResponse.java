package com.creatubbles.api.response.user;

import lombok.Getter;
import lombok.ToString;

import com.creatubbles.api.core.CreatubblesResponse;
import com.creatubbles.api.core.User;
import com.google.gson.annotations.SerializedName;

@Getter
@ToString(callSuper = true)
public class UserProfileResponse extends CreatubblesResponse {
    
    @SerializedName("attributes")
    private User user;
    
    @Override
    public void handleId(String id) {
        user.setId(id);
    }
}
