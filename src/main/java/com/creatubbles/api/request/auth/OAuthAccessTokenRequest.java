package com.creatubbles.api.request.auth;

import com.creatubbles.api.CreatubblesAPI;
import com.creatubbles.api.APIVersion;
import com.creatubbles.api.core.CreatubblesRequest;
import com.creatubbles.api.response.auth.OAuthAccessTokenResponse;
import org.glassfish.jersey.client.JerseyWebTarget;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

import static com.creatubbles.api.util.EndPoints.OAUTH_TOKEN;
import static com.creatubbles.api.util.OAuthUtil.CLIENT_ID;
import static com.creatubbles.api.util.OAuthUtil.CLIENT_SECRET;

@APIVersion(2)
public class OAuthAccessTokenRequest extends CreatubblesRequest<OAuthAccessTokenResponse> {

    private String username;
    private String password;

    public OAuthAccessTokenRequest(String username, String password) {
        super(null, null);
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

        Form form = new Form() //
                .param("grant_type", "password") //
                .param("client_id", CLIENT_ID) //
                .param("client_secret", CLIENT_SECRET) //
                .param("username", username) //
                .param("password", password);
        setResponse(invocationBuilder.post(Entity.form(form)));
        return this;
    }
}
