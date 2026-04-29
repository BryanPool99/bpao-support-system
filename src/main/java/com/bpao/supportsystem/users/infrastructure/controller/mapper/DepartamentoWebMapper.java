package com.bpao.supportsystem.users.infrastructure.controller.mapper;

import com.bpao.supportsystem.users.domain.model.Departamento;
import com.bpao.supportsystem.users.infrastructure.controller.dto.request.DepartamentoCreateRequestDto;
import com.bpao.supportsystem.users.infrastructure.controller.dto.request.DepartamentoUpdateRequestDto;
import com.bpao.supportsystem.users.infrastructure.controller.dto.response.DepartamentoCreateResponseDto;
import com.bpao.supportsystem.users.infrastructure.controller.dto.response.DepartamentoResponseDto;
import org.springframework.stereotype.Component;

@Component
public class DepartamentoWebMapper {
    public Departamento createRequestToDomain(DepartamentoCreateRequestDto createRequestDto) {
        if (createRequestDto == null) return null;

        return Departamento.builder()
                .nombre(createRequestDto.getName())
                .esActivo(createRequestDto.getIsActive())
                .build();
    }

    public DepartamentoCreateRequestDto toCreateRequestDto(Departamento domain) {
        if (domain == null) return null;

        return DepartamentoCreateRequestDto.builder()
                .name(domain.getNombre())
                .isActive(domain.getEsActivo())
                .build();
    }

    public Departamento updateRequestToDomain(DepartamentoUpdateRequestDto updateRequestDto) {
        if (updateRequestDto == null) return null;

        return Departamento.builder()
                .nombre(updateRequestDto.getName())
                .esActivo(updateRequestDto.getIsActive())
                .build();
    }

    public DepartamentoCreateResponseDto toCreateResponseDto(Departamento domain) {
        if (domain == null) return null;

        return DepartamentoCreateResponseDto.builder()
                .id(domain.getId())
                .name(domain.getNombre())
                .isActive(domain.getEsActivo())
                .build();
    }

    public DepartamentoResponseDto toResponseDto(Departamento domain) {
        if (domain == null) return null;

        return DepartamentoResponseDto.builder()
                .id(domain.getId())
                .name(domain.getNombre())
                .isActive(domain.getEsActivo())
                .build();
    }
}
