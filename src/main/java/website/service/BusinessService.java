package website.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Service;

import website.model.Business;

@Service
public class BusinessService extends BaseService {

  public Set<Business> getBusinesses() {
	  Business[] response = restTemplate
        .getForObject("/business/",
        	Business[].class
    );

    return new HashSet<>(Arrays.asList(response));
  }

}
