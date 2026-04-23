package com.bpao.supportsystem.users.application.service;

import com.bpao.supportsystem.users.application.port.in.departamento.CreateDepartamentoUseCase;
import com.bpao.supportsystem.users.domain.model.Departamento;
import org.springframework.stereotype.Service;

@Service
public class DepartamentoService implements CreateDepartamentoUseCase {
    @Override
    public Departamento createDepartamento(Departamento departamento) {
        return null;
    }
}
