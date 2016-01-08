package com.creatubbles.api.core;

import java.util.Map;

public class Image {

    public Map<ImageType, String> links;

    public enum ImageType {
        full_view,
        list_view_retina,
        list_view,
        matrix_view_retina,
        matrix_view,
        gallery_mobile,
        explore_mobile,
        share;
    }
}
