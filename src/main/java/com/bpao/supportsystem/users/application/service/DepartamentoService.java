package com.bpao.supportsystem.users.application.service;

import com.bpao.supportsystem.common.domain.exception.ResourceAlreadyExistsException;
import com.bpao.supportsystem.common.domain.exception.ResourceNotFoundException;
import com.bpao.supportsystem.common.domain.model.PageResponse;
import com.bpao.supportsystem.common.domain.util.StringNormalizerUtil;
import com.bpao.supportsystem.users.application.port.in.departamento.CreateDepartamentoUseCase;
import com.bpao.supportsystem.users.application.port.in.departamento.DeleteDepartamentoUseCase;
import com.bpao.supportsystem.users.application.port.in.departamento.RetrieveDepartamentoUseCase;
import com.bpao.supportsystem.users.application.port.in.departamento.UpdateDepartamentoUseCase;
import com.bpao.supportsystem.users.application.port.out.DepartamentoRepositoryPort;
import com.bpao.supportsystem.users.domain.model.Departamento;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DepartamentoService implements CreateDepartamentoUseCase, RetrieveDepartamentoUseCase, UpdateDepartamentoUseCase, DeleteDepartamentoUseCase {

    private final DepartamentoRepositoryPort departamentoRepositoryPort;

    @Override
    @Transactional
    public Departamento createDepartamento(Departamento departamento) {
        log.info("Inicio de metodo createDepartamento en DepartamentoService(application)");
        String nombreNormalizado = StringNormalizerUtil.normalizeToUpper(departamento.getNombre());
        departamento.setNombre(nombreNormalizado);
        if (departamentoRepositoryPort.existsByName(nombreNormalizado)) {
            throw new ResourceAlreadyExistsException("El departamento '" + nombreNormalizado + "' ya existe.");
        }
        return departamentoRepositoryPort.save(departamento);
    }

    @Override
    @Transactional(readOnly = true)
    public Departamento getDepartamentoById(Integer departamentoId) {
        log.info("Inicio del método getDepartamentoById en DepartamentoService(application) con id:{}", departamentoId);
        return departamentoRepositoryPort.findById(departamentoId);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<Departamento> getDepartamentoAllPaginated(int page, int size) {
        log.info("Inicio del método getDepartamentoAllPaginated con page:{} y size:{}", page, size);
        return departamentoRepositoryPort.findAll(page, size);
    }

    @Override
    @Transactional
    public Departamento patchDepartamento(Integer departamentoId, Departamento partialDepartamento) {
        log.info("Inicio del método patchDepartamento en DepartamentoService(application) con id:{}", departamentoId);
        Departamento existingDepartamento = departamentoRepositoryPort.findById(departamentoId);

        String nombreAActualizar = partialDepartamento.getNombre() != null
                ? StringNormalizerUtil.normalizeToUpper(partialDepartamento.getNombre())
                : existingDepartamento.getNombre();

        if (partialDepartamento.getNombre() != null && !nombreAActualizar.equals(existingDepartamento.getNombre())) {
            if (departamentoRepositoryPort.existsByName(nombreAActualizar)) {
                throw new ResourceAlreadyExistsException("El departamento '" + nombreAActualizar + "' ya existe.");
            }
        }

        Departamento departamentoActualizado = Departamento.builder()
                .id(existingDepartamento.getId())
                .nombre(nombreAActualizar)
                .esActivo(partialDepartamento.getEsActivo() != null ? partialDepartamento.getEsActivo() : existingDepartamento.getEsActivo())
                .build();

        return departamentoRepositoryPort.save(departamentoActualizado);
    }

    @Override
    @Transactional
    public void deleteDepartamento(Integer departamentoId) {
        log.info("Inicio del método deleteDepartamento en DepartamentoService(application) con id:{}", departamentoId);
        departamentoRepositoryPort.deleteById(departamentoId);
    }
}
