package br.com.ottimizza.salesforceclientapi.services;

import org.springframework.stereotype.Service;

import br.com.ottimizza.salesforceclientapi.domain.salesforce.oauth.SFOAuth2Authentication;

@Service
public interface SalesforceAuthService {

    // @Inject 
    // private SFInstanceDetails;

    // @Inject
    // private SFOAuth2AppDetails;

    // SFOAuth2Authentication
    SFOAuth2Authentication authorize() throws Exception;

}
