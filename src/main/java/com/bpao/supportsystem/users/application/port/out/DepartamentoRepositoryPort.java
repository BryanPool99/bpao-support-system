package com.bpao.supportsystem.users.application.port.out;

import com.bpao.supportsystem.common.domain.model.PageResponse;
import com.bpao.supportsystem.users.domain.model.Departamento;

public interface DepartamentoRepositoryPort {
    Departamento save(Departamento departamento);

    boolean existsByName(String nombre);

    PageResponse<Departamento> findAll(int page, int size);
}
