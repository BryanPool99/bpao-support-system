package com.bpao.supportsystem.users.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Usuario {
    private Integer id;
    private String nombreCompleto;
    private String correo;
    private Departamento departamento;
    private Rol rol;
    private Boolean esActivo;
}
