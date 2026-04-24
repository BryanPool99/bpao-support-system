package com.bpao.supportsystem.users.application.service;

import com.bpao.supportsystem.common.domain.exception.ResourceAlreadyExistsException;
import com.bpao.supportsystem.common.domain.util.StringNormalizerUtil;
import com.bpao.supportsystem.users.application.port.in.departamento.CreateDepartamentoUseCase;
import com.bpao.supportsystem.users.application.port.out.DepartamentoRepositoryPort;
import com.bpao.supportsystem.users.domain.model.Departamento;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DepartamentoService implements CreateDepartamentoUseCase {

    private final DepartamentoRepositoryPort departamentoRepositoryPort;

    @Override
    public Departamento createDepartamento(Departamento departamento) {
        log.info("Inicio de metodo createDepartamento en DepartamentoService(application)");
        // 1. Normalización para evitar valores con mayusuculas y minusculas
        String nombreNormalizado = StringNormalizerUtil.normalizeToUpper(departamento.getNombre());
        departamento.setNombre(nombreNormalizado);
        // 2. Validación de duplicados
        if(departamentoRepositoryPort.existsByName(nombreNormalizado)){
            throw new ResourceAlreadyExistsException("El departamento '" + nombreNormalizado + "' ya existe.");
        }
        return departamentoRepositoryPort.save(departamento);
    }
}
