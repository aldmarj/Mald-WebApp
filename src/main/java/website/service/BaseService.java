package website.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

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

    protected RestOperations getRestOperations()
    {
        return this.restTemplate;
    }
}
