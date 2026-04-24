package com.bpao.supportsystem.locations.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Sede {
    private Integer id;
    private String nombre;
    private Boolean esActivo;
}
