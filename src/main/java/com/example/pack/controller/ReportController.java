package com.example.pack.controller;

import com.example.pack.model.Client;
import com.example.pack.model.Duty;
import com.example.pack.model.Employee;
import com.example.pack.service.ClientService;
import com.example.pack.service.DutyService;
import com.example.pack.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private DutyService dutyService;

    @GetMapping("/home")
    public String home(Model model) {

        List<Duty> allDuty = dutyService.getAllDuty();
        List<Employee> allEmployees = employeeService.getAllEmployees();
        List<Client> allClient = clientService.getAllClient();

        model.addAttribute("title", "Report - Home");
        model.addAttribute("allDutyDetails", allDuty);
        model.addAttribute("allEmployees", allEmployees);
        model.addAttribute("allClients", allClient);
        return "/report/home";
    }


    @GetMapping("/today")
    public String todayReport(Model model) {
        List<Duty> allDuty = dutyService.getTodayDutyReport();
        model.addAttribute("title", "Report - Today's report");
        model.addAttribute("dutyList", allDuty);
        return "/report/today-report";
    }


    @GetMapping("/week")
    public String weekReport(Model model) {
        List<Duty> allDuty = dutyService.getCurrentWeekDutyReport();
        model.addAttribute("title", "Report - Week's report");
        model.addAttribute("dutyList", allDuty);
        return "/report/week-report";
    }


    @GetMapping("/month")
    public String monthReport(Model model) {
        List<Duty> allDuty = dutyService.getCurrentMonthDutyReport();
        model.addAttribute("title", "Report - Month's report");
        model.addAttribute("dutyList", allDuty);
        return "/report/month-report";
    }


    @GetMapping("/year")
    public String yearReport(Model model) {
        List<Duty> allDuty = dutyService.getCurrentYearDutyReport();
        model.addAttribute("title", "Report - Year's report");
        model.addAttribute("dutyList", allDuty);
        return "/report/year-report";
    }


    @PostMapping("/date-range")
    public String dateRangeReport(Model model,
                                  @RequestParam(value = "startDutyRange") String startDutyRange,
                                  @RequestParam(value = "endDutyRange") String endDutyRange) {
        List<Duty> allDuty = dutyService.getDateRangeDutyReport(startDutyRange, endDutyRange);
        model.addAttribute("title", "Report - Date Range report");
        model.addAttribute("dutyList", allDuty);
        return "/report/date-range-report";
    }


    @PostMapping("/employee-report")
    public String employeeReport(Model model, @RequestParam(value = "employeeName") String employeeName) {
        List<Duty> allDuty = dutyService.getByEmployeeDutyReport(employeeName);
        model.addAttribute("title", "Report - By Employee report");
        model.addAttribute("dutyList", allDuty);
        return "/report/employee-report";
    }

    @PostMapping("/client-report")
    public String clientReport(Model model, @RequestParam(value = "clientName") String clientName) {
        List<Duty> allDuty = dutyService.getByClientDutyReport(clientName);
        model.addAttribute("title", "Report - By Client report");
        model.addAttribute("dutyList", allDuty);
        return "/report/client-report";
    }


}
