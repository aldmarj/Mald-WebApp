package website;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import website.service.auth.ApiAuthenticationProvider;

@SpringBootConfiguration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter
{
    @Override
    protected void configure(final AuthenticationManagerBuilder auth)
    {
        auth.authenticationProvider(new ApiAuthenticationProvider());
    }
}
