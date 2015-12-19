package com.creatubbles.api.response.gallery;

import com.creatubbles.api.APIVersion;
import com.creatubbles.api.core.CreatubblesResponse;
import com.creatubbles.api.core.Gallery;

import java.util.List;

@APIVersion(1)
public class UserGalleryResponse extends CreatubblesResponse {
    public int total;
    public List<Gallery> galleries;
}
