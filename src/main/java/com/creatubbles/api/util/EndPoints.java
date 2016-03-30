package com.creatubbles.api.util;

public class EndPoints {

    public static final String URL_BASE = "https://api.creatubbles.com/v2/";

    public static final String URL_BASE_STAGING = "https://api.staging.creatubbles.com/v2/";

    public static final String SIGN_IN = "users/sign_in.json";

    public static final String SIGN_UP = "users.json";

    @Deprecated
    public static final String AUTH_TOKEN = "sessions/auth_token.json";

    public static final String USER_TOKEN = "sessions/user_token.json";

    public static final String USERS_CREATORS = "users/%s/creators.json";

    public static final String CREATORS_FOLLOWING_USERS = "creators/%s/users/following.json";

    public static final String CREATORS_FOLLOWERS = "creators/%s/users/followers.json";

    public static final String USERS_GALLERIES = "users/%s/galleries.json";

    public static final String CREATIONS_BY_ID = "creations/%s.json";

    public static final String SEARCH_CREATIONS = "search/creations.json";

    public static final String AWS_TOKEN = "sessions/aws_token.json";

    //API v2
    public static final String OAUTH_TOKEN = "oauth/token";
    public static final String USERS = "users";
    public static final String USERS_PROFILE = "users/%s";
    public static final String CREATIONS = "creations";
    public static final String CREATIONS_UPLOADS = "creations/%s/uploads";
    public static final String PING_CREATIONS_UPLOADS = "uploads/%s";
    public static final String LANDING_URLS = "landing_urls";
    public static final String SPECIFIC_LANDING_URL = "landing_urls/%s";
    public static final String CREATION_LANDING_URL = "creations/%s/landing_url";

}
