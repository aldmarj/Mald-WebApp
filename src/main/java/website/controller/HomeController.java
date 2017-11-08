package website.controller;

import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
		// set default value
		Business business = new Business();
		business.setBusinessName("businessName");
		business.setBusinessTag("businessTag");
		model.addAttribute("business", business);
		
		return "home";
	}
	
	@PostMapping("/businesses")
    public String addBusiness(@Valid @ModelAttribute("business")Business business, 
      BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "error";
        }
        
        businessService.addBusiness(business);

		return "redirect:/" + business.getBusinessTag();
    }
}
