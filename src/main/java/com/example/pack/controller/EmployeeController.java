package com.example.pack.controller;


import com.example.pack.enums.Designation;
import com.example.pack.exception.EmployeeIdNotFoundException;
import com.example.pack.model.Employee;
import com.example.pack.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/employee")
public class EmployeeController {


	@Autowired
	private EmployeeService employeeService;


	// display list of employees
	@GetMapping("/home")
	public String employeeList(Model model) {
		String keyword = null;
		return findPaginated(1, "firstName", "asc",keyword, model);
	}


	// display the employee create form
	@GetMapping("/show-new-employee-form")
	public String showNewEmployeeForm(Model model) {
		Employee employee = new Employee();
		model.addAttribute("title", "Create - Employee Create Form");
		model.addAttribute("employee", employee);
		model.addAttribute("designationType", Designation.values());
		return "/employee/new-employee";
	}


	// Process the employee create form
	@PostMapping("/save-employee")
	public String saveEmployee(@Valid @ModelAttribute("employee") Employee employee,
							   Errors errors,
							   @RequestParam("file") MultipartFile file,
							   Model model) throws IOException {

		if (errors.hasErrors()) {
			model.addAttribute("employee", employee);
			return "/employee/new-employee";
		} else {
			if(file.isEmpty()){
				System.out.println("Profile Image is Empty ! ");
				employeeService.saveEmployee(employee);
			}else {
				employee.setPhoto(file.getOriginalFilename());
				File saveFile = new ClassPathResource("/static/image").getFile();
				Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + file.getOriginalFilename());
				Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
				System.out.println("Profile Image is Uploaded.");
				employeeService.saveEmployee(employee);
			}
		}
		return "redirect:/employee/home";
	}




	// Display the employee Update form
	@GetMapping("/show-form-for-update")
	public String showFormForUpdate(@RequestParam("id") Long id, Model model) {

		Optional<Employee> optional = Optional.ofNullable(employeeService.getEmployeeById(id));
		Employee employee = null;
		if (optional.isPresent()) {
			employee = optional.get();
			model.addAttribute("title", "Update - Employee Update Form");
			model.addAttribute("designationType", Designation.values());
			model.addAttribute("employee", employee);
			return "/employee/new-employee";
		}else
			throw new EmployeeIdNotFoundException("Id Not Found !!! ");

	}


	// Delete the employee and redirect to the current page
	@GetMapping("/delete-employee")
	public String deleteEmployee(@RequestParam("id") Long id) {
			this.employeeService.deleteEmployeeById(id);
			return "redirect:/employee/home";
	}


	// Pagination work
	@GetMapping("/page")
	public String findPaginated(@RequestParam("pageNo") int pageNo,
								@RequestParam("sortField") String sortField,
								@RequestParam("sortDir") String sortDir,
								@RequestParam(value = "keyword", required = false) String keyword,
								Model model) {

		int pageSize = 2;
		
		Page<Employee> page = employeeService.findPaginated(pageNo, pageSize, sortField, sortDir, keyword);
		List<Employee> listEmployees = page.getContent();

		model.addAttribute("title", "Home - Employee Management");

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("keyword", keyword);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listEmployees", listEmployees);
		return "/employee/index";
	}
}


