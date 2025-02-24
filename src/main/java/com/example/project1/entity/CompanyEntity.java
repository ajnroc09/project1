package com.example.project1.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="companies")
public class CompanyEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	@Column(name = "name")
	private String name;
	@Column(name = "phone_number")
	private String phoneNumber;
	@Column(name = "tax")
	private String tax;
	// tìm hiểu các loại fetch, khi nào dùng lazy, khi nào nên dùng loại nào, khi không khai báo thì mặc định là gì
	// tìm hiểu các những cascade nào, khi nào nên dùng loại nào, khi không khai báo thì mặc định là gì
	// mappedBy sẽ truyền vào tên biến liên kết ứng với bên Many, trong trường hợp này bên EmployeeEntity là biến companyEntity
	@OneToMany(mappedBy = "companyEntity", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<EmployeeEntity> employees;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	public List<EmployeeEntity> getEmployees() {
		return employees;
	}

	public void setEmployees(List<EmployeeEntity> employees) {
		this.employees = employees;
	}
}
