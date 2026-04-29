package com.bpao.supportsystem.users.infrastructure.controller.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepartamentoUpdateRequestDto {
    private String name;
    private Boolean isActive;
}
