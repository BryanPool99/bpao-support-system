package com.bpao.supportsystem.users.infrastructure.persistance.repository.departamento;

import com.bpao.supportsystem.users.infrastructure.persistance.entity.DepartamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataDepartamentoRepository extends JpaRepository<DepartamentoEntity, Integer> {

    boolean existsByNombreDeptoIgnoreCase(String nombreDepto);
}
