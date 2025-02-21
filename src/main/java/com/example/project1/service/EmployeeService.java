package com.example.project1.service;

import com.example.project1.entity.EmployeeEntity;

import java.util.List;

public interface EmployeeService {
	EmployeeEntity saveEmployee(EmployeeEntity employee);
	List<EmployeeEntity> getAllEmployees();

	EmployeeEntity getEmployeeById(Long id);

	EmployeeEntity updateEmployee(Long id, EmployeeEntity employee); // ket hop giưa save va get

	void deleteEmployee(Long id); //void do khong can tra ve

}
