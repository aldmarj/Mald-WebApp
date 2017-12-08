package website.service.auth;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;
import java.net.URI;
import java.util.Objects;

public class AuthenticationInterceptor implements ClientHttpRequestInterceptor
{
    @Override
    public ClientHttpResponse intercept(final HttpRequest request, final byte[] body, final ClientHttpRequestExecution execution) throws IOException
    {
        return execution.execute(new AuthenticatedRequest(request), body);
    }

    private static class AuthenticatedRequest extends HttpRequestWrapper
    {
        AuthenticatedRequest(final HttpRequest request)
        {
            super(request);
        }

        @Override
        public URI getURI()
        {
            final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth == null || auth.getCredentials() == null || auth.getCredentials().toString().isEmpty())
            {
                return super.getURI();
            }
            else
            {
                final String uriString = super.getURI().toString();
                final StringBuilder uriWithToken = new StringBuilder(uriString);
                uriWithToken.append(uriString.contains("?") ? '&' : '?');
                uriWithToken.append('t'); //todo externalise
                uriWithToken.append('=');
                uriWithToken.append(auth.getCredentials().toString());
                return URI.create(uriWithToken.toString());
            }
        }
    }
}
