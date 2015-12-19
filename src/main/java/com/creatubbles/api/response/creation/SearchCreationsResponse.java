package com.creatubbles.api.response.creation;

import com.creatubbles.api.APIVersion;
import com.creatubbles.api.core.Creation;
import com.creatubbles.api.core.CreatubblesResponse;

import java.util.List;

@APIVersion(1)
public class SearchCreationsResponse extends CreatubblesResponse {
    public int total_entries, total_pages;
    public String page;
    public List<Creation> creations;
}
