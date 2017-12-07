package website.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Service;
import website.model.Client;

@Service
public class ClientService extends BaseService {

  public Set<Client> getClients(String businessTag) {
    Client[] response = restTemplate
        .getForObject("/business/{businessTag}/client/",
            Client[].class,
            businessTag
        );

    return new HashSet<>(Arrays.asList(response));
  }

}
