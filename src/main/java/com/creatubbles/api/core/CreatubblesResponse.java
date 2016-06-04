package com.creatubbles.api.core;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude = "originatingRequest")
public abstract class CreatubblesResponse {

    private String type;
    private String id;
    
    private String message;
    
    // Type is unnecessary because the response has obviously already been gotten
    private CreatubblesRequest<?> originatingRequest;

    public final CreatubblesRequest<?> getOriginalRequest() {
        return originatingRequest;
    }

    public final void setOriginatingRequest(CreatubblesRequest<?> originatingRequest) {
        this.originatingRequest = originatingRequest;
    }

    public final void setId(String id) {
        this.id = id;
        handleId(id);
    }
    
    public void handleId(String id) {}
}
