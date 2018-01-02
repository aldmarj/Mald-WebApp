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
 * Employee services for talking to the API regarding employees.
 * 
 * @author Lawrence
 */
@Service
public class EmployeeService extends BaseService 
{
	/**
	 * Get all of the employees for the business context.
	 * 
	 * @return a list of all the employees for a business.
	 */
    public Set<Employee> getEmployees()
    {
    	Employee[] response = restTemplate
                .getForObject("/business/{businessTag}/employee",
                		Employee[].class
                );

        return new HashSet<>(Arrays.asList(response));
    }
    
    /**
     * Add an employee to the business context.
     * 
     * @param  employee the employee to add.
     * @return success string or error string.
     */
    public ResponseEntity<String> addEmployee(Employee employee)
    {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Employee> request = new HttpEntity<Employee>(employee, headers);
        ResponseEntity<String> response =
                restTemplate.exchange("/business/{businessTag}/employee", HttpMethod.POST,
                        request, String.class);
        return response;
    }
}
