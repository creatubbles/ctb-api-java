package com.creatubbles.api.util;

public class EndPoints {

    public final static String URL_BASE = "https://www.creatubbles.com/api/v1/";

    public static final String SIGN_IN = "users/sign_in.json";

    public static final String SIGN_UP = "users.json";

    public static final String USERS_PROFILE = "users/%s.json";

    @Deprecated
    public static final String AUTH_TOKEN = "sessions/auth_token.json";

    public static final String USER_TOKEN = "sessions/user_token.json";

    public static final String USERS_CREATORS = "users/%s/creators.json";

    public static final String CREATORS_FOLLOWING_USERS = "creators/%s/users/following.json";

    public static final String CREATORS_FOLLOWERS = "creators/%s/users/followers.json";

    public static final String USERS_GALLERIES = "users/%s/galleries.json";

    public static final String CREATIONS = "creations.json";

    public static final String CREATORS_CREATIONS = "creators/%d/creations.json?page=%d";

    public static final String SEARCH_CREATIONS = "search/creations.json";

    public static final String AWS_TOKEN = "sessions/aws_token.json";

}
