package com.example.project1.controller;

import com.example.project1.dto.EmployeeDTO;
import com.example.project1.entity.EmployeeEntity;
import com.example.project1.exception.CompanyNotFoundException;
import com.example.project1.exception.EmployeeNotFoundException;
import com.example.project1.service.impl.EmployeeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    //	@Autowired
//	EmployeeServiceImpl employeeService;
    private final EmployeeServiceImpl employeeService;

    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    //------------------------
    // thêm mới nhân viên
    @PostMapping("/save")
    public ResponseEntity<EmployeeDTO> saveEmployee(@RequestBody EmployeeDTO employeeDTO) throws CompanyNotFoundException {
        return new ResponseEntity<>(employeeService.saveEmployee(employeeDTO), HttpStatus.OK);
    }

    // xem chi tiết nhân viên
    @GetMapping("/detail/{id}")
    public ResponseEntity<EmployeeDTO> getDetailEmployee(@PathVariable("id") String id) throws EmployeeNotFoundException {
        return new ResponseEntity<>(employeeService.getEmployeeById(id), HttpStatus.OK);
    }

    // lấy toàn bộ nhân viên
    @GetMapping("/get-all")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployee() {
        return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    // cập nhật nhân viên
    @PutMapping("/update/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable("id") String id, @RequestBody EmployeeDTO employeeDTO) throws CompanyNotFoundException, EmployeeNotFoundException {
        return new ResponseEntity<>(employeeService.updateEmployee(id, employeeDTO), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") String id) throws EmployeeNotFoundException {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>("delete successfully!", HttpStatus.OK);
    }

    // lấy toàn bộ danh sách nhân viên của một công ty; cần truyền vào id của công ty cần lấy
    @GetMapping("/{company_id}/get-all-employees")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(@PathVariable("company_id") String companyId) throws CompanyNotFoundException {
        return new ResponseEntity<>(employeeService.getAllEmployeesByCompanyId(companyId), HttpStatus.OK);
    }

}
