package com.bpao.supportsystem.users.application.port.out;

import com.bpao.supportsystem.users.domain.model.Departamento;

public interface DepartamentoRepositoryPort {
    Departamento save(Departamento departamento);
}
