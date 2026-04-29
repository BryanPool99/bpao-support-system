package com.bpao.supportsystem.users.infrastructure.controller;

import com.bpao.supportsystem.common.domain.model.PageResponse;
import com.bpao.supportsystem.users.application.port.in.departamento.CreateDepartamentoUseCase;
import com.bpao.supportsystem.users.application.port.in.departamento.DeleteDepartamentoUseCase;
import com.bpao.supportsystem.users.application.port.in.departamento.RetrieveDepartamentoUseCase;
import com.bpao.supportsystem.users.application.port.in.departamento.UpdateDepartamentoUseCase;
import com.bpao.supportsystem.users.infrastructure.controller.dto.request.DepartamentoCreateRequestDto;
import com.bpao.supportsystem.users.infrastructure.controller.dto.request.DepartamentoUpdateRequestDto;
import com.bpao.supportsystem.users.infrastructure.controller.dto.response.DepartamentoCreateResponseDto;
import com.bpao.supportsystem.users.infrastructure.controller.dto.response.DepartamentoResponseDto;
import com.bpao.supportsystem.users.infrastructure.controller.mapper.DepartamentoWebMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/departamentos")
@RequiredArgsConstructor
public class DepartamentoController {

    private final CreateDepartamentoUseCase createDepartamentoUseCase;
    private final RetrieveDepartamentoUseCase retrieveDepartamentoUseCase;
    private final UpdateDepartamentoUseCase updateDepartamentoUseCase;
    private final DeleteDepartamentoUseCase deleteDepartamentoUseCase;
    private final DepartamentoWebMapper departamentoWebMapper;

    @GetMapping
    public PageResponse<DepartamentoResponseDto> getAllDepartamentosWithPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        var domainPage = retrieveDepartamentoUseCase.getDepartamentoAllPaginated(page, size);
        List<DepartamentoResponseDto> responseDtos = domainPage.getData().stream()
                .map(departamentoWebMapper::toResponseDto)
                .toList();
        return new PageResponse<>(
                responseDtos,
                domainPage.getPageNumber(),
                domainPage.getPageSize(),
                domainPage.getTotalElements(),
                domainPage.getTotalPages(),
                domainPage.isLast()
        );
    }

    @GetMapping("/{id}")
    public DepartamentoResponseDto getDepartamentoById(@PathVariable Integer id) {
        var domainDepartamento = retrieveDepartamentoUseCase.getDepartamentoById(id);
        return departamentoWebMapper.toResponseDto(domainDepartamento);
    }

    @PostMapping
    public DepartamentoCreateResponseDto createDepartamento(
            @Valid @RequestBody DepartamentoCreateRequestDto requestDto
    ) {
        var domainDepartamento = departamentoWebMapper.createRequestToDomain(requestDto);
        var createdDepartamento = createDepartamentoUseCase.createDepartamento(domainDepartamento);
        return departamentoWebMapper.toCreateResponseDto(createdDepartamento);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DepartamentoResponseDto patchDepartamento(
            @PathVariable Integer id,
            @Valid @RequestBody DepartamentoUpdateRequestDto requestDto
    ) {
        var partialDomain = departamentoWebMapper.updateRequestToDomain(requestDto);
        var updatedDepartamento = updateDepartamentoUseCase.patchDepartamento(id, partialDomain);
        return departamentoWebMapper.toResponseDto(updatedDepartamento);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDepartamento(@PathVariable Integer id) {
        deleteDepartamentoUseCase.deleteDepartamento(id);
    }
}
