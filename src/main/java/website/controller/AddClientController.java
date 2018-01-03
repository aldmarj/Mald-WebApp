package website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AddClientController {
    @GetMapping("/{businessTag}/addClient")
    public String dashboard(@PathVariable final String businessTag, Model model) {
        model.addAttribute("businessTag", businessTag);

        return "addClient";
    }
}
