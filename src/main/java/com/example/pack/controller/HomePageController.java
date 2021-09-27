package com.example.pack.controller;


import com.example.pack.exception.EmployeeIdNotFoundException;
import com.example.pack.model.Duty;
import com.example.pack.model.Employee;
import com.example.pack.service.ClientService;
import com.example.pack.service.DutyService;
import com.example.pack.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/home")
public class HomePageController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private DutyService dutyService;

    @GetMapping("/index")
    public String dutyList(Model model) {

        List<Duty> currentActiveDuty = dutyService.getCurrentDuty();

        model.addAttribute("title", "Home");
        model.addAttribute("currentActiveDuty", currentActiveDuty);
        model.addAttribute("totalCurrentActiveDuty", (long) currentActiveDuty.size());
        model.addAttribute("todayUpcomingDutyList", dutyService.todayUpcomingDutyList().size());
        model.addAttribute("upcomingAfterTodayDutyList", dutyService.upcomingAfterTodayDutyList().size());
        model.addAttribute("onDutyEmployeeList", dutyService.onDutyEmployeeList().size());
        model.addAttribute("todayFinishedDutyList", dutyService.todayFinishedDutyList().size());
        model.addAttribute("finishedBeforeTodayDutyList", dutyService.finishedBeforeTodayDutyList().size());
        model.addAttribute("performCurrentMonthDutyList", dutyService.performCurrentMonthDutyList().size());
        model.addAttribute("performCurrentYearDutyList", dutyService.performCurrentYearDutyList().size());

        return "/home/index";
    }


    @GetMapping("/show-details")
    public String dutyListDetails(@RequestParam("id") Long id, Model model) {

        Optional<Duty> optional = Optional.ofNullable(dutyService.getDutyById(id));
        Duty dutyDetails = null;
        List<Employee> employee = null;

        if (optional.isPresent()) {
            dutyDetails = optional.get();
            employee = optional.get().getEmployees();
            System.out.println("Total Assign Employee - " + employee.size());
            model.addAttribute("title", "Home - Duty Details");
            model.addAttribute("dutyDetails", dutyDetails);
            model.addAttribute("employee", employee);
        } else
            throw new EmployeeIdNotFoundException("Id Not Found !!! ");

        return "/home/show-details";
    }

}
