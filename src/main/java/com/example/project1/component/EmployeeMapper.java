package com.example.project1.component;

import com.example.project1.dto.EmployeeDTO;
import com.example.project1.entity.EmployeeEntity;
import org.springframework.stereotype.Component;
@Component
public class EmployeeMapper {

    public void updateEntity(EmployeeDTO dto, EmployeeEntity entity) {
        if (dto == null || entity == null) {
            return;
        }
        entity.setFullName(dto.getFullName());
        entity.setAddress(dto.getAddress());
        entity.setPhoneNumber(dto.getPhoneNumber());
    }

    public EmployeeEntity toEntity(EmployeeDTO dto) {
        if (dto == null) {
            return null;
        }
        EmployeeEntity entity = new EmployeeEntity();
        entity.setId(dto.getId());
        entity.setFullName(dto.getFullName());
        entity.setAddress(dto.getAddress());
        entity.setPhoneNumber(dto.getPhoneNumber());
        return entity;
    }

    public EmployeeDTO toDTO(EmployeeEntity entity) {
        if (entity == null) {
            return null;
        }
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(entity.getId());
        dto.setFullName(entity.getFullName());
        dto.setAddress(entity.getAddress());
        dto.setPhoneNumber(entity.getPhoneNumber());
        return dto;
    }
}