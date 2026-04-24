package com.bpao.supportsystem.users.application.service;

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
        return departamentoRepositoryPort.save(departamento);
    }
}
