package br.com.ottimizza.salesforceclientapi.domain.salesforce.oauth;

import javax.validation.constraints.NotBlank;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "salesforce.oauth2")
public class SFOAuth2Properties {

    @NotBlank
    @Getter @Setter
    private String clientId; 

    @NotBlank
    @Getter @Setter
    private String clientSecret;

    @NotBlank
    @Getter @Setter
    private String username;

    @NotBlank
    @Getter @Setter
    private String password;

}
