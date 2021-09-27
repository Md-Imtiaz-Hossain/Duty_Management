package com.example.pack.service;


import com.example.pack.model.DutyLog;
import com.example.pack.repository.DutyLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class DutyLogService {

    ZoneId zoneId = ZoneId.of("Asia/Dhaka");
    LocalDate localDate = LocalDate.now(zoneId);
    Date currentDate = Date.valueOf(localDate);
    LocalTime localTime = LocalTime.now(zoneId);
    Time currentTime = Time.valueOf(localTime);


    @Autowired
    private DutyLogRepository dutyLogRepository;


    public List<DutyLog> getAllDuty() {
        return dutyLogRepository.findAll();
    }


    public void saveDuty(DutyLog dutyLog) {
        dutyLogRepository.save(dutyLog);
    }


}
