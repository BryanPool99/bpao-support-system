package com.bpao.supportsystem.users.application.port.in.departamento;

import com.bpao.supportsystem.common.domain.model.PageResponse;
import com.bpao.supportsystem.users.domain.model.Departamento;

public interface RetrieveDepartamentoUseCase {
    Departamento getDepartamentoById(Integer departamentoId);

    PageResponse<Departamento> getDepartamentoAllPaginated(int page, int size);
}
