package com.creatubbles.api.core;

import com.creatubbles.api.CreatubblesAPI;
import com.creatubbles.api.util.HttpMethod;
import org.glassfish.jersey.client.JerseyWebTarget;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

public class CreatubblesRequest {
    private String endPoint;
    private HttpMethod httpMethod;
    private String acceptLanguage;
    private String xSource;
    private String data;
    private Map<String, String> urlParameters;

    public CreatubblesRequest(String endPoint, HttpMethod httpMethod) {
        this(endPoint, httpMethod, null, null);
    }

    public CreatubblesRequest(String endPoint, HttpMethod httpMethod, String accessToken) {
        this(endPoint, httpMethod, accessToken, null);
    }

    public CreatubblesRequest(String endPoint, HttpMethod httpMethod, Map<String, String> urlParameters) {
        this(endPoint, httpMethod, null, urlParameters);
    }

    public CreatubblesRequest(String endPoint, HttpMethod httpMethod, String accessToken, Map<String, String> urlParameters) {
        this.endPoint = endPoint;
        this.httpMethod = httpMethod;
        if (urlParameters != null) {
            this.urlParameters = urlParameters;
        } else {
            this.urlParameters = new HashMap<String, String>();
        }
        if (accessToken != null) {
            this.urlParameters.put("access_token", accessToken);
        }
    }

    public String getEndPoint() {
        return endPoint;
    }

    public CreatubblesRequest setEndPoint(String endPoint) {
        this.endPoint = endPoint;
        return this;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public CreatubblesRequest setHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
        return this;
    }

    public Map<String, String> getUrlParameters() {
        return urlParameters;
    }

    public String getAcceptLanguage() {
        return acceptLanguage;
    }

    public CreatubblesRequest setAcceptLanguage(String acceptLanguage) {
        this.acceptLanguage = acceptLanguage;
        return this;
    }

    public String getXSource() {
        return xSource;
    }

    public CreatubblesRequest setXSource(String xSource) {
        this.xSource = xSource;
        return this;
    }

    public String getData() {
        return data;
    }

    public CreatubblesRequest setData(String data) {
        this.data = data;
        return this;
    }

    public CreatubblesRequest setUrlParameter(String key, String value) {
        this.urlParameters.put(key, value);
        return this;
    }

    public Response execute() {
        String url = CreatubblesAPI.buildURL(endPoint);

        JerseyWebTarget webTarget = CreatubblesAPI.CLIENT.target(url);
        for (String paramKey : urlParameters.keySet()) {
            String paramValue = urlParameters.get(paramKey);
            if (paramValue != null && !paramValue.isEmpty()) {
                webTarget = webTarget.queryParam(paramKey, paramValue);
            }
        }

        Invocation.Builder invocationBuilder = webTarget
                .request(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        if (acceptLanguage != null && acceptLanguage.length() == 2) {
            invocationBuilder.header("Accept-Language", acceptLanguage.toLowerCase());
        }

        if (xSource != null && !xSource.isEmpty()) {
            invocationBuilder.header("X-Source", xSource.toLowerCase());
        }

        switch (httpMethod) {
            case GET: {
                return invocationBuilder.get();
            }
            case POST: {
                return invocationBuilder.post(Entity.entity(data, MediaType.APPLICATION_JSON));
            }
            case PUT: {
                return invocationBuilder.put(Entity.entity(data, MediaType.APPLICATION_JSON));
            }
            default: {
                return null;
            }
        }
    }

    public Future<Response> async() {
        String url = CreatubblesAPI.buildURL(endPoint);

        JerseyWebTarget webTarget = CreatubblesAPI.CLIENT.target(url);
        for (String paramKey : urlParameters.keySet()) {
            String paramValue = urlParameters.get(paramKey);
            if (paramValue != null && !paramValue.isEmpty()) {
                webTarget = webTarget.queryParam(paramKey, paramValue);
            }
        }

        Invocation.Builder invocationBuilder = webTarget
                .request(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        if (acceptLanguage != null && acceptLanguage.length() == 2) {
            invocationBuilder.header("Accept-Language", acceptLanguage.toLowerCase());
        }

        if (xSource != null && !xSource.isEmpty()) {
            invocationBuilder.header("X-Source", xSource.toLowerCase());
        }

        switch (httpMethod) {
            case GET: {
                return invocationBuilder.async().get();
            }
            case POST: {
                return invocationBuilder.async().post(Entity.entity(data, MediaType.APPLICATION_JSON));
            }
            case PUT: {
                return invocationBuilder.async().put(Entity.entity(data, MediaType.APPLICATION_JSON));
            }
            default: {
                return null;
            }
        }
    }
}
