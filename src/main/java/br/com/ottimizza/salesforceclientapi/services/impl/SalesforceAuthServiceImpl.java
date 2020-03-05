package br.com.ottimizza.salesforceclientapi.services.impl;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.ottimizza.salesforceclientapi.domain.salesforce.instance.SFInstanceProperties;
import br.com.ottimizza.salesforceclientapi.domain.salesforce.oauth.SFOAuth2Authentication;
import br.com.ottimizza.salesforceclientapi.domain.salesforce.oauth.SFOAuth2Properties;
import br.com.ottimizza.salesforceclientapi.domain.salesforce.oauth.jwt.SFOAuth2JWT;
import br.com.ottimizza.salesforceclientapi.domain.salesforce.oauth.jwt.SFOAuth2JWTClaim;
import br.com.ottimizza.salesforceclientapi.domain.salesforce.oauth.jwt.SFOAuth2JWTRequestBody;
import br.com.ottimizza.salesforceclientapi.services.SalesforceAuthService;

@Service
public class SalesforceAuthServiceImpl implements SalesforceAuthService {

    @Inject
    private SFInstanceProperties instanceProperties;

    @Inject
    private SFOAuth2Properties oauth2Properties;

    @Value("${salesforce.keystore.password}")
    private String password;

    @Override
    public SFOAuth2Authentication authorize() throws Exception {
        return requestAccessToken(instanceProperties.getSalesforceTokenEndpoint(), buildJWT());
    }

    private SFOAuth2JWT buildJWT() { // @formatter:off
        return SFOAuth2JWT.builder()
                .claims(
                    SFOAuth2JWTClaim.builder()
                        .iss(oauth2Properties.getClientId())
                        .sub(oauth2Properties.getUsername())
                        .aud("https://login.salesforce.com")
                        .exp(1333685628L)
                    .build()
                )
                .password(password)
            .build();
    }

    private SFOAuth2Authentication requestAccessToken(String url, SFOAuth2JWT jwt) throws Exception {
        RestTemplate template = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String body = SFOAuth2JWTRequestBody.fromSFOAuth2JWT(jwt).encoded();

        System.out.println();
        System.out.println();
        System.out.println("Body: " + body);
        System.out.println();
        System.out.println();

        HttpEntity<String> request = new HttpEntity<String>(
            body, headers
        );

        return template.postForObject(url, request, SFOAuth2Authentication.class);
    }

}
