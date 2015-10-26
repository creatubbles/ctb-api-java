package com.creatubbles.api.core;

public abstract class CreatubblesResponse {
    public String message;
    private CreatubblesRequest originatingRequest;

    public final CreatubblesRequest getOriginalRequest() {
        return originatingRequest;
    }

    public void setOriginatingRequest(CreatubblesRequest originatingRequest) {
        this.originatingRequest = originatingRequest;
    }
}
