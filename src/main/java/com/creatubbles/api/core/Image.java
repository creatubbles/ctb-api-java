package com.creatubbles.api.core;

import java.util.List;
import java.util.Map;

public class Image {
    /**
     * @see #links
     */
    @Deprecated
    public String url;
    
    @Deprecated
    public List<Creator> creators;
    
    /**
     * @see #links
     */
    @Deprecated
    public String store_dir;
    
    public Map<ImageType, String> links;

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
