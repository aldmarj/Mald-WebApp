package website.service;

import org.springframework.stereotype.Service;
import website.model.Employee;

import java.util.ArrayList;
import java.util.Arrays;

@Service
public class EmployeeService extends BaseService {
    public ArrayList getTopEmployees(String businessTag) {
        Employee[] response = restTemplate
                .getForObject("/business/{businessTag}/employee/mostWorked/top/1/10/between/10/20",
                        Employee[].class,
                        businessTag
                );

        return new ArrayList(Arrays.asList(response));
    }
}
