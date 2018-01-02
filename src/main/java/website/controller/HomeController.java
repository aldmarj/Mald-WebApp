package website.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import website.model.Account;
import website.model.Business;
import website.model.Employee;
import website.service.BusinessService;
import website.service.EmployeeService;

@Controller
public class HomeController {

	private final BusinessService businessService;
	private final EmployeeService employeeService;

	@Autowired
	public HomeController(BusinessService businessService, EmployeeService employeeService) {
		this.businessService = businessService;
		this.employeeService = employeeService;
	}

	@GetMapping("/")
	public String home(Model model) {
		// Get all businesses to display as a list
		Set<?> businesses = businessService.getBusinesses();
	    model.addAttribute("businesses", businesses);
	    
	    // Set default values for displaying in the fields
		Business business = createDefaultBusiness();
		model.addAttribute("business", business);

		return "home";	
	}
	
	@PostMapping("/")
    public String addBusiness(@ModelAttribute("business")Business business,
    		Model model, RedirectAttributes redirectAttributes) 
	{		
		Employee employee = business.getDefaultEmployee();
		setupEmployeeWithBusinessDetails(business, employee);
		
		// Attempt to add the business
        ResponseEntity<String> businessResponse = businessService.addBusiness(business);
        if(businessResponse.getStatusCode() == HttpStatus.BAD_REQUEST)
        {
        	// Report the failure to the user.
        	redirectAttributes.addFlashAttribute("error", businessResponse.getBody());
        	return "redirect:/";
        }
        
        // Attempt to add the employee
        ResponseEntity<String> employeeResponce = 
        		employeeService.addEmployee(employee, business.getBusinessTag());
        if(businessResponse.getStatusCode() == HttpStatus.BAD_REQUEST)
        {
        	// Report the failure to the user.
        	redirectAttributes.addFlashAttribute("error", employeeResponce.getBody());
        	return "redirect:/";
        }
    	
        // Report success to the user.
    	redirectAttributes.addFlashAttribute("success", businessResponse.getBody());
		return "redirect:/" + business.getBusinessTag();
    }
	
	private Business createDefaultBusiness()
	{
	    // Get the possible new business
		Business business = new Business();
		
		// set default value of the fields
		business.setBusinessName("Name");
		business.setBusinessTag("Tag");
		business.setDefaultEmployee(createDefaultEmployee());
		
		return business;
	}
	
	private Employee createDefaultEmployee()
	{
	    // Get the possible new business
		Employee employee = new Employee();
		Account account = new Account();
				
		// set default value of the fields
		account.setUserName("User Name");
		
		employee.setAccount(account);
		employee.setFirstName("First Name");
		employee.setSurName("Sur Name");
		employee.setJobRole("Job Role");
		
		return employee;
	}
	
	private void setupEmployeeWithBusinessDetails(Business business, Employee employee)
	{
		employee.setBusinessTag(business.getBusinessTag());
		employee.getAccount().setBusinessTag(business.getBusinessTag());
	}
}
