package com.example.project1.controller;

import com.example.project1.dto.CompanyDTO;
import com.example.project1.entity.CompanyEntity;
import com.example.project1.exception.CompanyNotFoundException;
import com.example.project1.service.impl.CompanyServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/companies")
public class CompanyController {

	//@Autowired
	private final CompanyServiceImpl companyService;
	public CompanyController (CompanyServiceImpl companyService) {
		this.companyService = companyService;
	}

	//them moi 1 cty
	@PostMapping("/save")
	public ResponseEntity<CompanyDTO> saveCompany(@RequestBody CompanyDTO companyDTO){
		return new ResponseEntity<>(companyService.saveCompany(companyDTO), HttpStatus.OK);
	}
	// xem chi tiết cty
	@GetMapping("/detail/{id}")
	public ResponseEntity<CompanyDTO> getDetailCompany(@PathVariable("id") Long id) throws CompanyNotFoundException {
		return new ResponseEntity<>(companyService.getCompanyById(id),HttpStatus.OK);
	}

	// lấy toàn bộ cty
	@GetMapping("/get-all")
	public ResponseEntity<List<CompanyDTO>> getAllCompanies(){
		return new ResponseEntity<>(companyService.getAllCompanies(),HttpStatus.OK);
	}

	// cập nhật cty
	@PutMapping("/update/{id}")
	public ResponseEntity<CompanyDTO> updateCompany(@PathVariable("id") Long id, @RequestBody CompanyDTO companyDTO) throws CompanyNotFoundException {
		return new ResponseEntity<>(companyService.updateCompany(id, companyDTO),HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteCompany(@PathVariable("id") Long id) throws CompanyNotFoundException {
		companyService.deleteCompany(id);
		return new ResponseEntity<>("delete successfully!",HttpStatus.OK);
	}

}
