package com.example.project1.service;

import com.example.project1.entity.CompanyEntity;
import com.example.project1.entity.EmployeeEntity;

import java.util.List;

public interface CompanyService {
	CompanyEntity saveCompany(CompanyEntity company);
	List<CompanyEntity> getAllCompanies();

	CompanyEntity getCompanyById(Long id);

	CompanyEntity updateCompany(Long id, CompanyEntity company);
	void deleteCompany(Long id);
}
