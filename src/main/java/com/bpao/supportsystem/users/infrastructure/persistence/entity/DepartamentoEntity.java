package com.bpao.supportsystem.users.infrastructure.persistence.entity;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "departamento_seq")
    @SequenceGenerator(name = "departamento_seq", sequenceName = "departamento_seq", allocationSize = 1)
    private Integer idDepartamento;

    @Column(name = "nombre", unique = true, nullable = false, length = 100)
    private String nombreDepto;

    @Column(name = "activo")
    private Integer activo;
}
