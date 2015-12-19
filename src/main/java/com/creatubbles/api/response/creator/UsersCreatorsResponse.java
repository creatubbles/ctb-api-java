package com.creatubbles.api.response.creator;

import com.creatubbles.api.APIVersion;
import com.creatubbles.api.core.Creator;
import com.creatubbles.api.core.CreatubblesResponse;

import java.util.List;

@APIVersion(1)
public class UsersCreatorsResponse extends CreatubblesResponse {
    public int total_entries, total_pages;
    public String page, next, prev, first, last;
    public List<Creator> creators;
}
