package com.creatubbles.api.core;

import java.util.Map;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.NonFinal;

@ToString
@EqualsAndHashCode
@NonFinal
public class Image {
    
    private Map<ImageType, String> links;
    
    public String getUrl(ImageType type) {
        return links.get(type);
    }

    public enum ImageType {
        original,
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
