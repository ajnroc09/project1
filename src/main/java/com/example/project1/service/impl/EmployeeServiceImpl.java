package com.example.project1.service.impl;

import com.example.project1.component.CompanyMapper;
import com.example.project1.component.EmployeeMapper;
import com.example.project1.dto.CompanyDTO;
import com.example.project1.dto.EmployeeDTO;
import com.example.project1.entity.CompanyEntity;
import com.example.project1.entity.EmployeeEntity;
import com.example.project1.exception.CompanyNotFoundException;
import com.example.project1.exception.EmployeeNotFoundException;
import com.example.project1.repository.CompanyRepository;
import com.example.project1.repository.EmployeeRepository;
import com.example.project1.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    //	@Autowired
//	EmployeeRepository employeeRepository;
    private final EmployeeRepository employeeRepository;
    private final CompanyRepository companyRepository;
    private final EmployeeMapper employeeMapper;
    private final CompanyMapper companyMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, CompanyRepository companyRepository, EmployeeMapper employeeMapper, CompanyMapper companyMapper) {

        this.employeeRepository = employeeRepository;
        this.companyRepository = companyRepository;
        this.employeeMapper = employeeMapper;
        this.companyMapper = companyMapper;
    }

    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) throws CompanyNotFoundException {
        EmployeeEntity employeeEntity = employeeMapper.toEntity(employeeDTO);
        // kiểm tra có tồn tại công ty không
        CompanyEntity companyEntity = companyRepository.findById(employeeDTO.getCompany().getId())
                .orElseThrow(() -> new CompanyNotFoundException("Company not found"));
        employeeEntity.setCompanyEntity(companyEntity);
        employeeRepository.save(employeeEntity);
        return employeeMapper.toDTO(employeeEntity);
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        return employeeEntities.stream()
                .map(employeeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO getEmployeeById(String id) throws EmployeeNotFoundException {
        EmployeeEntity employeeEntity = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
        EmployeeDTO employeeDTO = employeeMapper.toDTO(employeeEntity);
        employeeDTO.setCompany(companyMapper.toDTO(employeeEntity.getCompanyEntity()));
        return employeeDTO;

    }

    @Override
    public EmployeeDTO updateEmployee(String id, EmployeeDTO employeeDTO) throws EmployeeNotFoundException, CompanyNotFoundException {
        EmployeeEntity employeeEntity = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
        employeeMapper.updateEntity(employeeDTO,employeeEntity);
        CompanyEntity companyEntity = companyRepository.findById(employeeDTO.getCompany().getId())
                .orElseThrow(() -> new CompanyNotFoundException("Company not found"));
        employeeEntity.setCompanyEntity(companyEntity);
        employeeRepository.save(employeeEntity);
        return employeeMapper.toDTO(employeeEntity);
    }

    @Override
    public void deleteEmployee(String id) throws EmployeeNotFoundException {
        EmployeeEntity employeeEntity = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
        employeeRepository.delete(employeeEntity);
    }

    @Override
    public List<EmployeeDTO> getAllEmployeesByCompanyId(String companyId) throws CompanyNotFoundException {
        CompanyEntity companyEntity = companyRepository.findById(companyId)
                .orElseThrow(() -> new CompanyNotFoundException("Company not found"));
        return employeeRepository.findAllByCompanyId(companyEntity.getId())
                .stream()
                .map(employeeMapper::toDTO)
                .collect(Collectors.toList());

    }
}
