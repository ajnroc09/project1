package com.example.project1.service;

import com.example.project1.dto.EmployeeDTO;
import com.example.project1.entity.EmployeeEntity;
import com.example.project1.exception.CompanyNotFoundException;
import com.example.project1.exception.EmployeeNotFoundException;

import java.util.List;

public interface EmployeeService {
	EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) throws CompanyNotFoundException;
	List<EmployeeDTO> getAllEmployees();

	EmployeeDTO getEmployeeById(String id) throws EmployeeNotFoundException;

	EmployeeDTO updateEmployee(String id, EmployeeDTO employeeDTO) throws EmployeeNotFoundException, CompanyNotFoundException; // ket hop giưa save va get

	void deleteEmployee(String id) throws EmployeeNotFoundException; //void do khong can tra ve

	List<EmployeeDTO> getAllEmployeesByCompanyId(String companyId) throws CompanyNotFoundException;

}
