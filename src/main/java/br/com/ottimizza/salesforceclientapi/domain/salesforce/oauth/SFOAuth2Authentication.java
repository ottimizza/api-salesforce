package br.com.ottimizza.salesforceclientapi.domain.salesforce.oauth;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SFOAuth2Authentication implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("token_type")
    private String tokenType;

    @JsonProperty("scope")
    private String scope;

    @JsonProperty("id_token")
    private String idToken;

    @JsonProperty("instance_url")
    private String instanceUrl;

    @JsonProperty("id")
    private String id;

    @JsonProperty("sfdc_community_url")
    private String sfdcCommunityUrl;

    @JsonProperty("sfdc_community_id")
    private String sfdcCommunityId;

}
