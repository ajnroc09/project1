package com.example.project1.service.impl;

import com.example.project1.component.CompanyMapper;
import com.example.project1.component.EmployeeMapper;
import com.example.project1.dto.CompanyDTO;
import com.example.project1.entity.CompanyEntity;
import com.example.project1.entity.EmployeeEntity;
import com.example.project1.exception.CompanyNotFoundException;
import com.example.project1.repository.CompanyRepository;
import com.example.project1.repository.EmployeeRepository;
import com.example.project1.service.CompanyService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final EmployeeRepository employeeRepository;
    private final CompanyMapper companyMapper;
    private final EmployeeMapper employeeMapper;


    public CompanyServiceImpl(CompanyRepository companyRepository, EmployeeRepository employeeRepository, EmployeeMapper employeeMapper, CompanyMapper companyMapper) {

        this.companyRepository = companyRepository;
        this.companyMapper = companyMapper;
        this.employeeMapper = employeeMapper;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public CompanyDTO saveCompany(CompanyDTO companyDTO) {
        CompanyEntity companyEntity = companyMapper.toEntity(companyDTO);
        companyRepository.save(companyEntity);
        return companyMapper.toDTO(companyEntity);
    }

    @Override
    public List<CompanyDTO> getAllCompanies() {
        List<CompanyEntity> companyEntities = companyRepository.findAll();
        //
//        List<CompanyDTO> companyDTOs = new ArrayList<>();
//        for(CompanyEntity company: companyEntities){
//            companyDTOs.add(companyMapper.toDTO(company));
//        }
//        return companyDTOs;
        // duyệt qua từng phần tử của list company entity để map sang dto
        return companyEntities.stream()
                .map(companyMapper::toDTO)  // tương đương:       .map(e -> companyMapper.toDTO(e))
                .collect(Collectors.toList());
    }

    @Override
    public CompanyDTO getCompanyById(String id) throws CompanyNotFoundException {
        //---------------
        CompanyEntity companyEntity = companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException("Company not found"));

        // lấy danh sách nhân viên của công ty:
        List<EmployeeEntity> employeeEntities = employeeRepository.findAllByCompanyId(companyEntity.getId());
        CompanyDTO companyDTO = companyMapper.toDTO(companyEntity);
        companyDTO.setEmployees(
                employeeEntities.stream()
                        .map(employeeMapper::toDTO)
                        .collect(Collectors.toList())
        );
        return companyDTO;
    }

    @Override
    public CompanyDTO updateCompany(String id, CompanyDTO companyDTO) throws CompanyNotFoundException {
        // tìm công ty muống cập nhật
        CompanyEntity companyEntity = companyRepository.findById(id)
                .orElseThrow(()->new CompanyNotFoundException("Company not found"));
        // map từ công ty mới sang công ty đã tồn tại
        companyMapper.updateEntity(companyDTO, companyEntity);
        // lưu vào trong db
        companyRepository.save(companyEntity);
        // những phương thức giao tiếp với db thông qua repository phải là entity
        return companyMapper.toDTO(companyEntity);
    }

    @Override
    public void deleteCompany(String id) throws CompanyNotFoundException {
        CompanyEntity companyEntity = companyRepository.findById(id)
                .orElseThrow(()->new CompanyNotFoundException("Company not found"));
        companyRepository.delete(companyEntity);
    }
}
