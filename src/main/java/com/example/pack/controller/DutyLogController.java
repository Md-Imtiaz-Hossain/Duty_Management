package com.example.pack.controller;

import com.example.pack.model.DutyLog;
import com.example.pack.service.DutyLogService;
import com.example.pack.service.DutyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Controller
@RequestMapping("/duty-log")
public class DutyLogController {

    @Autowired
    private DutyLogService dutyLogService;

    @Autowired
    private DutyService dutyService;

    @GetMapping("/log-form")
    public String logInformation(Model model) {
        model.addAttribute("title", "Duty Log - Duty Log Form");
        model.addAttribute("allDutyList", dutyService.getAllDuty());
        model.addAttribute("dutyLog", new DutyLog());


        return "/dutyLog/duty-log-form";
    }




    @PostMapping("/log-save")
    public String logInformationSave(@ModelAttribute("dutyLog") DutyLog dutyLog,
                                     Model model) {


        ZoneId zoneId = ZoneId.of("Asia/Dhaka");
        LocalDateTime localDate = LocalDateTime.now(zoneId);
        dutyLog.setCurrentDateTime(localDate);

        dutyLogService.saveDuty(dutyLog);
        return "redirect:/duty-log/log-form";
    }

}
