package com.barber.demo.service;

import com.barber.demo.domain.Servicio;
import java.util.List;

public interface ServicioService {

    List<Servicio> getServicios();

    Servicio getServicio(Servicio servicio);

    void save(Servicio servicio);

    void delete(Servicio servicio);
}