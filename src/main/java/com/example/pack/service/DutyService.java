package com.example.pack.service;


import com.example.pack.exception.ClientNotFoundException;
import com.example.pack.model.Client;
import com.example.pack.model.Duty;
import com.example.pack.model.Employee;
import com.example.pack.repository.DutyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class DutyService {

    ZoneId zoneId = ZoneId.of("Asia/Dhaka");
    LocalDate localDate = LocalDate.now(zoneId);
    Date currentDate = Date.valueOf(localDate);
    LocalTime localTime = LocalTime.now(zoneId);
    Time currentTime = Time.valueOf(localTime);


    @Autowired
    private DutyRepository dutyRepository;


    public List<Duty> getAllDuty() {
        return dutyRepository.findAll();
    }


    public void saveDuty(Duty duty) {
        dutyRepository.save(duty);
    }


    public Duty getDutyById(Long id) {
        Optional<Duty> optional = dutyRepository.findById(id);
        Duty duty = null;
        if (optional.isPresent()) {
            duty = optional.get();
        } else {
            throw new ClientNotFoundException("No Client record exist for given id");
        }
        return duty;
    }


    public void deleteDutyById(Long id) {
        this.dutyRepository.deleteById(id);
    }


    public List<Duty> getCurrentDuty() {

        List<Duty> allDutyList = dutyRepository.findAll();

        List<Duty> currentDutyList = new ArrayList<>();
        List<Duty> upcomingAfterTodayDutyList = new ArrayList<>();
        List<Duty> finishedBeforeTodayDutyList = new ArrayList<>();
        List<Duty> todayUpcomingDutyList = new ArrayList<>();
        List<Duty> todayFinishedDutyList = new ArrayList<>();
        List<Duty> onDutyEmployeeList = new ArrayList<>();


        for (Duty duty : allDutyList) {
            Date dutyDate = duty.getDutyDate();
            Time startTime = duty.getStart();
            Time endTime = duty.getEnd();


            if (currentDate.compareTo(dutyDate) == 0) {
                if (currentTime.compareTo(startTime) > 0 && currentTime.compareTo(endTime) < 0) {
                    currentDutyList.add(duty);
                } else if (currentTime.compareTo(endTime) < 0) {
                    todayUpcomingDutyList.add(duty);
                } else {
                    todayFinishedDutyList.add(duty);
                }
            } else if (currentDate.compareTo(dutyDate) < 0) {
                upcomingAfterTodayDutyList.add(duty);
            } else {
                finishedBeforeTodayDutyList.add(duty);
            }
        }


        System.out.println("========  After today =========" + upcomingAfterTodayDutyList);
        System.out.println("======== Before Today  =========" + finishedBeforeTodayDutyList);
        System.out.println("========  Today upcoming =========" + todayUpcomingDutyList);
        System.out.println("========  Today Finished =========" + todayFinishedDutyList);
        return currentDutyList;
    }


    public List<Duty> todayUpcomingDutyList() {
        List<Duty> allDutyList = dutyRepository.findAll();
        List<Duty> currentDutyList = new ArrayList<>();
        List<Duty> todayUpcomingDutyList = new ArrayList<>();
        for (Duty duty : allDutyList) {
            Date dutyDate = duty.getDutyDate();
            Time startTime = duty.getStart();
            Time endTime = duty.getEnd();
            if (currentDate.compareTo(dutyDate) == 0) {
                if (currentTime.compareTo(startTime) > 0 && currentTime.compareTo(endTime) < 0) {
                    currentDutyList.add(duty);
                } else if (currentTime.compareTo(endTime) < 0) {
                    todayUpcomingDutyList.add(duty);
                }
            }
        }
        return todayUpcomingDutyList;
    }


    public List<Duty> upcomingAfterTodayDutyList() {
        List<Duty> allDutyList = dutyRepository.findAll();

        List<Duty> currentDutyList = new ArrayList<>();
        List<Duty> upcomingAfterTodayDutyList = new ArrayList<>();

        for (Duty duty : allDutyList) {
            Date dutyDate = duty.getDutyDate();
            Time startTime = duty.getStart();
            Time endTime = duty.getEnd();
            if (currentDate.compareTo(dutyDate) == 0) {
                if (currentTime.compareTo(startTime) > 0 && currentTime.compareTo(endTime) < 0) {
                    currentDutyList.add(duty);
                }
            } else if (currentDate.compareTo(dutyDate) < 0) {
                upcomingAfterTodayDutyList.add(duty);
            }
        }
        return upcomingAfterTodayDutyList;
    }


    public List<Employee> onDutyEmployeeList() {

        int employeeCount = 0;
        List<Duty> allDutyList = getCurrentDuty();
        List<Employee> onDutyEmployeeList = new ArrayList<Employee>();
        List<Employee> employees = null;

        for (Duty duty : allDutyList) {
            employees = duty.getEmployees();
            for (Employee employee : employees) {
                employeeCount++;
                onDutyEmployeeList.add(employee);
                System.out.println(employee.getFirstName() + " " + employee.getLastName());
            }
        }
        return onDutyEmployeeList;
    }


    public List<Duty> todayFinishedDutyList() {
        List<Duty> allDutyList = dutyRepository.findAll();
        List<Duty> currentDutyList = new ArrayList<>();
        List<Duty> todayUpcomingDutyList = new ArrayList<>();
        List<Duty> todayFinishedDutyList = new ArrayList<>();
        for (Duty duty : allDutyList) {
            Date dutyDate = duty.getDutyDate();
            Time startTime = duty.getStart();
            Time endTime = duty.getEnd();
            if (currentDate.compareTo(dutyDate) == 0) {
                if (currentTime.compareTo(startTime) > 0 && currentTime.compareTo(endTime) < 0) {
                    currentDutyList.add(duty);
                } else if (currentTime.compareTo(endTime) < 0) {
                    todayUpcomingDutyList.add(duty);
                } else {
                    todayFinishedDutyList.add(duty);
                }
            }
        }
        return todayFinishedDutyList;
    }


    public List<Duty> finishedBeforeTodayDutyList() {

        List<Duty> allDutyList = dutyRepository.findAll();

        List<Duty> currentDutyList = new ArrayList<>();
        List<Duty> upcomingAfterTodayDutyList = new ArrayList<>();
        List<Duty> finishedBeforeTodayDutyList = new ArrayList<>();

        for (Duty duty : allDutyList) {
            Date dutyDate = duty.getDutyDate();
            Time startTime = duty.getStart();
            Time endTime = duty.getEnd();

            if (currentDate.compareTo(dutyDate) == 0) {
                if (currentTime.compareTo(startTime) > 0 && currentTime.compareTo(endTime) < 0) {
                    currentDutyList.add(duty);
                }
            } else if (currentDate.compareTo(dutyDate) < 0) {
                upcomingAfterTodayDutyList.add(duty);
            } else {
                finishedBeforeTodayDutyList.add(duty);
            }
        }
        return finishedBeforeTodayDutyList;
    }


    public List<Duty> performCurrentMonthDutyList() {

        List<Duty> allDutyList = dutyRepository.findAll();

        List<Duty> currentDutyList = new ArrayList<>();
        List<Duty> upcomingAfterTodayDutyList = new ArrayList<>();
        List<Duty> todayUpcomingDutyList = new ArrayList<>();
        List<Duty> performCurrentMonthDutyList = new ArrayList<>();


        for (Duty duty : allDutyList) {
            Date dutyDate = duty.getDutyDate();
            Time startTime = duty.getStart();
            Time endTime = duty.getEnd();

            if (duty.getDutyDate().getMonth() + 1 == currentDate.getMonth() + 1) {
                if (currentDate.compareTo(dutyDate) == 0) {
                    if (currentTime.compareTo(startTime) > 0 && currentTime.compareTo(endTime) < 0) {
                        currentDutyList.add(duty);
                    } else if (currentTime.compareTo(endTime) < 0) {
                        todayUpcomingDutyList.add(duty);
                    } else {
                        performCurrentMonthDutyList.add(duty);
                    }
                } else if (currentDate.compareTo(dutyDate) < 0) {
                    upcomingAfterTodayDutyList.add(duty);
                } else {
                    performCurrentMonthDutyList.add(duty);
                }
            }
        }
        return performCurrentMonthDutyList;
    }


    public List<Duty> performCurrentYearDutyList() {

        List<Duty> allDutyList = dutyRepository.findAll();

        List<Duty> currentDutyList = new ArrayList<>();
        List<Duty> upcomingAfterTodayDutyList = new ArrayList<>();
        List<Duty> performCurrentMonthDutyList = new ArrayList<>();
        List<Duty> todayUpcomingDutyList = new ArrayList<>();

        for (Duty duty : allDutyList) {
            Date dutyDate = duty.getDutyDate();
            Time startTime = duty.getStart();
            Time endTime = duty.getEnd();

            if (currentDate.compareTo(dutyDate) == 0) {
                if (currentTime.compareTo(startTime) > 0 && currentTime.compareTo(endTime) < 0) {
                    currentDutyList.add(duty);
                } else if (currentTime.compareTo(endTime) < 0) {
                    todayUpcomingDutyList.add(duty);
                } else {
                    performCurrentMonthDutyList.add(duty);
                }
            } else if (currentDate.compareTo(dutyDate) < 0) {
                upcomingAfterTodayDutyList.add(duty);
            } else {
                performCurrentMonthDutyList.add(duty);
            }
        }
        return performCurrentMonthDutyList;
    }


    public List<Employee> offDutyEmployeeList() {

        int totalEmployee = 0;
        List<Duty> allDutyList = getCurrentDuty();
        List<Employee> offDutyEmployeeList = new ArrayList<Employee>();
        List<Employee> employees = null;

        for (Duty duty : allDutyList) {
            employees = duty.getEmployees();
            for (Employee employee : employees) {
                totalEmployee++;
                offDutyEmployeeList.add(employee);
                System.out.println(employee.getFirstName() + " " + employee.getLastName());
            }
        }
        System.out.println("====== New On Duty Emp -- " + offDutyEmployeeList);

        return offDutyEmployeeList;
    }


    public List<Duty> getTodayDutyReport() {

        List<Duty> allDutyList = dutyRepository.findAll();
        List<Duty> todayDutyReport = new ArrayList<>();
        for (Duty duty : allDutyList) {

            Date dutyDate = duty.getDutyDate();
            Time startTime = duty.getStart();
            Time endTime = duty.getEnd();

            if (currentDate.compareTo(dutyDate) == 0) {
                if (currentTime.compareTo(startTime) > 0 && currentTime.compareTo(endTime) < 0) {
                    todayDutyReport.add(duty);
                } else if (currentTime.compareTo(endTime) < 0) {
                    todayDutyReport.add(duty);
                } else {
                    todayDutyReport.add(duty);
                }
            }
        }

        return todayDutyReport;
    }


    public List<Duty> getCurrentWeekDutyReport() {

        List<Duty> allDutyList = dutyRepository.findAll();
        List<Duty> performCurrentWeekDutyList = new ArrayList<>();
        for (Duty duty : allDutyList) {
            Date dutyDate = duty.getDutyDate();

            Calendar dutyDateCalender = Calendar.getInstance();
            dutyDateCalender.setTime(dutyDate);

            Calendar currentDateCalender = Calendar.getInstance();
            currentDateCalender.setTime(currentDate);

            if (dutyDateCalender.get(Calendar.WEEK_OF_YEAR) == currentDateCalender.get(Calendar.WEEK_OF_YEAR)) {
                performCurrentWeekDutyList.add(duty);
            }
        }
        return performCurrentWeekDutyList;
    }


    public List<Duty> getCurrentMonthDutyReport() {

        List<Duty> allDutyList = dutyRepository.findAll();
        List<Duty> performCurrentMonthDutyList = new ArrayList<>();
        for (Duty duty : allDutyList) {
            Date dutyDate = duty.getDutyDate();

            Calendar dutyDateCalender = Calendar.getInstance();
            dutyDateCalender.setTime(dutyDate);

            Calendar currentDateCalender = Calendar.getInstance();
            currentDateCalender.setTime(currentDate);

            if (dutyDateCalender.get(Calendar.MONTH) == currentDateCalender.get(Calendar.MONTH)) {
                performCurrentMonthDutyList.add(duty);
            }
        }
        return performCurrentMonthDutyList;
    }


    public List<Duty> getCurrentYearDutyReport() {

        List<Duty> allDutyList = dutyRepository.findAll();
        List<Duty> performCurrentYearDutyList = new ArrayList<>();
        for (Duty duty : allDutyList) {
            Date dutyDate = duty.getDutyDate();

            Calendar dutyDateCalender = Calendar.getInstance();
            dutyDateCalender.setTime(dutyDate);

            Calendar currentDateCalender = Calendar.getInstance();
            currentDateCalender.setTime(currentDate);

            if (dutyDateCalender.get(Calendar.YEAR) == currentDateCalender.get(Calendar.YEAR)) {
                performCurrentYearDutyList.add(duty);
            }
        }
        return performCurrentYearDutyList;
    }


    public List<Duty> getDateRangeDutyReport(String startDutyRange, String endDutyRange) {

        List<Duty> allDutyList = dutyRepository.findAll();
        List<Duty> rangeDutyList = new ArrayList<>();

        try {
            SimpleDateFormat displayFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat parseFormat = new SimpleDateFormat("yyyy-MM-dd");

            java.util.Date startDate = parseFormat.parse(startDutyRange);
            java.util.Date endDate = parseFormat.parse(endDutyRange);

            for (Duty duty : allDutyList) {
                Date dutyDate = duty.getDutyDate();
                if (startDate.compareTo(dutyDate) <= 0 && endDate.compareTo(dutyDate) >= 0) {
                    rangeDutyList.add(duty);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rangeDutyList;
    }


    public List<Duty> getByEmployeeDutyReport(String employeeName) {

        List<Duty> allDutyList = getAllDuty();
        List<Duty> employeeReport = new ArrayList<>();
        List<Employee> employees = null;

        for (Duty duty : allDutyList) {
            employees = duty.getEmployees();
            for (Employee employee : employees) {
                if ((employee.getFirstName()).equals(employeeName)) {
                    employeeReport.add(duty);
                }
            }
        }
        return employeeReport;
    }


    public List<Duty> getByClientDutyReport(String clientName) {

        List<Duty> allDutyList = getAllDuty();
        List<Duty> clientReport = new ArrayList<>();
        Client allClient = null;

        for (Duty duty : allDutyList) {
            allClient = duty.getClient();
            String contactPerson = allClient.getContactPerson();
            if (contactPerson.compareTo(clientName) == 0){
                clientReport.add(duty);
            }
        }
        return clientReport;
    }
}
