/**
 * 
 */
package website.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import website.model.Employee;

/**
 * @author Lawrence
 *
 */
@Service
public class EmployeeService extends BaseService 
{
    public Set<Employee> getEmployees()
    {
    	Employee[] response = restTemplate
                .getForObject("/business/{businessTag}/employee",
                		Employee[].class
                );

        return new HashSet<>(Arrays.asList(response));
    }
    
    public ResponseEntity<String> addEmployee(Employee employee)
    {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Employee> request = new HttpEntity<Employee>(employee, headers);
        ResponseEntity<String> response =
                restTemplate.exchange("/business/{businessTag}/employee", HttpMethod.POST,
                        request, String.class);
        return response;
    }
    
    public ResponseEntity<String> addEmployee(Employee employee, String businessTag)
    {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Employee> request = new HttpEntity<Employee>(employee, headers);
        ResponseEntity<String> response =
                restTemplate.exchange("/business/" + businessTag + "/employee", HttpMethod.POST,
                        request, String.class);
        return response;
    }
}
