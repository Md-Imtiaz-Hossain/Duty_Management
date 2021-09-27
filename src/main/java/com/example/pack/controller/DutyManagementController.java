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
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/duty")
public class DutyManagementController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private DutyService dutyService;


    @GetMapping("/duty-list")
    public String dutyList(Model model) {

        List<Duty> dutyList = dutyService.getAllDuty();

        model.addAttribute("title", "Duty - Duty List");
        model.addAttribute("dutyList", dutyList);

        return "/duty/duty-list";
    }


    @GetMapping("/create-duty")
    public String home2(Model model) {
        List<Client> clientList = clientService.getAllClient();
        List<Employee> employeeList = employeeService.getAllEmployees();

        model.addAttribute("title", " Duty - Create And Assign Duty");

        model.addAttribute("clientList", clientList);
        model.addAttribute("employeeList", employeeList);
        model.addAttribute("duty", new Duty());


        return "/duty/create-duty";
    }


    // Process the Duty create form
    @PostMapping("/save-duty")
    public String saveDuty(@Valid @ModelAttribute("duty") Duty duty,
                           Errors errors, Model model,
                           @RequestParam(value = "startAtFormat") String startAtFormat,
                           @RequestParam(value = "endAtFormat") String endAtFormat) {

        if (errors.hasErrors()) {
            model.addAttribute("duty", duty);
            return "/client/new-client";
        } else {

            try {
                SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm:ss");
                SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm:ss a");

                if (startAtFormat.equals("PM")) {
                    String startTime = duty.getStart().toString() + " " + "PM";
                    Date date = parseFormat.parse(startTime);
                    duty.setStartAt(Time.valueOf(displayFormat.format(date)));
                }

                if (endAtFormat.equals("PM")) {
                    String endTime = duty.getEnd().toString() + " " + "PM";
                    Date date = parseFormat.parse(endTime);
                    duty.setEndAt(Time.valueOf(displayFormat.format(date)));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            dutyService.saveDuty(duty);
            return "redirect:/duty/duty-list";
        }
    }

//<!--AIzaSyCIX4C_VQ7ALIIh5tHqD9qHcDnZI3uilHA-->

}
