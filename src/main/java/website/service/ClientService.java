package website.service;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.stereotype.Service;
import website.model.Client;

@Service
public class ClientService extends BaseService {

  public Collection<Client> getClients(String businessTag) {
    Client[] response = restTemplate
        .getForObject("/business/{businessTag}/client/",
            Client[].class,
            businessTag
        );

    return Arrays.asList(response);
  }

}
