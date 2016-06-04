package com.creatubbles.api.core;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.Response;

import jersey.repackaged.com.google.common.base.Throwables;
import jersey.repackaged.com.google.common.collect.Lists;

import org.glassfish.jersey.client.JerseyWebTarget;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import com.creatubbles.api.CreatubblesAPI;
import com.creatubbles.api.response.ArrayResponse;
import com.creatubbles.api.util.HttpMethod;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

public abstract class CreatubblesRequest<T extends CreatubblesResponse> {

    private String endPoint, acceptLanguage, data;
    private HttpMethod httpMethod;
    private Map<String, String> urlParameters;
    private Response response;
    private Future<Response> futureResponse;

    private T responseCache;
    private T[] responseArrayCache;
    private JsonObject metaCache;
    
    private String accessToken;
    private static final String EMPTY_RESPONSE = "{}";
    private static final String APPLICATION_VND_API_JSON = "application/vnd.api+json";

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
        this.accessToken = accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public CreatubblesRequest<T> setEndPoint(String endPoint) {
        this.endPoint = endPoint;
        return this;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public CreatubblesRequest<T> setHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
        return this;
    }

    public String getUrlParameter(String key) {
        return urlParameters.get(key);
    }

    public String getAcceptLanguage() {
        return acceptLanguage;
    }

    public CreatubblesRequest<T> setAcceptLanguage(String acceptLanguage) {
        this.acceptLanguage = acceptLanguage;
        return this;
    }

    public String getData() {
        return data;
    }

    public CreatubblesRequest<T> setData(String data) {
        this.data = data;
        return this;
    }

    public CreatubblesRequest<T> setUrlParameter(String key, String value) {
        this.urlParameters.put(key, value);
        return this;
    }

    public abstract Class<? extends T> getResponseClass();

    public void resetResponse() {
        if (response != null || futureResponse != null) {
            response = null;
            futureResponse = null;
        }
    }

    public boolean isDone() {
        return ((response != null) || (futureResponse != null && futureResponse.isDone()));
    }

    public boolean wasSuccessful() {
        if (isDone()) {
            return isSuccessStatus(getRawResponse());
        }
        return false;
    }

    private boolean isSuccessStatus(Response response) {
        return isSuccessStatusCode(response.getStatus());
    }

    public boolean isSuccessStatusCode(int status) {
        return status == 200 || status == 204;
    }

    public void cancelRequest() {
        if (futureResponse != null & !futureResponse.isDone()) {
            futureResponse.cancel(true);
        }
    }

