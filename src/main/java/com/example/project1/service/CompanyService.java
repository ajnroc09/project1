package com.example.project1.service;

import com.example.project1.dto.CompanyDTO;
import com.example.project1.entity.CompanyEntity;
import com.example.project1.entity.EmployeeEntity;
import com.example.project1.exception.CompanyNotFoundException;

import java.util.List;

public interface CompanyService {
	CompanyDTO saveCompany(CompanyDTO companyDTO);
	List<CompanyDTO> getAllCompanies();

	CompanyDTO getCompanyById(Long id) throws CompanyNotFoundException;

	CompanyDTO updateCompany(Long id, CompanyDTO company) throws CompanyNotFoundException;
	void deleteCompany(Long id) throws CompanyNotFoundException;
}
