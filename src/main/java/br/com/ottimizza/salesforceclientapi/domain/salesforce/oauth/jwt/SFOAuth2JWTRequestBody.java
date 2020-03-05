package br.com.ottimizza.salesforceclientapi.domain.salesforce.oauth.jwt;

import java.io.Serializable;
import java.text.MessageFormat;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SFOAuth2JWTRequestBody implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("grant_type")
    private String grantType = "urn:ietf:params:oauth:grant-type:jwt-bearer";

    @JsonProperty("assertion")
    private String assertion;

    public static SFOAuth2JWTRequestBody fromSFOAuth2JWT(SFOAuth2JWT jwt, String password) throws Exception {
        SFOAuth2JWTRequestBody request = new SFOAuth2JWTRequestBody();
        request.setGrantType("urn:ietf:params:oauth:grant-type:jwt-bearer");
        request.setAssertion(jwt.buildSFJWTAccessToken(password));
        return request;
    }

    public String encoded() {
        return MessageFormat.format("grant_type={0}&assertion={1}", grantType, assertion);
    }

}