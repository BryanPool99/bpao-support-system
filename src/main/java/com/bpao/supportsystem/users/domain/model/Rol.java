package com.bpao.supportsystem.users.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Rol {
    private Integer id;
    private String nombre;
    private String descripcion;
    private Boolean esActivo;
}