    public Response getRawResponse() {
        if (response == null && futureResponse != null && futureResponse.isDone()) {
            try {
                response = futureResponse.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        return response;
    }

    @SuppressWarnings("unchecked")
    private void initResponse() {
        if (responseCache == null && responseArrayCache == null) {
            Response response = getRawResponse();
            Class<? extends T> responseClass = getResponseClass();
            if (response != null && responseClass != null) {
                String json = response.readEntity(String.class);
                if (isSuccessStatus(response) && json.isEmpty()) {
                    json = EMPTY_RESPONSE;
                } else if (!isSuccessStatus(response)) {
                    responseCache = createDefaultResponse(json);
                } else {
                    try {
                        JsonObject jsonObj = CreatubblesAPI.GSON.fromJson(json, JsonObject.class);
                        if (isArrayResponse()) {
                            initResponseArray(jsonObj);
                        } else {
                            initResponseSingle(jsonObj);
                        }
                        if (jsonObj.has("meta")) {
                            metaCache = jsonObj.get("meta").getAsJsonObject();
                        }
                    } catch (JsonSyntaxException e) { // protect against invalid API returns (for now)
                        System.err.println("Invalid JSON: " + json);
                        e.printStackTrace();
                        boolean isArray = isArrayResponse();
                        if (isArray && responseArrayCache == null) {
                            responseArrayCache = (T[]) Array.newInstance(getResponseClass(), 1);
                            responseArrayCache[0] = createDefaultResponse(json);
                        } else if (!isArray && responseCache == null) {
                            responseCache = createDefaultResponse(json);
                        }
                    }
                }
            }
        }
    }

    private void initResponseSingle(JsonObject json) {
        responseCache = CreatubblesAPI.GSON.fromJson(json, getResponseClass());
        updateResponse(responseCache, json);
    }

    @SuppressWarnings("unchecked")
    private void initResponseArray(JsonObject json) {
        responseArrayCache = (T[]) CreatubblesAPI.GSON.fromJson(json, Array.newInstance(getResponseClass(), 0).getClass());
        for (int i = 0; i < responseArrayCache.length; i++) {
            updateResponse(responseArrayCache[i], json);
        }
    }

    private void updateResponse(T resp, JsonObject root) {
        resp.handleId(resp.getId());
        resp.setOriginatingRequest(this);
    }

    private T createDefaultResponse(String json) {
        try {
            T resp = getResponseClass().newInstance();
            resp.setMessage(json);
            return resp;
        } catch (Exception e) {
            throw Throwables.propagate(e);
        }
    }
    
    public final boolean isArrayResponse() {
        return getResponseClass().isAnnotationPresent(ArrayResponse.class);
    }

    /**
     * @return A cached version of the deserialized response object.
     *         <p>
     *         {@code null} if the response <i>is</i> marked with {@link ArrayResponse}.
     */
    public final T getResponse() {
        initResponse();
        return responseCache;
    }

    /**
     * @return An unmodifiable view of the array response as a list.
     *         <p>
     *         {@code null} if the response is not marked with {@link ArrayResponse}.
     */
    public final List<T> getResponseList() {
        initResponse();
        return responseArrayCache == null ? Lists.<T>newArrayList() : Arrays.asList(responseArrayCache);
    }
    
    public final <M> M getMetadata(Class<M> metaClass) {
        return CreatubblesAPI.GSON.fromJson(metaCache, metaClass);
    }

    public CreatubblesRequest<T> execute() {
        resetResponse();
        String url = CreatubblesAPI.buildURL(endPoint);

        JerseyWebTarget webTarget = CreatubblesAPI.CLIENT.target(url);
        for (String paramKey : urlParameters.keySet()) {
            String paramValue = urlParameters.get(paramKey);
            if (paramValue != null && !paramValue.isEmpty()) {
                webTarget = webTarget.queryParam(paramKey, paramValue);
            }
        }

        if (CreatubblesAPI.stagingModeEnabled()) {
            HttpAuthenticationFeature basicAuth = HttpAuthenticationFeature.basic("c", "c");
            webTarget.register(basicAuth);
        }

        Invocation.Builder invocationBuilder = webTarget.request(APPLICATION_VND_API_JSON).accept(APPLICATION_VND_API_JSON);

        if (acceptLanguage != null && acceptLanguage.length() == 2) {
            invocationBuilder.header("Accept-Language", acceptLanguage.toLowerCase());
        }
        if (accessToken != null && !accessToken.isEmpty()) {
            invocationBuilder.header("Authorization", "Bearer " + accessToken);
        }

        if (httpMethod == HttpMethod.GET) {
            response = invocationBuilder.get();
        } else if (httpMethod == HttpMethod.POST) {
            response = invocationBuilder.post(Entity.entity(data, APPLICATION_VND_API_JSON));
        } else if (httpMethod == HttpMethod.PUT) {
            response = invocationBuilder.put(Entity.entity(data, APPLICATION_VND_API_JSON));
        }

        return this;
    }

    public CreatubblesRequest<T> async() {
        resetResponse();
        String url = CreatubblesAPI.buildURL(endPoint);

        JerseyWebTarget webTarget = CreatubblesAPI.CLIENT.target(url);
        for (String paramKey : urlParameters.keySet()) {
            String paramValue = urlParameters.get(paramKey);
            if (paramValue != null && !paramValue.isEmpty()) {
                webTarget = webTarget.queryParam(paramKey, paramValue);
            }
        }

        Invocation.Builder invocationBuilder = webTarget.request(APPLICATION_VND_API_JSON).accept(APPLICATION_VND_API_JSON);

        if (acceptLanguage != null && acceptLanguage.length() == 2) {
            invocationBuilder.header("Accept-Language", acceptLanguage.toLowerCase());
        }
        if (accessToken != null && !accessToken.isEmpty()) {
            invocationBuilder.header("Authorization", "Bearer " + accessToken);
        }

        if (httpMethod == HttpMethod.GET) {
            futureResponse = invocationBuilder.async().get();
        } else if (httpMethod == HttpMethod.POST) {
            futureResponse = invocationBuilder.async().post(Entity.entity(data, APPLICATION_VND_API_JSON));
        } else if (httpMethod == HttpMethod.PUT) {
            futureResponse = invocationBuilder.async().put(Entity.entity(data, APPLICATION_VND_API_JSON));
        }

        return this;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public void setResponseCache(T responseCache) {
        this.responseCache = responseCache;
    }
}
