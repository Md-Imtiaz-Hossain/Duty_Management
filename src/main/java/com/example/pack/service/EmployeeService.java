package com.example.pack.service;


import com.example.pack.exception.EmployeeNotFoundException;
import com.example.pack.model.Employee;
import com.example.pack.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService{

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


	public Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection, String keyword) {

		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		if (keyword != null){
			return employeeRepository.findAll(keyword, pageable);
		}
		return this.employeeRepository.findAll(pageable);
	}
}
