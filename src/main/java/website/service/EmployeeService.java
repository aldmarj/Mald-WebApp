/**
 * 
 */
package website.service;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import website.model.Employee;
import website.utils.DateUtils;

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
    public Collection<Employee> getEmployees(String businessTag)
    {
    	Employee[] response = restTemplate
                .getForObject("/business/{businessTag}/employee",
                		Employee[].class,
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
    public Collection<Employee> getTopNEmployeesForCurrentMonth(String businessTag, int n) {    	
    	long startMonth = DateUtils.getStartOfCurrentMonthInMillis();
    	long endMonth = DateUtils.getEndOfCurrentMonthInMillis();
    	
        Employee[] response = restTemplate
                .getForObject("/business/{businessTag}/employee/mostWorked/top/1/{n}/between/{startMonth}/{endMonth}",
                        Employee[].class,
                        businessTag,
                        n,
                        startMonth,
                        endMonth
                );

        return Arrays.asList(response);
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
                        request, String.class, employee.getBusinessTag());
        return response;
    }
}
