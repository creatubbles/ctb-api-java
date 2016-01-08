package com.creatubbles.api.response.creator;

import java.util.List;

import com.creatubbles.api.APIVersion;
import com.creatubbles.api.core.Creator;
import com.creatubbles.api.core.CreatubblesResponse;

@APIVersion(1)
@Deprecated
public class UsersCreatorsResponse extends CreatubblesResponse {
    public int total_entries, total_pages;
    public String page, next, prev, first, last;
    public List<Creator> creators;
}
