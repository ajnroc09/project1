package com.example.project1.repository;

import com.example.project1.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity,String> {

    @Query(value = "select * from employees e where e.company_id = :companyId",nativeQuery = true)
    List<EmployeeEntity> findAllByCompanyId(@Param("companyId") String companyId);
}
