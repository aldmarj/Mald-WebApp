package website.service;

import org.springframework.stereotype.Service;
import website.model.Employee;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
public class EmployeeService extends BaseService {
    public Set<Employee> getTopEmployees(String businessTag) {
        Employee[] response = restTemplate
                .getForObject("/business/{businessTag}/employee/mostWorked/top/1/10/between/10/20",
                        Employee[].class,
                        businessTag
                );

        return new HashSet<>(Arrays.asList(response));
    }
}
