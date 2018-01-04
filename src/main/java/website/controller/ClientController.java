package website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Controller for handling Client requests.
 * 
 * @author Aldmar
 */
@Controller
public class ClientController {
	
	/**
	 * Get request for showing the add client page.
	 * 
	 * @param businessTag - The business context.
	 * @param model - The data model.
	 * @return the name of the jsp to display.
	 */
    @GetMapping("/{businessTag}/addClient")
    public String dashboard(@PathVariable final String businessTag, Model model) {
        model.addAttribute("businessTag", businessTag);

        return "addClient";
    }
}
