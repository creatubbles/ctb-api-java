package com.creatubbles.api.core;

import java.util.List;

public class Creator {
    public int id;
    public String name, created_at, avatar_url;
    public int creator_user_id, birth_month, birth_year;
    public String age;
    public int gender;
    public boolean is_male;
    public List<Group> groups;
    public Country country;
    public int views_count, followers_count, followings_count;
}
