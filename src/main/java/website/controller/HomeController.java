package website.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import website.service.BusinessService;

@Controller
public class HomeController {

	private final BusinessService businessService;

	@Autowired
	public HomeController(BusinessService businessService) {
		this.businessService = businessService;
	}

	@RequestMapping("/")
	String home(Model model) {
		Set<?> businesses = businessService.getBusinesses();
	    model.addAttribute("businesses", businesses);
		return "home";
	}
}
