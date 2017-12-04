package website.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import website.model.Business;

@Service
public class BusinessService extends BaseService
{

    public Set<Business> getBusinesses()
    {
        Business[] response = this
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
                this.exchange("/business/", HttpMethod.POST,
                        request, String.class);
        return response;
    }
}
