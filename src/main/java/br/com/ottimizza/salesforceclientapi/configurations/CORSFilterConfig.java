package br.com.ottimizza.salesforceclientapi.configurations;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * CORS (Cross-Origin Resource Sharing).
 * 
 * Classe responsável pela configuração de disponibilidade dos recursos a
 * clientes HTTP. Assim todos os recursos se tornam disponíveis a qualquer
 * origem.
 */
@Configuration
public class CORSFilterConfig {

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        // Classe responsável pela Configuração do CORS no sistema.
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);

        // Permitimos requisições de qualquer origem ou cliente.
        config.addAllowedOrigin("*");

        // Permitimos requisições com qualquer Header no request.
        config.addAllowedHeader("*");

        // Permitimos todos os tipos de requisições (GET, POST, PUT, OPTIONS...)
        config.addAllowedMethod("*");

        // Aplicamos as configurações acima para todos os recursos HTTP a partir da URL
        // base (/**).
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        // Criamos um Bean expondo essas propriedades de configuração de CORS.
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);

        return bean;
    }

}
