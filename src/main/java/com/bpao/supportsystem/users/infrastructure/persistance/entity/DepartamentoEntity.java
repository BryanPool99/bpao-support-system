package com.bpao.supportsystem.users.infrastructure.persistance.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "departamentos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepartamentoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDepartamento;

    @NotNull(message = "El nombre del departamento es obligatorio")
    @Size(max = 100, message = "El nombre del departamento debe tener maximo 100 caracteres")
    @Column(unique = true)
    private String nombreDepto;

    private Integer activo;
}
