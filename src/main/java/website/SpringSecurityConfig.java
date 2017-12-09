package website;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
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
import java.util.List;

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
                    .antMatchers("/","/businesses", "/webjars/**", "/*/login").permitAll()
                    .anyRequest().authenticated()
                    .accessDecisionManager(this.createAccessDecisionManager())
                    .and()
                .formLogin()
                    .authenticationDetailsSource(r -> BusinessTagUtils.getBusinessTag(r.getRequestURI()))
                    .defaultSuccessUrl("/businesses",false)
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
        final List<AccessDecisionVoter<?>> decisionVoters = Arrays.asList(
                new WebExpressionVoter(),
                new RoleVoter(),
                new AuthenticatedVoter(),
                new BusinessAccessVoter("","businesses", "webjars"));
        return new UnanimousBased(decisionVoters);
    }
}
