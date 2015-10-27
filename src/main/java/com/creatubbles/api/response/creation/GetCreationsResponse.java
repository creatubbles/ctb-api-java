package com.creatubbles.api.response.creation;

import com.creatubbles.api.core.Creation;
import com.creatubbles.api.core.CreatubblesResponse;

public class GetCreationsResponse extends CreatubblesResponse {
    public int total_entries, total_pages, page;
    public Creation[] creations;
}
