package com.creatubbles.api.request.auth;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.JerseyWebTarget;

import com.creatubbles.api.APIVersion;
import com.creatubbles.api.CreatubblesAPI;
import com.creatubbles.api.core.CreatubblesRequest;
import com.creatubbles.api.response.auth.OAuthAccessTokenResponse;

import static com.creatubbles.api.util.EndPoints.OAUTH_TOKEN;

@APIVersion(2)
public class OAuthAccessTokenRequest extends CreatubblesRequest<OAuthAccessTokenResponse> {

    private final String clientId, clientSecret;
    private final String username, password;
    
    public OAuthAccessTokenRequest(String clientId, String clientSecret) {
        this(clientId, clientSecret, null, null);
    }

    public OAuthAccessTokenRequest(String clientId, String clientSecret, String username, String password) {
        super(null, null);
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.username = username;
        this.password = password;
    }

    @Override
    public Class<? extends OAuthAccessTokenResponse> getResponseClass() {
        return OAuthAccessTokenResponse.class;
    }

    @Override
    public CreatubblesRequest<OAuthAccessTokenResponse> execute() {
        resetResponse();
        String url = CreatubblesAPI.buildURL(OAUTH_TOKEN);

        JerseyWebTarget webTarget = CreatubblesAPI.CLIENT.target(url);
        Invocation.Builder invocationBuilder = webTarget
                .request(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_JSON);

        Form form;
        
        if (username != null && password != null) {
            form = new Form()
                .param("grant_type", "password")
                .param("client_id", clientId)
                .param("client_secret", clientSecret)
                .param("username", username)
                .param("password", password);
        } else {
            form = new Form()
                .param("grant_type", "client_credentials")
                .param("client_id", clientId)
                .param("client_secret", clientSecret);
        }
        
        setResponse(invocationBuilder.post(Entity.form(form)));
        return this;
    }
}
