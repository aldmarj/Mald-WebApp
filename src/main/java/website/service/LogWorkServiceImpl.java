package website.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import website.model.Client;

@Service
public class LogWorkServiceImpl extends BaseService implements LogWorkService {

  @Override
  public Set<Client> getClients(String businessTag) {
    Client[] response = restTemplate
        .getForObject("/business/{businessTag}/clientId/",
            Client[].class,
            businessTag
        );

    return new HashSet<>(Arrays.asList(response));
  }

}
