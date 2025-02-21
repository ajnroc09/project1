package com.example.project1.controller;

import com.example.project1.entity.EmployeeEntity;
import com.example.project1.service.impl.EmployeeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

//	@Autowired
//	EmployeeServiceImpl employeeService;
		private final EmployeeServiceImpl employeeService;
		public EmployeeController (EmployeeServiceImpl employeeService) {
			this.employeeService = employeeService;
		}
//------------------------
	// thêm mới nhân viên
	@PostMapping("/save")
	public ResponseEntity<EmployeeEntity> saveEmployee(@RequestBody EmployeeEntity employee){
		employeeService.saveEmployee(employee);
		return new ResponseEntity<>(employee, HttpStatus.OK);
	}

	// xem chi tiết nhân viên
	@GetMapping("/detail/{id}")
	public ResponseEntity<EmployeeEntity> getDetailEmployee(@PathVariable("id") Long id){
		return new ResponseEntity<>(employeeService.getEmployeeById(id),HttpStatus.OK);
	}

	// lấy toàn bộ nhân viên
	@GetMapping("/get-all")
	public ResponseEntity<List<EmployeeEntity>> getAllEmployee(){
		return new ResponseEntity<>(employeeService.getAllEmployees(),HttpStatus.OK);
	}

	// cập nhật nhân viên
	@PutMapping("/update/{id}")
	public ResponseEntity<EmployeeEntity> updateEmployee(@PathVariable("id") Long id, @RequestBody EmployeeEntity employee){
		return new ResponseEntity<>(employeeService.updateEmployee(id, employee),HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long id){
		employeeService.deleteEmployee(id);
		return new ResponseEntity<>("delete successfully!",HttpStatus.OK);
	}



}
