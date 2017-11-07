package website.controller;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import website.service.LogWorkService;

@Controller
public class LogWorkController {

  private final LogWorkService logWorkService;

  @Autowired
  public LogWorkController(LogWorkService logWorkService) {
    this.logWorkService = logWorkService;
  }

  @GetMapping("/{businessTag}/logWork")
  String logWork(@PathVariable final String businessTag, Model model) {
    Set<?> clients = logWorkService.getClients(businessTag);
    model.addAttribute("clients", clients);
    return "logWork";
  }
}
