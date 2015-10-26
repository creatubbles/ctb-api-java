package com.creatubbles.api.request.auth;

import com.creatubbles.api.core.CreatubblesRequest;
import com.creatubbles.api.response.auth.SignInResponse;
import com.creatubbles.api.util.EndPoints;
import com.creatubbles.api.util.HttpMethod;

public class SignInRequest extends CreatubblesRequest<SignInResponse> {

    public SignInRequest(String email, String password) {
        super(EndPoints.SIGN_IN, HttpMethod.POST);
        setData("{\"user\" : { \"email\" : \"" + email + "\", \"password\" : \"" + password + "\" }}");
    }

    @Override
    public Class<? extends SignInResponse> getResponseClass() {
        return SignInResponse.class;
    }
}
