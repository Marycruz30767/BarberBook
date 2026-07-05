package com.barber.demo.service.impl;

import com.barber.demo.dao.ServicioDao;
import com.barber.demo.domain.Servicio;
import com.barber.demo.service.ServicioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioServiceImpl implements ServicioService {

    @Autowired
    private ServicioDao servicioDao;

    @Override
    public List<Servicio> getServicios() {
        return servicioDao.findByActivoTrue();
    }

    @Override
    public Servicio getServicio(Servicio servicio) {
        return servicioDao.findById(servicio.getIdServicio()).orElse(null);
    }

    @Override
    public void save(Servicio servicio) {
        servicioDao.save(servicio);
    }

    @Override
    public void delete(Servicio servicio) {
        servicioDao.delete(servicio);
    }

}