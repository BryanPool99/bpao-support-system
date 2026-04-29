package com.bpao.supportsystem.users.infrastructure.persistence.mapper;

import com.bpao.supportsystem.users.domain.model.Departamento;
import com.bpao.supportsystem.users.infrastructure.persistence.entity.DepartamentoEntity;
import org.springframework.stereotype.Component;

@Component
public class DepartamentoPersistenceMapper {
    public Departamento toDomain(DepartamentoEntity entity) {
        if (entity == null) return null;

        return Departamento.builder()
                .id(entity.getIdDepartamento())
                .nombre(entity.getNombreDepto())
                .esActivo(entity.getActivo() != null && entity.getActivo().equals(1))
                .build();
    }

    public DepartamentoEntity toEntity(Departamento domain) {
        if (domain == null) return null;
        return DepartamentoEntity.builder()
                .idDepartamento(domain.getId())
                .nombreDepto(domain.getNombre())
                .activo(Boolean.TRUE.equals(domain.getEsActivo()) ? 1 : 0)
                .build();
    }
}
