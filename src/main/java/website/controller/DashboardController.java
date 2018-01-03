package website.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import website.model.Business;
import website.model.Client;
import website.model.WorkLog;
import website.service.ClientService;
import website.service.EmployeeService;
import website.service.WorkLogService;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class DashboardController {
    private final ClientService clientService;
    private final WorkLogService worklogService;
    private final EmployeeService employeeService;

    @Autowired
    public DashboardController(ClientService clientService, WorkLogService worklogService, EmployeeService employeeService) {
        this.clientService = clientService;
        this.worklogService = worklogService;
        this.employeeService = employeeService;
    }


    @GetMapping("/{businessTag}")
    public String dashboard(@PathVariable final String businessTag, Model model) {
        model.addAttribute("businessTag", businessTag);

        List clients = clientService.getClients(businessTag);
        model.addAttribute("clients", clients);


        Set<?> worklog = worklogService.getWorklogs(businessTag);
        model.addAttribute("worklog", worklog);

        ArrayList topEmployee = employeeService.getTopEmployees(businessTag);
        model.addAttribute("topEmployee", topEmployee);


        return "dashboard";
    }
}
