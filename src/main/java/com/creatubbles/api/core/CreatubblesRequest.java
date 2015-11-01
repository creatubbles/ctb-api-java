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
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public abstract class CreatubblesRequest<T extends CreatubblesResponse> {
    private String endPoint, acceptLanguage, xSource, data;
    private HttpMethod httpMethod;
    private Map<String, String> urlParameters;
    private Response response;
    private Future<Response> futureResponse;
    private T responseCache;

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

    public CreatubblesRequest<T> setAccessToken(String accessToken) {
        this.urlParameters.put("access_token", accessToken);
        return this;
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

    public String getXSource() {
        return xSource;
    }

    public CreatubblesRequest<T> setXSource(String xSource) {
        this.xSource = xSource;
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

    private void resetResponse() {
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
            return getRawResponse().getStatus() == 200;
        }
        return false;
    }

    public void cancelRequest() {
        if (futureResponse != null & !futureResponse.isDone()) {
            futureResponse.cancel(true);
        }
    }

    public Response getRawResponse() {
        if (response == null && futureResponse.isDone()) {
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

    public T getResponse() {
        if (responseCache == null) {
            Response response = getRawResponse();
            Class<? extends T> responseClass = getResponseClass();
            if (response != null && responseClass != null) {
                T creatubblesResponse = CreatubblesAPI.GSON.fromJson(response.readEntity(String.class), responseClass);
                creatubblesResponse.setOriginatingRequest(this);
                responseCache = creatubblesResponse;
            }
        }
        return responseCache;
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

        Invocation.Builder invocationBuilder = webTarget
                .request(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        if (acceptLanguage != null && acceptLanguage.length() == 2) {
            invocationBuilder.header("Accept-Language", acceptLanguage.toLowerCase());
        }

        if (xSource != null && !xSource.isEmpty()) {
            invocationBuilder.header("X-Source", xSource.toLowerCase());
        }

        if (httpMethod == HttpMethod.GET) {
            response = invocationBuilder.get();
        } else if (httpMethod == HttpMethod.POST) {
            response = invocationBuilder.post(Entity.entity(data, MediaType.APPLICATION_JSON));
        } else if (httpMethod == HttpMethod.PUT) {
            response = invocationBuilder.put(Entity.entity(data, MediaType.APPLICATION_JSON));
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

        Invocation.Builder invocationBuilder = webTarget
                .request(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        if (acceptLanguage != null && acceptLanguage.length() == 2) {
            invocationBuilder.header("Accept-Language", acceptLanguage.toLowerCase());
        }

        if (xSource != null && !xSource.isEmpty()) {
            invocationBuilder.header("X-Source", xSource.toLowerCase());
        }

        if (httpMethod == HttpMethod.GET) {
            futureResponse = invocationBuilder.async().get();
        } else if (httpMethod == HttpMethod.POST) {
            futureResponse = invocationBuilder.async().post(Entity.entity(data, MediaType.APPLICATION_JSON));
        } else if (httpMethod == HttpMethod.PUT) {
            futureResponse = invocationBuilder.async().put(Entity.entity(data, MediaType.APPLICATION_JSON));
        }

        return this;
    }
}
