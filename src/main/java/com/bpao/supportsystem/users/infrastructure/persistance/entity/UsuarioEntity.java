package com.bpao.supportsystem.users.infrastructure.persistance.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    @NotNull(message = "El nombre completo del usuario es obligatorio")
    @Size(max = 200, message = "El nombre completo del usuario debe tener maximo 200 caracteres")
    private String nombreCompleto;

    @NotNull(message = "El correo del usuario es obligatorio")
    @Size(max = 200, message = "El correo del usuario debe tener maximo 200 caracteres")
    @Email(message = "El formato del correo no es válido")
    @Column(unique = true)
    private String correo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_departamento")
    private DepartamentoEntity departamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rol")
    private RolEntity rol;

    private Integer activo;
}
