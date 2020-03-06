package br.com.ottimizza.salesforceclientapi.services;

import br.com.ottimizza.salesforceclientapi.domain.models.SFObject;
import br.com.ottimizza.salesforceclientapi.domain.salesforce.instance.SFInstanceProperties;
import br.com.ottimizza.salesforceclientapi.domain.salesforce.oauth.SFOAuth2Authentication;

public interface IGenericSFService <T extends SFObject> {

    public String create(String sName, T sObject, 
                         SFOAuth2Authentication sfAuth2Authentication, 
                         SFInstanceProperties sfInstanceProperties);
    
    public String patch(String sName, T sObject, 
                        SFOAuth2Authentication sfAuth2Authentication, 
                        SFInstanceProperties sfInstanceProperties);
    
}