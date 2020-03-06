package br.com.ottimizza.salesforceclientapi.controllers;

import javax.inject.Inject;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ottimizza.salesforceclientapi.domain.models.SFParticularidade;
import br.com.ottimizza.salesforceclientapi.domain.salesforce.instance.SFInstanceProperties;
import br.com.ottimizza.salesforceclientapi.services.SFParticularidadeService;
import br.com.ottimizza.salesforceclientapi.services.SalesforceAuthService;

@RestController
@RequestMapping("/api/v1/salesforce/Roteiros_vs_Contas__c")
public class SFParticularidadeController {

    @Inject
    SFInstanceProperties sfInstanceProperteies;

    @Inject 
    SalesforceAuthService sfAuthService;

    @Inject
    SFParticularidadeService sfParticularidadeService;

    @GetMapping // global controller
    public ResponseEntity<?> query(@RequestParam("q") String query, OAuth2Authentication authentication) {
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<?> upsert(@RequestBody SFParticularidade sfParticularidade, OAuth2Authentication authentication) 
            throws Exception {
        return ResponseEntity.ok(sfParticularidadeService.create(
            SFParticularidade.S_NAME, sfParticularidade, sfAuthService.authorize(), sfInstanceProperteies
        ));
    }

}
