package com.creatubbles.api.core;

public class Creation {

    public String id, user_id;
    public String[] creator_ids;
    public String name, created_at, created_at_age;
    public int created_at_month, created_at_year, comments_count, bubble_count, views_count;
    public String last_bubbled_at, last_commented_at, last_submitted_at;
    public Image image;
    public String short_url;

    @Deprecated
    public Creator[] creators;
    /**
     * @see #short_url
     * @see Image#links
     */
    @Deprecated
    public String store_dir;
    /**
     * @see #short_url
     * @see Image#links
     */
    @Deprecated
    public String url;
}
