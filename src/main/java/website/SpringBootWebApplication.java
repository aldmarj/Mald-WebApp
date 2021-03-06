package website;

import java.util.Locale;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.client.RestTemplate;
import website.handlers.EMSResponseErrorHandler;
import website.service.auth.AuthenticationInterceptor;

@SpringBootApplication
public class SpringBootWebApplication
{

    /**
     * Value provided from application-{environment}.properties file
     */
    @Value("${api.rootUri}")
    private String apiRootUri;

    public static void main(String[] args)
    {
        SpringApplication.run(SpringBootWebApplication.class, args);
    }

    /**
     * Bean definition that returns a preconfigured RestTemplate.
     * Useful for @Autowire declarations, see {@link website.service.BaseService}
     */
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder)
    {
        return builder
                .rootUri(apiRootUri)
                .errorHandler(new EMSResponseErrorHandler())
                .additionalInterceptors(new AuthenticationInterceptor())
                .build();
    }

  @Bean
  public MessageSource messageSource() {
    Locale.setDefault(Locale.ENGLISH);
    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
    messageSource.addBasenames("classpath:messages");
    return messageSource;
  }

}
