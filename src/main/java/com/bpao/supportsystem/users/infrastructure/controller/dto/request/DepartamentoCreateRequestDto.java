package com.bpao.supportsystem.users.infrastructure.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepartamentoCreateRequestDto {
    @NotNull(message = "El nombre del departamento es obligatorio")
    @NotBlank(message = "El nombre del departamento no debe estar en blanco")
    private String name;

    private Boolean isActive;
}
