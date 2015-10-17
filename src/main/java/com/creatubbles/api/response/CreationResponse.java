package com.creatubbles.api.response;

import com.creatubbles.api.core.Creation;
import com.creatubbles.api.core.CreatubblesResponse;

import java.util.List;

public class CreationResponse extends CreatubblesResponse {
    public int total_entries, total_pages, page;
    public List<Creation> creations;
}
