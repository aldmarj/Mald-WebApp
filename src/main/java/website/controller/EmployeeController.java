/**
 * 
 */
package website.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import website.model.Employee;
import website.service.EmployeeService;

/**
 * Controller to handle the mutation of employee data on the addEmployee page.
 * 
 * @author Lawrence
 */
@Controller
public class EmployeeController {
	/** Handle to the employee service used to talk to the API **/
    private final EmployeeService employeeService;

    /**
     * CLASS CONSTRUCTOR
     * 
     * @param employeeService - a handle to the employeeService, spring hanldes the instantiation of this.
     */
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    
    /**
     * GET request mapping for showing the add employee page.
     * 
     * @param businessTag - the business context.
     * @param model - the data model to display.
     * @return the name of the jsp to display.
     */
    @GetMapping("/{businessTag}/addEmployee")
    public String getEmployee(@PathVariable final String businessTag, Model model) {
    	Employee employee = HomeController.createInitialEmployee();
    	
		model.addAttribute("employee", employee);
		
    	return "addEmployee";
    }
    
    /**
     * POST request mapping for adding an employee to the API.
     * 
     * @param employee - The employee to add.
     * @param businessTag - The business context.
     * @param model - The data model.
     * @param redirectAttributes - Temporary attribute for error reporting.
     * @return the name of the jsp to display.
     */
    @PostMapping("/{businessTag}/addEmployee")
    public String addEmployee(@ModelAttribute("employee")Employee employee,
    		@PathVariable final String businessTag,
    		Model model, RedirectAttributes redirectAttributes)
    {
    	 employee.setBusinessTag(businessTag);
    	 
    	 if (employee.getParentUserName() != null 
    			 && employee.getParentUserName().isEmpty())
    	 {
    		 employee.setParentUserName(null);
    	 }
    	 
 		// Attempt to add the business
         ResponseEntity<String> response = employeeService.addEmployee(employee);
         if(response.getStatusCode() == HttpStatus.BAD_REQUEST)
         {
         	// Report the failure to the user.
         	redirectAttributes.addFlashAttribute("error", response.getBody());
         	return "redirect:/" + businessTag + "/addEmployee";
         }
         
         // Report success to the user.
     	redirectAttributes.addFlashAttribute("success", response.getBody());
 		return "redirect:/" + businessTag;
    }
}
