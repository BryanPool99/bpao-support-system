package com.bpao.supportsystem.users.infrastructure.controller.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartamentoCreateResponseDto {
    private Integer id;
    private String name;
    private Boolean isActive;
}
