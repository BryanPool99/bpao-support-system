package com.bpao.supportsystem.users.infrastructure.persistence.repository.departamento;

import com.bpao.supportsystem.common.domain.exception.ResourceNotFoundException;
import com.bpao.supportsystem.common.domain.model.PageResponse;
import com.bpao.supportsystem.users.application.port.out.DepartamentoRepositoryPort;
import com.bpao.supportsystem.users.domain.model.Departamento;
import com.bpao.supportsystem.users.infrastructure.persistence.entity.DepartamentoEntity;
import com.bpao.supportsystem.users.infrastructure.persistence.mapper.DepartamentoPersistenceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class JpaDepartamentoRepositoryAdapter implements DepartamentoRepositoryPort {

    private final SpringDataDepartamentoRepository springDataDepartamentoRepository;
    private final DepartamentoPersistenceMapper departamentoPersistenceMapper;

    @Override
    @Transactional
    public Departamento save(Departamento departamento) {
        log.info("Inicio del método save en JpaDepartamentoRepositoryAdapter(infrastructure)");
        var departamentoEntity = departamentoPersistenceMapper.toEntity(departamento);
        var newDepartamentoEntity = springDataDepartamentoRepository.save(departamentoEntity);
        return departamentoPersistenceMapper.toDomain(newDepartamentoEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByName(String nombre) {
        log.info("Inicio del método existsByName en JpaDepartamentoRepositoryAdapter(infrastructure)");
        return springDataDepartamentoRepository.existsByNombreDeptoIgnoreCase(nombre);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<Departamento> findAll(int page, int size) {
        log.info("Inicio del método findAll en JpaDepartamentoRepositoryAdapter(infrastructure) con page:{} y size:{}", page, size);
        Pageable pageable = PageRequest.of(page, size);
        Page<DepartamentoEntity> entityPage = springDataDepartamentoRepository.findAll(pageable);
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

    @Override
    @Transactional(readOnly = true)
    public Departamento findById(Integer id) {
        log.info("Inicio del método findById en JpaDepartamentoRepositoryAdapter(infrastructure) con id:{}", id);
        DepartamentoEntity entity = springDataDepartamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Departamento con id " + id + " no encontrado."));
        return departamentoPersistenceMapper.toDomain(entity);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        log.info("Inicio del método deleteById en JpaDepartamentoRepositoryAdapter(infrastructure) con id:{}", id);
        if (!springDataDepartamentoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Departamento con id " + id + " no encontrado.");
        }
        springDataDepartamentoRepository.deleteById(id);
    }
}
