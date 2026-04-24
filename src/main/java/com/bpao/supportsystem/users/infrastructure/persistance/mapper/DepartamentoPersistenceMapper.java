package com.bpao.supportsystem.users.infrastructure.persistance.mapper;

import com.bpao.supportsystem.users.domain.model.Departamento;
import com.bpao.supportsystem.users.infrastructure.persistance.entity.DepartamentoEntity;
import org.springframework.stereotype.Component;

@Component
public class DepartamentoPersistenceMapper {
    // Convierte de Entidad (Oracle) a Modelo (Negocio)
    public Departamento toDomain(DepartamentoEntity entity) {
        if (entity==null) return null;

        return Departamento.builder()
                .id(entity.getIdDepartamento())
                .nombre(entity.getNombreDepto())
                .esActivo(entity.getActivo().equals(1))
                .build();
    }

    // Convierte de Modelo (Negocio) a Entidad (Oracle)
    public DepartamentoEntity toEntity(Departamento domain) {
        if (domain==null) return null;
        return DepartamentoEntity.builder()
                .idDepartamento(domain.getId())
                .nombreDepto(domain.getNombre())
                .activo(domain.getEsActivo() ? 1:0)
                .build();
    }
}
