package com.bpao.supportsystem.users.application.port.in.departamento;

import com.bpao.supportsystem.users.domain.model.Departamento;

public interface UpdateDepartamentoUseCase {
    Departamento patchDepartamento(Integer departamentoId, Departamento partialDepartamento);
}
