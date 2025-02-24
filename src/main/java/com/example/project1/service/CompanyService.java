package com.example.project1.service;

import com.example.project1.dto.CompanyDTO;
import com.example.project1.entity.CompanyEntity;
import com.example.project1.entity.EmployeeEntity;
import com.example.project1.exception.CompanyNotFoundException;

import java.util.List;

public interface CompanyService {
	CompanyDTO saveCompany(CompanyDTO companyDTO);
	List<CompanyDTO> getAllCompanies();

	CompanyDTO getCompanyById(String id) throws CompanyNotFoundException;

	CompanyDTO updateCompany(String id, CompanyDTO company) throws CompanyNotFoundException;
	void deleteCompany(String id) throws CompanyNotFoundException;
}
