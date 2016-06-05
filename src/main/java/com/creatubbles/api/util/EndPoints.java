package com.creatubbles.api.util;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum EndPoints implements EndPoint {

    //API v2
    OAUTH_TOKEN("oauth/token"),
    USERS("users"),
    USERS_PROFILE("users/%s"),
    CREATIONS("creations"),
    CREATIONS_UPLOADS("creations/%s/uploads"),
    PING_CREATIONS_UPLOADS("uploads/%s"),
    LANDING_URLS("landing_urls"),
    SPECIFIC_LANDING_URL("landing_urls/%s"),
    CREATION_LANDING_URL("creations/%s/landing_url"),
    GALLERIES("galleries"), 
    USER_GALLERIES("users/%s/galleries"),
    CREATIONS_GALLERIES("creations/%s/galleries"),
    
    ;

    private final String template;

    @Override
    public String getTemplate() {
        return template;
    }

    @Override
    public String format(Object... args) {
        return String.format(getTemplate(), args);
    }
    
    @Override
    public String toString() {
        return getTemplate();
    }
}
