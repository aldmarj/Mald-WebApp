package website;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import website.service.auth.ApiAuthenticationProvider;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter
{

    @Autowired
    private AuthenticationProvider authProvider;

    @Override
    protected void configure(final HttpSecurity http) throws Exception
    {
        http.csrf().disable() //TODO reenable after testing
                .authorizeRequests()
                    .antMatchers("/","/businesses", "/webjars/**").anonymous()
                    .anyRequest().authenticated()
                    .and()
                .formLogin().loginPage("/").loginProcessingUrl("/*/login").permitAll()
                .and().logout().permitAll();
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth)
    {
        auth.authenticationProvider(authProvider);
    }
}
