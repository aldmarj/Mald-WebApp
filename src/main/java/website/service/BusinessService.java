package website.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import website.model.Business;

import java.util.Arrays;
import java.util.Collection;

/**
 * Business service to support communications with the API.
 * 
 * @author Lawrence
 */
@Service
public class BusinessService extends BaseService
{
	/**
	 * Get all of the buisnesses stored in the EMS.
	 * 
	 * @return the list fo businesses.
	 */
    public Collection<Business> getBusinesses()
    {
        Business[] response = restTemplate
                .getForObject("/business/",
                        Business[].class
                );

        return Arrays.asList(response);
    }

    /**
     * Add a business to the API.
     * 
     * @param business - the business to add.
     * @return success string or error string if invalid.
     */
    public ResponseEntity<String> addBusiness(Business business)
    {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Business> request = new HttpEntity<Business>(business, headers);
        ResponseEntity<String> response =
                restTemplate.exchange("/business/", HttpMethod.POST,
                        request, String.class);
        return response;
    }
}
