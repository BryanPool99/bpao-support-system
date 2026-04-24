package com.bpao.supportsystem.users.infrastructure.controller.mapper;

import com.bpao.supportsystem.users.domain.model.Departamento;
import com.bpao.supportsystem.users.infrastructure.controller.dto.request.DepartamentoCreateRequestDto;
import com.bpao.supportsystem.users.infrastructure.controller.dto.response.DepartamentoCreateResponseDto;
import com.bpao.supportsystem.users.infrastructure.controller.dto.response.DepartamentoResponseDto;
import org.springframework.stereotype.Component;

@Component
public class DepartamentoWebMapper {
    public Departamento creteRequestToDomain(DepartamentoCreateRequestDto createRequestDto) {
        if (createRequestDto==null) return null;

        return Departamento.builder()
                .nombre(createRequestDto.getName())
                .esActivo(createRequestDto.getIsActive())
                .build();
    }

    public DepartamentoCreateRequestDto toCreateRequestDto(Departamento domain) {
        if (domain==null) return null;

        return DepartamentoCreateRequestDto.builder()
                .name(domain.getNombre())
                .isActive(domain.getEsActivo())
                .build();
    }

    public Departamento createResponseToDomain(DepartamentoCreateResponseDto createResponseDto) {
        if (createResponseDto==null) return null;

        return Departamento.builder()
                .id(createResponseDto.getId())
                .nombre(createResponseDto.getName())
                .esActivo(createResponseDto.getIsActive())
                .build();
    }

    public DepartamentoCreateResponseDto toCreateResponseDto(Departamento domain) {
        if (domain==null) return null;

        return DepartamentoCreateResponseDto.builder()
                .id(domain.getId())
                .name(domain.getNombre())
                .isActive(domain.getEsActivo())
                .build();
    }

    public DepartamentoResponseDto toResponseDto(Departamento domain) {
        if (domain==null) return null;

        return DepartamentoResponseDto.builder()
                .id(domain.getId())
                .name(domain.getNombre())
                .isActive(domain.getEsActivo())
                .build();
    }

    public Departamento responseDtoToDomain(DepartamentoResponseDto responseDto) {
        if (responseDto==null) return null;

        return Departamento.builder()
                .id(responseDto.getId())
                .nombre(responseDto.getName())
                .esActivo(responseDto.getIsActive())
                .build();
    }
}
