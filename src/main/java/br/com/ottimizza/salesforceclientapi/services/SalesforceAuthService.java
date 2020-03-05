package br.com.ottimizza.salesforceclientapi.services;

import org.springframework.stereotype.Service;

import br.com.ottimizza.salesforceclientapi.domain.salesforce.oauth.SFOAuth2Authentication;

@Service
public interface SalesforceAuthService {

    public SFOAuth2Authentication authorize() throws Exception;

}
