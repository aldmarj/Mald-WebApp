package website.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import website.model.Client;
import website.service.ClientService;

@Controller
public class AddClientController {

  private final ClientService clientService;
  private final Validator validator;

  @InitBinder
  private void initBinder(WebDataBinder binder) {
    binder.setValidator(validator);
  }

  @Autowired
  public AddClientController(ClientService clientService,
      @Qualifier("clientValidator") Validator validator) {
    this.clientService = clientService;
    this.validator = validator;
  }

  @GetMapping("/{businessTag}/addClient")
  public String addClientView(@PathVariable final String businessTag, Model model) {
    model.addAttribute("businessTag", businessTag);

    if (!model.containsAttribute("client")) {
      Client client = new Client();
      client.setBusinessTag(businessTag);
      model.addAttribute("client", client);
    }

    return "addClient";
  }

  @PostMapping("/{businessTag}/addClient")
  public String addClient(@PathVariable final String businessTag, Model model,
      @ModelAttribute("client") @Validated Client client, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return addClientView(businessTag, model);
    }

    // use API to add new client
    ResponseEntity<String> response = clientService.addClient(businessTag, client);

    if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
      // Report the failure to the user.
      model.addAttribute("addClientError", response.getBody());
      return addClientView(businessTag, model);
    }

    // Report success to the user.
    model.addAttribute("addClientSuccess", response.getBody());
    return "redirect:/" + businessTag;
  }
}
