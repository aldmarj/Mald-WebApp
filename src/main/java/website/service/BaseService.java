package website.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.client.RestTemplate;

/**
 * Should be extended by any service created in the app. <br/>
 * Provides a {@link RestTemplate} pre-configured with a rootUri. <br/>
 * See {@link website.SpringBootWebApplication} for the RestTemplate config
 */
public abstract class BaseService
{

    @Autowired
    private RestTemplate restTemplate;

    private static String appendLoginToken(final String url)
    {
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null)
        {
            return url;
        }
        else
        {
            String urlWithToken = url;
            urlWithToken += url.contains("?") ? "&" : "?";
            urlWithToken += "t=" + auth.getName();
            return urlWithToken;
        }

    }

    protected <T> T getForObject(final String uri, final Class<T> responseType)
    {
        return restTemplate.getForObject(appendLoginToken(uri), responseType);
    }

    protected <T> ResponseEntity<T> exchange(final String url, final HttpMethod method,
                                             final HttpEntity<?> requestEntity, final Class<T> responseType,
                                             final Object... uriVariables)
    {
        return restTemplate.exchange(appendLoginToken(url), method, requestEntity, responseType, uriVariables);
    }

}
