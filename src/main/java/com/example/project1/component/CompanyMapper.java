package com.example.project1.component;

import com.example.project1.dto.CompanyDTO;
import com.example.project1.entity.CompanyEntity;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {
    public void updateEntity(CompanyDTO dto, CompanyEntity entity) {
        if (dto == null || entity == null) {
            return;
        }
        entity.setName(dto.getName());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setTax(dto.getTax());
    }

    public CompanyEntity toEntity(CompanyDTO dto) {
        if (dto == null) {
            return null;
        }
        CompanyEntity entity = new CompanyEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setTax(dto.getTax());
        return entity;
    }

    public CompanyDTO toDTO(CompanyEntity entity) {
        if (entity == null) {
            return null;
        }
        CompanyDTO dto = new CompanyDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setTax(entity.getTax());
        return dto;
    }


}

