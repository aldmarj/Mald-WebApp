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

import website.model.Business;
import website.service.BusinessService;

@Controller
public class HomeController {

	private final BusinessService businessService;

	@Autowired
	public HomeController(BusinessService businessService) {
		this.businessService = businessService;
	}

	@GetMapping("/")
	public String home(Model model) {
		return "redirect:/businesses";
	}
	
	@GetMapping("/businesses")
	public String showAllBusinesses(Model model) {
		// Get all businesses to display as a list
		Set<?> businesses = businessService.getBusinesses();
	    model.addAttribute("businesses", businesses);
	    
	    // Get the possible new business
		Business business = new Business();
		// set default value
		business.setBusinessName("Name");
		business.setBusinessTag("Tag");
		model.addAttribute("business", business);
		
		return "home";
	}
	
	@PostMapping("/businesses")
    public String addBusiness(@ModelAttribute("business")Business business, 
    		Model model, RedirectAttributes redirectAttributes) 
	{
        ResponseEntity<String> response = businessService.addBusiness(business);
        if(response.getStatusCode() == HttpStatus.BAD_REQUEST)
        {
        	// Report the failure to the user.
        	redirectAttributes.addFlashAttribute("error", response.getBody());
        	return "redirect:/businesses";
        }
    	
        // Report success to the user.
    	redirectAttributes.addFlashAttribute("success", response.getBody());
		return "redirect:/" + business.getBusinessTag();
    }
}
