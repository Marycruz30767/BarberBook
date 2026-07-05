package com.barber.demo.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

@Data
@Entity
@Table(name = "pago")
public class Pago implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago")
    private Integer idPago;

    private BigDecimal monto;
    private String metodoPago;
    private String estado;

    @ManyToOne
    @JoinColumn(name = "id_reserva")
    private Reserva reserva;
}