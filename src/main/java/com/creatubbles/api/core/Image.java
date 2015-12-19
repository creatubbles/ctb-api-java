package com.creatubbles.api.core;

import java.util.List;

public class Image {
    public String url;
    public Links links;
    public List<Creator> creators;
    public String store_dir;

    public class Links {
        public String full_view, list_view_retina, list_view, matrix_view_retina, matrix_view, gallery_mobile, explore_mobile, share;
    }
}
