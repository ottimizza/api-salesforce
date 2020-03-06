package br.com.ottimizza.salesforceclientapi.domain.salesforce.instance;

import java.text.MessageFormat;

import javax.validation.constraints.NotBlank;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "salesforce.instance")
public class SFInstanceProperties {

    @NotBlank
    @Getter @Setter
    private String apiVersion; // v20.0

    @NotBlank
    @Getter @Setter
    private String instanceId; // ottimizza.my

    public String getSalesforceTokenEndpoint() {
        return MessageFormat.format("https://{0}.salesforce.com/services/oauth2/token", this.instanceId);
    }

    public String getSaleforceServiceUrl() {
        return MessageFormat.format("https://{0}.salesforce.com/services/data/{1}", this.instanceId, this.apiVersion);
    }
    public String buildServiceUrl(String path, Object... args) {
        return MessageFormat.format(this.getSaleforceServiceUrl() + path, args);
    }
    public String buildServiceUrl(String path) {
        return this.getSaleforceServiceUrl() + path;
    }
    public String buildUrlFromDomain(String path) {
        return MessageFormat.format("https://{0}.salesforce.com", this.instanceId) + path;
    }

}
