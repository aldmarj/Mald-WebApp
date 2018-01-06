package website.service;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.stereotype.Service;
import website.model.Client;
import website.utils.DateUtils;

/**
 * Client services for talking to the API regarding employees.
 */
@Service
public class ClientService extends BaseService {

	/**
	 * Get all of the clients for the business context.
	 * 
	 * @return a list of all the clients for a business.
	 */
  public Collection<Client> getClients(String businessTag) {
    Client[] response = restTemplate
        .getForObject("/business/{businessTag}/client/",
            Client[].class,
            businessTag
        );

    return Arrays.asList(response);
  }

  /**
   * Get the employees who have worked the most for a time frame.
   * 
   * @param businessTag - The business context,
   * @return the employees of have worked the most for a time frame. Most first.
   */
  public Collection<Client> getTopNClientsForCurrentMonth(String businessTag, int n) {    	
  	long startMonth = DateUtils.getStartOfCurrentMonthInMillis();
  	long endMonth = DateUtils.getEndOfCurrentMonthInMillis();
  	
  	Client[] response = restTemplate
              .getForObject("/business/{businessTag}/client/mostWorked/top/1/{n}/between/{startMonth}/{endMonth}",
            		  Client[].class,
                      businessTag,
                      n,
                      startMonth,
                      endMonth
              );

      return Arrays.asList(response);
  }
}
