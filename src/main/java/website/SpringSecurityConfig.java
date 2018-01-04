package website;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import website.service.auth.BusinessAccessVoter;
import website.service.auth.BusinessAuthFailureHandler;
import website.service.auth.BusinessLoginUrlAuthenticationEntryPoint;
import website.utils.BusinessTagUtils;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter
{
    private static final String[] PERMITTED_URLS = new String[]
    {
        "/",
        "/webjars/**",
        "/*/login",
        "/error",
        "/health"
    };

    private static final String[] SHARED_BUSINESSES = new String[]
    {
        "",
        "webjars",
        "error",
        "health"
    };

    @Autowired
    private AuthenticationProvider authProvider;

    @Override
    protected void configure(final HttpSecurity http) throws Exception
    {
        http.csrf().disable() //TODO reenable after testing
                .authorizeRequests()
                    .antMatchers(PERMITTED_URLS).permitAll()
                    .anyRequest().authenticated()
                    .accessDecisionManager(this.createAccessDecisionManager())
                    .and()
                .formLogin()
                    .authenticationDetailsSource(r -> BusinessTagUtils.getBusinessTag(r.getRequestURI()))
                    .defaultSuccessUrl("/",false)
                    .failureHandler(new BusinessAuthFailureHandler())
                    .loginProcessingUrl("/*/login")
                    .permitAll()
                    .and()
                .logout()
                    .permitAll()
                    .and()
                .exceptionHandling()
                    .authenticationEntryPoint(new BusinessLoginUrlAuthenticationEntryPoint("/login"));
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth)
    {
        auth.authenticationProvider(authProvider).eraseCredentials(false);
    }

    private AccessDecisionManager createAccessDecisionManager()
    {
        return new UnanimousBased(Arrays.asList(
                new WebExpressionVoter(),
                new RoleVoter(),
                new AuthenticatedVoter(),
                new BusinessAccessVoter(SHARED_BUSINESSES)));
    }
}
