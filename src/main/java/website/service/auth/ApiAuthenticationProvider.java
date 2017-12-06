package website.service.auth;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import website.service.BaseService;

@Component
public class ApiAuthenticationProvider implements AuthenticationProvider
{
    private final LoginService loginService;

    public ApiAuthenticationProvider()
    {
        this.loginService = new LoginService();
    }

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException
    {
        final String username = authentication.getName();
        final String password = authentication.getCredentials().toString();
        final String token = loginService.login("cibusinesstag",username, password);
        return null;
    }

    @Override
    public boolean supports(final Class<?> authentication)
    {
        return true;
    }
}
