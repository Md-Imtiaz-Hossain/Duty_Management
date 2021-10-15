package com.example.pack.controller;

import com.example.pack.RestControllerRequestResponse.*;
import com.example.pack.model.Duty;
import com.example.pack.model.DutyLog;
import com.example.pack.service.DutyLogService;
import com.example.pack.service.DutyService;
import com.example.pack.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/mobile-api")
@CrossOrigin
public class JwtController {

    ZoneId zoneId = ZoneId.of("Asia/Dhaka");
    LocalDateTime localDateTime = LocalDateTime.now(zoneId);

    @Autowired
    private DutyService dutyService;

    @Autowired
    private JwtService jwtService;


    @Autowired
    private DutyLogService dutyLogService;


    //  /sign-in   POST{username,password}  200  {token:jwt-token User-details:object}
    @PostMapping({"/sign-in"})
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        return jwtService.createJwtToken(jwtRequest);
    }

    // duties  GET   token  200  duty-list:Array
    @GetMapping("/duties")
    public List<Duty> allDutyInformation(Model model) {
        List<Duty> allDuty = dutyService.getAllDuty();
        System.out.println(allDuty);
        return allDuty;
    }

    // duties/{id}    GET  token 200   duty-details:object
    @GetMapping("/duty")
    public List<Duty> dutyInformation(Model model, @RequestParam("id") Long id) {
        List<Duty> allDuty = Collections.singletonList(dutyService.getDutyById(id));
        System.out.println(allDuty);
        return allDuty;
    }


    // duties/{id}/start   POST{location-lat:number,location-lang:number,start-time:timestamp,comment:text}           token 200
    @PostMapping("/duty-start-location")
    public List<DutyLog> dutyStartLocation(@RequestBody DutiesStart startDutiesLocation,
                                           Model model,
                                           @RequestParam("duty_id") Long duty_id) {

        DutyLog dutyLog = new DutyLog();
        dutyLog.setStart_dateTime(localDateTime);
        dutyLog.setStart_location_latitude(startDutiesLocation.getStart_location_latitude());
        dutyLog.setStart_location_longitude(startDutiesLocation.getStart_location_longitude());
        dutyLog.setStart_comment(startDutiesLocation.getStart_comment());
        dutyLog.setDuty_id(duty_id);
        dutyLogService.saveDutyLog(dutyLog);
        return dutyLogService.getAllDutyLog();
    }


    // duties/{id}/end     POST{location-lat:number,location-lang:number,endtime:timestamp,comment:text}              token 200
    @PostMapping("/duty-end-location")
    public List<DutyLog> dutyEndLocation(@RequestBody DutiesEnd endDutiesLocation,
                                         Model model,
                                         @RequestParam("duty_id") Long duty_id) {

        DutyLog dutyLog = new DutyLog();
        dutyLog.setEnd_dateTime(localDateTime);
        dutyLog.setEnd_location_latitude(endDutiesLocation.getEnd_location_latitude());
        dutyLog.setEnd_location_longitude(endDutiesLocation.getEnd_location_longitude());
        dutyLog.setEnd_comment(endDutiesLocation.getEnd_comment());
        dutyLog.setDuty_id(duty_id);
        dutyLogService.saveDutyLog(dutyLog);
        return dutyLogService.getAllDutyLog();
    }

    /// duties/{id}/location POST{lat:number,lang:number}
    @PostMapping("/duty-current-location")
    public List<DutyLog> dutyCurrentLocation(@RequestBody CurrentDutiesLocation currentDutiesLocation,
                                             Model model,
                                             @RequestParam("duty_id") Long duty_id) {

        DutyLog dutyLog = new DutyLog();
        dutyLog.setCurrent_dateTime(localDateTime);
        dutyLog.setCurrent_location_latitude(currentDutiesLocation.getCurrent_location_latitude());
        dutyLog.setCurrent_location_longitude(currentDutiesLocation.getCurrent_location_longitude());
        dutyLog.setDuty_id(duty_id);
        dutyLogService.saveDutyLog(dutyLog);
        return dutyLogService.getAllDutyLog();
    }


//    @PostMapping({"/sign-out"})
//    public JwtResponse signOut(@RequestBody JwtRequest jwtRequest) throws Exception {
//        return jwtService.createJwtToken(jwtRequest);
//    }


}
