package com.bpao.supportsystem.users.infrastructure.persistance.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "roles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RolEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRol;

    @NotNull(message = "El nombre del rol es obligatorio")
    @Size(max = 50, message = "El nombre del rol debe tener maximo 50 caracteres")
    @Column(unique = true)
    private String nombreRol;

    @Size(max = 200, message = "La descripcion del rol debe tener maximo 200 caracteres")
    private String descripcion;

    private Integer activo;
}
