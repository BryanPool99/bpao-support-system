package com.bpao.supportsystem.users.infrastructure.controller;

import com.bpao.supportsystem.users.application.port.in.departamento.CreateDepartamentoUseCase;
import com.bpao.supportsystem.users.infrastructure.controller.dto.request.DepartamentoCreateRequestDto;
import com.bpao.supportsystem.users.infrastructure.controller.dto.response.DepartamentoCreateResponseDto;
import com.bpao.supportsystem.users.infrastructure.controller.mapper.DepartamentoWebMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/departamento")
@RequiredArgsConstructor
public class DepartamentoController {

    private final CreateDepartamentoUseCase createDepartamentoUseCase;
    private final DepartamentoWebMapper departamentoWebMapper;

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
