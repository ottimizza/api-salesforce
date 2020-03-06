package br.com.ottimizza.salesforceclientapi.services.impl;

import java.text.MessageFormat;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import br.com.ottimizza.salesforceclientapi.domain.models.SFObject;
import br.com.ottimizza.salesforceclientapi.domain.salesforce.instance.SFInstanceProperties;
import br.com.ottimizza.salesforceclientapi.domain.salesforce.oauth.SFOAuth2Authentication;
import br.com.ottimizza.salesforceclientapi.services.IGenericSFService;

public class GenericSFService <T extends SFObject> implements IGenericSFService<T> {

    @Override
    public String create(String sName, T sObject, SFOAuth2Authentication sfAuth2Authentication, SFInstanceProperties sfInstanceProperties) {
        RestTemplate template = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + sfAuth2Authentication.getAccessToken());

        HttpEntity<T> request = new HttpEntity<T>(sObject, headers);

        String url = MessageFormat.format("{0}/sobjects/{1}", sfInstanceProperties.getSaleforceServiceUrl(), sName);

        return template.postForObject(url, request, String.class);
    }

    @Override
    public String patch(String sName, T sObject, SFOAuth2Authentication sfAuth2Authentication, SFInstanceProperties sfInstanceProperties) {
        RestTemplate template = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + sfAuth2Authentication.getAccessToken());

        HttpEntity<T> request = new HttpEntity<T>(sObject, headers);

        String url = MessageFormat.format("{0}/sobjects/{1}", sfInstanceProperties.getSaleforceServiceUrl(), sName);

        return template.patchForObject(url, request, String.class);
    }

}