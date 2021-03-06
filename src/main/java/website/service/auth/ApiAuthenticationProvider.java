package website.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * Authentication Provider that takes a {@link UsernamePasswordAuthenticationToken}
 * and authenticates it by sending the details to the API server.
 *
 * @author Matt
 */
@Component
public class ApiAuthenticationProvider implements AuthenticationProvider
{
    private final LoginService loginService;

    @Autowired
    public ApiAuthenticationProvider(final LoginService loginService)
    {
        this.loginService = loginService;
    }

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException
    {
        final String username = authentication.getName();
        final String password = authentication.getCredentials().toString();
        final String business = authentication.getDetails().toString();
        final String token = loginService.login(business, username, password);
        if (token == null || token.isEmpty())
        {
            throw new BadCredentialsException("username or password are incorrect");
        }
        else
        {
            final AbstractAuthenticationToken newAuth =
                    new UsernamePasswordAuthenticationToken(
                            username,
                            token,
                            Collections.singleton(new SimpleGrantedAuthority("ROLE_" + business)));
            newAuth.setDetails(business);
            return newAuth;
        }
    }

    @Override
    public boolean supports(final Class<?> authentication)
    {
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }
}
