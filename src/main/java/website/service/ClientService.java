package website.service;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.stereotype.Service;
import website.model.Client;

@Service
public class ClientService extends BaseService {

  public ArrayList getClients(String businessTag) {
    Client[] response = restTemplate
        .getForObject("/business/{businessTag}/client/",
            Client[].class,
            businessTag
        );

    return new ArrayList(Arrays.asList(response));
  }

}
