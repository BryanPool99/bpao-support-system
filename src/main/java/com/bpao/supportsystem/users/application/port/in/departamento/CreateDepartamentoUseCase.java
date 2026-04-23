package com.bpao.supportsystem.users.application.port.in.departamento;

import com.bpao.supportsystem.users.domain.model.Departamento;

public interface CreateDepartamentoUseCase {
    Departamento createDepartamento(Departamento departamento);
}
