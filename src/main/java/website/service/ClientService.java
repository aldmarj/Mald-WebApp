package website.service;

import java.util.Arrays;
import java.util.Collection;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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

  public ResponseEntity<String> addClient(String businessTag, Client client) {
    HttpHeaders headers = new HttpHeaders();
    HttpEntity<Client> request = new HttpEntity<Client>(client, headers);
    ResponseEntity<String> response =
        restTemplate.exchange("/business/{businessTag}/client", HttpMethod.POST,
            request, String.class, businessTag);
    return response;
  }
}
