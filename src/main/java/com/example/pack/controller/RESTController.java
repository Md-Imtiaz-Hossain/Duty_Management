package com.example.pack.controller;



import com.example.pack.model.DutyLog;
import com.example.pack.service.DutyLogService;
import com.example.pack.service.DutyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RESTController {


    @Autowired
    private DutyLogService dutyLogService;

    @Autowired
    private DutyService dutyService;

    @GetMapping("/all-duty-log")
    public List<DutyLog> allLogInformation(Model model) {
        List<DutyLog> allDuty = dutyLogService.getAllDuty();
        System.out.println(allDuty);
        return allDuty;
    }

}
