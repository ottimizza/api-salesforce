package br.com.ottimizza.salesforceclientapi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/salesforce/Particularidade__c")
public class SFParticularidadeController {

    @GetMapping // global controller
    public ResponseEntity<?> query(@RequestParam("q") String query, OAuth2Authentication authentication) {
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> upsert(@RequestParam("") String query, OAuth2Authentication authentication) {
        return ResponseEntity.ok().build();
    }

}
