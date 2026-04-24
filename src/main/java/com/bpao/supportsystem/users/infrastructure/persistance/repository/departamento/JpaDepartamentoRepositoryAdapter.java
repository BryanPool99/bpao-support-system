package com.bpao.supportsystem.users.infrastructure.persistance.repository.departamento;

import com.bpao.supportsystem.common.domain.model.PageResponse;
import com.bpao.supportsystem.users.application.port.out.DepartamentoRepositoryPort;
import com.bpao.supportsystem.users.domain.model.Departamento;
import com.bpao.supportsystem.users.infrastructure.persistance.entity.DepartamentoEntity;
import com.bpao.supportsystem.users.infrastructure.persistance.mapper.DepartamentoPersistenceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Override
    public PageResponse<Departamento> findAll(int page, int size) {
        log.info("Inicio del método findAll en JpaDepartamentoRepositoryAdapter(infrastructure) con " +
                 "page:{} y size:{}", page, size);
        Pageable pageable = PageRequest.of(page, size);
        Page<DepartamentoEntity> entityPage = springDataDepartamentoRepository.findAll(pageable);
        //Convertimos la lista de entidades a dominio
        List<Departamento> data = entityPage.getContent().stream()
                .map(departamentoPersistenceMapper::toDomain)
                .toList();
        return new PageResponse<>(
                data,
                entityPage.getNumber(),
                entityPage.getSize(),
                entityPage.getTotalElements(),
                entityPage.getTotalPages(),
                entityPage.isLast()
        );
    }
}
