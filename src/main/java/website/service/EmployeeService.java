package website.service;

import org.springframework.stereotype.Service;
import website.model.Employee;

import java.util.Arrays;
import java.util.Collection;

@Service
public class EmployeeService extends BaseService {
    public Collection<Employee> getTopEmployees(String businessTag) {
        Employee[] response = restTemplate
                .getForObject("/business/{businessTag}/employee/mostWorked/top/1/10/between/10/20",
                        Employee[].class,
                        businessTag
                );

        return Arrays.asList(response);
    }
}
