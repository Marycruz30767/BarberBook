package com.barber.demo.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@Entity
@Table(name = "bitacora_reserva")
public class BitacoraReserva implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bitacora")
    private Integer idBitacora;

    private String accion;

    @Column(name = "fecha_accion")
    private LocalDateTime fechaAccion;

    @ManyToOne
    @JoinColumn(name = "id_reserva")
    private Reserva reserva;
}