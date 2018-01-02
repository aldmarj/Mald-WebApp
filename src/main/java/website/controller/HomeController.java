package website.controller;

import java.util.Collection;

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

/**
 * Controller to handle URL mapping and model mutations regarding the home page.
 * 
 * @author Lawrence
 */
@Controller
public class HomeController {

	/** Business service for talking to the API **/
	private final BusinessService businessService;

	/**
	 * CLASS CONSTRUCTOR
	 * 
	 * @param businessService - Instance is autowired and popualted by spring.
	 */
	@Autowired
	public HomeController(BusinessService businessService) {
		this.businessService = businessService;
	}

	/**
	 * GET request handle for the root URL.
	 * 
	 * @param model - The model.
	 * @return name of the jsp to show.
	 */
	@GetMapping("/")
	public String home(Model model) {
		// Get all businesses to display as a list
		Collection<?> businesses = businessService.getBusinesses();
	    model.addAttribute("businesses", businesses);
	    
	    // Set default values for displaying in the fields
		Business business = createDefaultBusiness();
		model.addAttribute("business", business);

		return "home";	
	}
	
	/**
	 * POST request handle for the root URL.
	 * 
	 * @param business - the business that is being posted.
	 * @param model - the view's model.
	 * @param redirectAttributes - temporary attributes for showing errors.
	 * @return the name of the jsp to show.
	 */
	@PostMapping("/")
    public String addBusiness(@ModelAttribute("business")Business business,
    		Model model, RedirectAttributes redirectAttributes) 
	{		
		business.getInitialEmployee().setBusinessTag(business.getBusinessTag());
		
		// Attempt to add the business
        ResponseEntity<String> businessResponse = businessService.addBusiness(business);
        if(businessResponse.getStatusCode() == HttpStatus.BAD_REQUEST)
        {
        	// Report the failure to the user.
        	redirectAttributes.addFlashAttribute("error", businessResponse.getBody());
        	return "redirect:/";
        }
    	
        // Report success to the user.
    	redirectAttributes.addFlashAttribute("success", businessResponse.getBody());
		return "redirect:/" + business.getBusinessTag();
    }
	
	/**
	 * Return a business populated with defaults for display.
	 * 
	 * @return a business populated with defaults.
	 */
	private Business createDefaultBusiness()
	{
	    // Get the possible new business
		Business business = new Business();
		
		// set default value of the fields
		business.setBusinessName("Name");
		business.setBusinessTag("Tag");
		business.setInitialEmployee(createInitialEmployee());
		
		return business;
	}
	
	/**
	 * Return an employee populated with defaults for display.
	 * 
	 * @return an employee populated with defaults.
	 */
	private Employee createInitialEmployee()
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
}
