package website.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

/**
 * Should be extended by any service created in the app. <br/>
 * Provides a {@link RestTemplate} pre-configured with a rootUri. <br/>
 * See {@link ClientService} for example subclass implementation <br/>
 * See {@link website.SpringBootWebApplication} for the RestTemplate config
 */
public abstract class BaseService {

  @Autowired
  protected RestTemplate restTemplate;

}
