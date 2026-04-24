package com.bpao.supportsystem.users.infrastructure.controller;

import com.bpao.supportsystem.common.domain.model.PageResponse;
import com.bpao.supportsystem.users.application.port.in.departamento.CreateDepartamentoUseCase;
import com.bpao.supportsystem.users.application.port.in.departamento.RetrieveDepartamentoUseCase;
import com.bpao.supportsystem.users.infrastructure.controller.dto.request.DepartamentoCreateRequestDto;
import com.bpao.supportsystem.users.infrastructure.controller.dto.response.DepartamentoCreateResponseDto;
import com.bpao.supportsystem.users.infrastructure.controller.dto.response.DepartamentoResponseDto;
import com.bpao.supportsystem.users.infrastructure.controller.mapper.DepartamentoWebMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/departamento")
@RequiredArgsConstructor
public class DepartamentoController {

    private final CreateDepartamentoUseCase createDepartamentoUseCase;
    private final RetrieveDepartamentoUseCase retrieveDepartamentoUseCase;
    private final DepartamentoWebMapper departamentoWebMapper;

    @GetMapping
    public PageResponse<DepartamentoResponseDto> getAllDepartamentosWithPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        var domainPage = retrieveDepartamentoUseCase.getDepartamentoAllPaginated(page, size);
        // Mapeamos el contenido de la página a DTOs de respuesta
        List<DepartamentoResponseDto> responseDtos = domainPage.getData().stream()
                .map(departamentoWebMapper::toResponseDto)
                .toList();
        // Creamos la respuesta paginada para el frontend
        return new PageResponse<>(
                responseDtos,
                domainPage.getPageNumber(),
                domainPage.getPageSize(),
                domainPage.getTotalElements(),
                domainPage.getTotalPages(),
                domainPage.isLast()
        );
    }

    @PostMapping
    public DepartamentoCreateResponseDto createDepartamento(
            @Valid @RequestBody DepartamentoCreateRequestDto requestDto
    ) {
        // 1. Usamos el webMapper para entrar al dominio
        var domainDepartamento = departamentoWebMapper.creteRequestToDomain(requestDto);
        // 2. Ejecutamos lógica
        var createDepartamento = createDepartamentoUseCase.createDepartamento(domainDepartamento);
        // 3. Usamos el webMapper para salir hacia el JSON
        return departamentoWebMapper.toCreateResponseDto(createDepartamento);
    }

}
