package com.example.project1.service.impl;

import com.example.project1.entity.EmployeeEntity;
import com.example.project1.repository.EmployeeRepository;
import com.example.project1.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

//	@Autowired
//	EmployeeRepository employeeRepository;
	private final EmployeeRepository employeeRepository;

	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override
	public EmployeeEntity saveEmployee(EmployeeEntity employee) {
		employeeRepository.save(employee);
		return employee;
	}

	@Override
	public List<EmployeeEntity> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public EmployeeEntity getEmployeeById(Long id) {
		return employeeRepository.findById(id).get();
	}

	@Override
	public EmployeeEntity updateEmployee(Long id, EmployeeEntity employee) {
		Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(id);
		if(employeeEntity.isPresent()){
			EmployeeEntity newEmployee = employeeEntity.get();
			newEmployee.setFullName(employee.getFullName());
			newEmployee.setAddress(employee.getAddress());
			newEmployee.setPhoneNumber(employee.getPhoneNumber());
			employeeRepository.save(newEmployee); // lưu vào db
			return newEmployee;
		}
		return null;
	}

	@Override
	public void deleteEmployee(Long id) {
		Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(id);
		if(employeeEntity.isPresent()){
			employeeRepository.deleteById(id);
		}
	}
}
