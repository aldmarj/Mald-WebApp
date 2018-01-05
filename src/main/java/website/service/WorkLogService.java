package website.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import website.model.WorkLog;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
public class WorkLogService extends BaseService {
    public ResponseEntity<String> addWorkLog(String businessTag, WorkLog workLog) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<WorkLog> request = new HttpEntity<WorkLog>(workLog, headers);
        ResponseEntity<String> response =
                restTemplate.exchange("/business/{businessTag}/worklog", HttpMethod.POST,
                        request, String.class, businessTag);
        return response;
    }

    public Set<WorkLog> getWorklogs(String businessTag) {
        WorkLog[] response = restTemplate
                .getForObject("/business/{businessTag}/worklog/range/0/1",
                        WorkLog[].class,
                        businessTag
                );

        return new HashSet<>(Arrays.asList(response));
    }

}
