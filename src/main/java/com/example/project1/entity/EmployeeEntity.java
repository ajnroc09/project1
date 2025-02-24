package com.example.project1.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "employees")
public class EmployeeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	@Column(name = "full_name")
	private String fullName;
	@Column(name = "address")
	private String address;
	@Column(name = "phone_number")
	private String phoneNumber;

	// 1.dùng joincolumn để thiết lập khóa ngoại trong db đặt tên là gì, khi khai báo kiểu dữ liệu là một entity CompanyEntity
	// 		thì jpa sẽ tự liên kết khóa ngoại company_id với khóa chính tương ứng với bảng của CompanyEntity
	// 2.trong mối quan hệ 1-n ví dụ như của employee-company thì @ManyToOne là bắt buộc cần thiết lập ở bên Many,
	//		còn @OneToMany có thể có hoặc không ở bên One. ở bên CompanyEntity tôi vẫn thiết lập @OneToMany, và hiện tại nên thiết lập đầy đủ.
	@ManyToOne
	@JoinColumn(name = "company_id",nullable = false)
	private CompanyEntity companyEntity;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public CompanyEntity getCompanyEntity() {
		return companyEntity;
	}

	public void setCompanyEntity(CompanyEntity companyEntity) {
		this.companyEntity = companyEntity;
	}
}
