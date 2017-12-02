package website.controller;

import java.security.Security;
import java.time.LocalDateTime;
import java.time.temporal.TemporalField;
import java.util.Date;
import java.util.Set;
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
import website.config.WebMvcConfig;
import website.model.Business;
import website.model.WorkLog;
import website.service.ClientService;
import website.service.WorkLogService;

@Controller
public class LogWorkController {

  private final ClientService clientService;
  private final WorkLogService workLogService;

  @Autowired
  public LogWorkController(ClientService clientService, WorkLogService workLogService) {
    this.clientService = clientService;
    this.workLogService = workLogService;
  }

  @GetMapping("/{businessTag}/logWork")
  String logWork(@PathVariable final String businessTag, Model model) {
    model.addAttribute("businessTag", businessTag);

    Set<?> clients = clientService.getClients(businessTag);
    model.addAttribute("clients", clients);

    WorkLog workLog = new WorkLog();
    // TODO: Needs to use Authentication solution to get current userName
    workLog.setUserName("Default");
    workLog.setDescription("Enter a description...");
    model.addAttribute("workLog", workLog);

    model.addAttribute("BOOTSTRAP_DATETIMEPICKER_FORMAT",
        WebMvcConfig.BOOTSTRAP_DATETIMEPICKER_FORMAT);

    return "logWork";
  }

  @PostMapping("/{businessTag}/logWork")
  public String addWorkLog(@PathVariable final String businessTag,
      @ModelAttribute("workLog") WorkLog workLog,
      RedirectAttributes redirectAttributes) {
    ResponseEntity<String> response = workLogService.addWorkLog(businessTag, workLog);
    if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
      // Report the failure to the user.
      redirectAttributes.addFlashAttribute("error", response.getBody());
      return "redirect:/" + businessTag + "logWork";
    }

    // Report success to the user.
    redirectAttributes.addFlashAttribute("success", response.getBody());
    return "redirect:/" + businessTag;
  }
}
