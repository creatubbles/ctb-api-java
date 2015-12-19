package com.creatubbles.api.response.creator;

import com.creatubbles.api.APIVersion;
import com.creatubbles.api.core.CreatubblesResponse;
import com.creatubbles.api.core.User;

import java.util.List;

@APIVersion(1)
public class CreatorsFollowersResponse extends CreatubblesResponse {
    public int total_entries, total_pages, page;
    public List<User> users;
}
