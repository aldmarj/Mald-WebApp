package website.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import website.service.auth.AuthenticatedRestTemplate;

/**
 * Should be extended by any service created in the app. <br/>
 * Provides a {@link RestTemplate} pre-configured with a rootUri. <br/>
 * See {@link website.SpringBootWebApplication} for the RestTemplate config
 */
@Service
public abstract class BaseService
{
    @Autowired
    private RestTemplate restTemplate;

    private AuthenticatedRestTemplate authenticatedRestTemplate;

    protected RestOperations getRestOperations()
    {
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null)
        {
            return restTemplate;
        }
        else
        {
            if (authenticatedRestTemplate == null)
            {
                authenticatedRestTemplate = new AuthenticatedRestTemplate(restTemplate);
            }
            return authenticatedRestTemplate;
        }
    }
}
