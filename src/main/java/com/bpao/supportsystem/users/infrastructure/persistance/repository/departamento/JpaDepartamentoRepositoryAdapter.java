package com.bpao.supportsystem.users.infrastructure.persistance.repository.departamento;

import com.bpao.supportsystem.users.application.port.out.DepartamentoRepositoryPort;
import com.bpao.supportsystem.users.domain.model.Departamento;
import com.bpao.supportsystem.users.infrastructure.persistance.mapper.DepartamentoPersistenceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class JpaDepartamentoRepositoryAdapter implements DepartamentoRepositoryPort {

    private final SpringDataDepartamentoRepository springDataDepartamentoRepository;
    private final DepartamentoPersistenceMapper departamentoPersistenceMapper;

    @Override
    public Departamento save(Departamento departamento) {
        log.info("Inicio del método save en JpaDepartamentoRepositoryAdapter(infrastructure)");
        var departamentoEntity = departamentoPersistenceMapper.toEntity(departamento);
        var newDepartamentoEntity = springDataDepartamentoRepository.save(departamentoEntity);
        return departamentoPersistenceMapper.toDomain(newDepartamentoEntity);
    }

    @Override
    public boolean existsByName(String nombre) {
        log.info("Inicio del método existsByName en JpaDepartamentoRepositoryAdapter(infrastructure)");
        return springDataDepartamentoRepository.existsByNombreDeptoIgnoreCase(nombre);
    }
}
