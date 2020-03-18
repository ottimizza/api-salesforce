package br.com.ottimizza.salesforceclientapi.controllers;

import br.com.ottimizza.salesforceclientapi.constraints.MethodExecution;
import javax.inject.Inject;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ottimizza.salesforceclientapi.services.SalesforceService;

@RestController
@RequestMapping("/api/v1/salesforce")
public class SalesforceController {

    @Inject
    SalesforceService salesforceService;

    @PostMapping("/composite/tree/{objectId}")
    public ResponseEntity<?> createMultipleRecords(@PathVariable("objectId") String objectId, 
                                    @RequestBody String object,
                                    OAuth2Authentication authentication) throws Exception {
        return ResponseEntity.ok(salesforceService.insertMultiple(objectId, object));
    }

    @GetMapping("/sobjects/{objectId}/{salesforceId}")
    public ResponseEntity<?> fetchBySalesforceId(@PathVariable("objectId") String objectId,
                                    @PathVariable("salesforceId") String salesforceId,
                                    OAuth2Authentication authentication) throws Exception {
        return ResponseEntity.ok(salesforceService.fetchBySalesforceId(objectId, salesforceId));
    }

    @GetMapping("/sobjects/{objectId}/{externalIdName}/{externalId}")
    public ResponseEntity<?> fetchByExternalId(@PathVariable("objectId") String objectId,
                                    @PathVariable("externalIdName") String externalIdName, 
                                    @PathVariable("externalId") String externalId,
                                    OAuth2Authentication authentication) throws Exception {
        return ResponseEntity.ok(salesforceService.fetchByExternalId(objectId, externalIdName, externalId));
    }

    @PostMapping("/sobjects/{objectId}")
    public ResponseEntity<?> create(@PathVariable("objectId") String objectId, 
                                    @RequestBody String object,
                                    OAuth2Authentication authentication) throws Exception {
        return ResponseEntity.ok(salesforceService.insert(objectId, object));
    }

    @PatchMapping("/sobjects/{objectId}/{externalIdName}/{externalId}")
    public ResponseEntity<?> upsert(@PathVariable("objectId") String objectId,
                                    @PathVariable("externalIdName") String externalIdName, 
                                    @PathVariable("externalId") String externalId,
                                    @RequestBody String object,
                                    OAuth2Authentication authentication) throws Exception {
        return ResponseEntity.ok(salesforceService.upsert(objectId, externalIdName, externalId, object));
    }
    
    @PostMapping("/sobjects/{objectId}/{externalIdName}/{externalId}")
    public ResponseEntity<?> postUpsert(@PathVariable("objectId") String objectId,
                                    @PathVariable("externalIdName") String externalIdName, 
                                    @PathVariable("externalId") String externalId,
                                    @RequestBody String object,
                                    OAuth2Authentication authentication) throws Exception {
        return ResponseEntity.ok(salesforceService.upsert(objectId, externalIdName, externalId, object));
    }

    @GetMapping("/resolve_url")
    public ResponseEntity<?> resolveURL(@RequestParam("url") String url,
                                    OAuth2Authentication authentication) throws Exception {
        return ResponseEntity.ok(salesforceService.resolveURL(url));
    }

    @GetMapping("/execute_soql")
    public ResponseEntity<?> executeSOQL(@RequestParam("soql") String soql,
                                         @RequestParam(defaultValue = "1", required = false) int methodExecution,
                                    OAuth2Authentication authentication) throws Exception {
        return ResponseEntity.ok(salesforceService.executeSOQL(soql, methodExecution));
    }

}
