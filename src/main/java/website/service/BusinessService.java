package website.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;
import website.model.Business;

@Service
public class BusinessService extends BaseService
{
    @Autowired
    public BusinessService(final RestTemplate restTemplate)
    {
        super(restTemplate);
    }

    public Set<Business> getBusinesses()
    {
        Business[] response = this.getRestOperations()
                .getForObject("/business/",
                        Business[].class
                );

        return new HashSet<>(Arrays.asList(response));
    }

    public ResponseEntity<String> addBusiness(Business business)
    {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Business> request = new HttpEntity<Business>(business, headers);
        ResponseEntity<String> response =
                this.getRestOperations().exchange("/business/", HttpMethod.POST,
                        request, String.class);
        return response;
    }
}
