package website.controller;

import java.security.Principal;
import java.util.Collection;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import website.config.WebMvcConfig;
import website.model.Client;
import website.model.WorkLog;
import website.service.ClientService;
import website.service.WorkLogService;

@Controller
public class LogWorkController {

  private final ClientService clientService;
  private final WorkLogService workLogService;
  private final Validator validator;

  @InitBinder
  private void initBinder(WebDataBinder binder) {
    binder.setValidator(validator);
  }

  @Autowired
  public LogWorkController(ClientService clientService, WorkLogService workLogService,
      @Qualifier("workLogValidator") Validator validator) {
    this.clientService = clientService;
    this.workLogService = workLogService;
    this.validator = validator;
  }

  @GetMapping("/{businessTag}/logWork")
  String logWork(Principal principal, @PathVariable final String businessTag, Model model) {
    model.addAttribute("businessTag", businessTag);

    // use API to get all clients for this business
    Collection<Client> clients = clientService.getClients(businessTag);
    model.addAttribute("clients", clients);

    // if method has been called with an existing workLog use it
    if (!model.containsAttribute("workLog")) {
      WorkLog workLog = new WorkLog();
      
      workLog.setUserName(principal.getName());
      model.addAttribute("workLog", workLog);
    }

    model.addAttribute("MOMENTJS_DATE_FORMAT",
        WebMvcConfig.MOMENTJS_DATE_FORMAT);

    return "logWork";
  }

  @PostMapping("/{businessTag}/logWork")
  public String addWorkLog(Principal principal, @PathVariable final String businessTag, Model model,
      @ModelAttribute("workLog") @Validated WorkLog workLog, BindingResult bindingResult,
      RedirectAttributes redirectAttributes) {
    if (bindingResult.hasErrors()) {
        return "redirect:/" + businessTag + "/logWork";
    }
    
    workLog.setUserName(principal.getName());

    // use API to add new workLog
    ResponseEntity<String> response = workLogService.addWorkLog(businessTag, workLog);

    if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
      // Report the failure to the user.
      model.addAttribute("addWorkLogError", response.getBody());
      return "redirect:/" + businessTag + "/logWork";
    }

    // Report success to the user.
    redirectAttributes.addFlashAttribute("addWorkLogSuccess", response.getBody());
    return "redirect:/" + businessTag;
  }
}
