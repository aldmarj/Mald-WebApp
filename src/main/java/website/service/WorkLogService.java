package website.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import website.model.WorkLog;
import website.utils.DateUtils;

import java.util.Arrays;
import java.util.Collection;

/**
 * Employee services for talking to the API regarding worklogs.
 * 
 * @author Lawrence
 */
@Service
public class WorkLogService extends BaseService {
	
	/**
	 * Add a worklog to the API.
	 * 
	 * @param businessTag - The business context.
	 * @param workLog - The worklog to add.
	 * @return the responce.
	 */
    public ResponseEntity<String> addWorkLog(String businessTag, WorkLog workLog) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<WorkLog> request = new HttpEntity<WorkLog>(workLog, headers);
        ResponseEntity<String> response =
                restTemplate.exchange("/business/{businessTag}/worklog", HttpMethod.POST,
                        request, String.class, businessTag);
        return response;
    }

    /**
     * Get all the worklogs for the current week.
     * 
     * @param businessTag - The business context.
     * @return the worklogs.
     */
    public Collection<WorkLog> getWorklogs(String businessTag) {
    	long startDay = DateUtils.getStartOfCurrentDayInMillis();
    	long endDay = DateUtils.getEndOfCurrentDayInMillis();
    	
        WorkLog[] response = restTemplate
                .getForObject("/business/{businessTag}/worklog/range/{startDay}/{endDay}",
                        WorkLog[].class,
                        businessTag,
                        startDay,
                        endDay
                );

        return Arrays.asList(response);
    }

}
