package br.com.ottimizza.salesforceclientapi.services;

import br.com.ottimizza.salesforceclientapi.domain.models.SFObject;
import br.com.ottimizza.salesforceclientapi.domain.salesforce.instance.SFInstanceProperties;
import br.com.ottimizza.salesforceclientapi.domain.salesforce.oauth.SFOAuth2Authentication;

public interface IGenericSFService <T extends SFObject> {

    public String create(String sName, T sObject, SFOAuth2Authentication sfAuth2Authentication, SFInstanceProperties sfInstanceProperties);

    // default public String create(String sName, T sObject, SFOAuth2Authentication sfAuth2Authentication, SFInstanceProperties sfInstanceProperties) {
    //     RestTemplate template = new RestTemplate();

    //     HttpHeaders headers = new HttpHeaders();
    //     headers.setContentType(MediaType.APPLICATION_JSON);
    //     headers.set("Authorization", "Bearer " + sfAuth2Authentication.getAccessToken());

    //     HttpEntity<T> request = new HttpEntity<T>(sObject, headers);

    //     String url = MessageFormat.format("{0}/sobjects/{1}", sfInstanceProperties.getSaleforceServiceUrl(), sName);

    //     System.out.println();
    //     System.out.println();
    //     System.out.println("URL : " + url.toString());
    //     System.out.println("Body: " + sObject.toString());
    //     System.out.println();
    //     System.out.println();

    //     return template.postForObject(url, request, String.class);
    // }
    
}