package br.com.ottimizza.salesforceclientapi.configurations;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

@Configuration // @formatter:off
@EnableOAuth2Client
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${oauth2-config.server-url}")
    private String OAUTH2_SERVER_URL;

    @Value("${oauth2-config.client-id}")
    private String OAUTH2_CLIENT_ID;

    @Value("${oauth2-config.client-secret}")
    private String OAUTH2_CLIENT_SECRET;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String[] _allowed = Arrays.asList(new String[] {
                "/", "/oauth/**", "/auth/**",
        }).toArray(new String[] {});

         String[] _protected = Arrays.asList(new String[] {
                "/api/**"
        }).toArray(new String[] {});

        http
            .authorizeRequests()
                .antMatchers(_allowed).permitAll();

        http
            .csrf().disable()
            .authorizeRequests()
                .antMatchers(_protected).permitAll()
                .anyRequest().authenticated();
    }

    /**
     * The heart of our interaction with the resource; handles redirection for
     * authentication, access tokens, etc.
     *
     * @param oauth2ClientContext
     * @return
     */

    @Bean
    @Primary
    public ResourceServerTokenServices tokenServices() {
        RemoteTokenServices tokenServices = new RemoteTokenServices();

        final String checkTokenEndpointUrl = OAUTH2_SERVER_URL + "/oauth/check_token";

        tokenServices.setClientId(OAUTH2_CLIENT_ID);
        tokenServices.setClientSecret(OAUTH2_CLIENT_SECRET);
        tokenServices.setCheckTokenEndpointUrl(checkTokenEndpointUrl);

        return tokenServices;
    }

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        OAuth2AuthenticationManager authenticationManager = new OAuth2AuthenticationManager();
        authenticationManager.setTokenServices(tokenServices());
        return authenticationManager;
    }

}