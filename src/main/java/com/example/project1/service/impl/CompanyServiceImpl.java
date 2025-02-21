package com.example.project1.service.impl;

import com.example.project1.entity.CompanyEntity;
import com.example.project1.entity.EmployeeEntity;
import com.example.project1.repository.CompanyRepository;
import com.example.project1.service.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
	private final CompanyRepository companyRepository;
	public CompanyServiceImpl (CompanyRepository companyRepository) {
		this.companyRepository = companyRepository;
	}

	@Override
	public CompanyEntity saveCompany(CompanyEntity company) {
		companyRepository.save(company);
		return company;
	}

	@Override
	public List<CompanyEntity> getAllCompanies() {
		return companyRepository.findAll();
	}

	@Override
	public CompanyEntity getCompanyById(Long id) {
	//---------------
		Optional<CompanyEntity> companyEntity = companyRepository.findById(id);
		if(companyEntity.isPresent()) {
			return companyEntity.get();
		};


//		return companyRepository.findById(id).get();
		return null;
	//-----------------
	}

	@Override
	public CompanyEntity updateCompany(Long id, CompanyEntity company) {
		Optional<CompanyEntity> companyEntity= companyRepository.findById(id);
		if(companyEntity.isPresent()) {
			CompanyEntity newCompany= companyEntity.get();
			newCompany.setName(company.getName());
			newCompany.setPhoneNumber(company.getPhoneNumber());
			newCompany.setTax(company.getTax());
			companyRepository.save(newCompany); // lưu vào db
			// haha
			// kkk
			return newCompany;
		};
		return null;
	}

	@Override
	public void deleteCompany(Long id) {
		Optional<CompanyEntity> companyEntity= companyRepository.findById(id);
		if(companyEntity.isPresent()) {
			companyRepository.deleteById(id);
		};
	}
}
