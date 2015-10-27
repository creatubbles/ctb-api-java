package com.creatubbles.api.core;

public abstract class CreatubblesResponse {

    public String message;
    // Type is unnecessary because the response has obviously already been gotten
    private CreatubblesRequest<?> originatingRequest;

    public final CreatubblesRequest<?> getOriginalRequest() {
        return originatingRequest;
    }

    public void setOriginatingRequest(CreatubblesRequest<?> originatingRequest) {
        this.originatingRequest = originatingRequest;
    }
}
