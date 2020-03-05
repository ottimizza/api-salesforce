package br.com.ottimizza.salesforceclientapi.controllers;

import javax.inject.Inject;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ottimizza.salesforceclientapi.services.SalesforceAuthService;

@RestController
@RequestMapping("/api/v1/salesforce/oauth")
public class SFOAuth2Controller {

    @Inject
    SalesforceAuthService salesforceAuthService;

    @GetMapping // global controller
    public ResponseEntity<?> query(OAuth2Authentication authentication) throws Exception {
        return ResponseEntity.ok(salesforceAuthService.authorize());
    }


}