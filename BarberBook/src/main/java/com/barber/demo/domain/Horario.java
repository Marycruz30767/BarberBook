package com.barber.demo.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Data;

@Data
@Entity
@Table(name = "horario")
public class Horario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_horario")
    private Integer idHorario;

    private LocalDate fecha;
    private LocalTime hora;
    private Boolean disponible;

    @ManyToOne
    @JoinColumn(name = "id_empleado")
    private Empleado empleado;
}