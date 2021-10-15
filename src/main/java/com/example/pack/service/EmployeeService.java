package com.example.pack.service;


import com.example.pack.exception.EmployeeNotFoundException;
import com.example.pack.model.Employee;
import com.example.pack.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;




    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }


    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }


    public Employee getEmployeeById(Long id) {
        Optional<Employee> optional = employeeRepository.findById(id);
        Employee employee = null;
        if (optional.isPresent()) {
            employee = optional.get();
        } else {
            throw new EmployeeNotFoundException("No employee record exist for given id");
        }
        return employee;
    }



    public void deleteEmployeeById(Long id) {
        this.employeeRepository.deleteById(id);
    }

}
