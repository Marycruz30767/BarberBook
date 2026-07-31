package com.barber.demo.service.impl;

import com.barber.demo.dao.EmpleadoDao;
import com.barber.demo.domain.Empleado;
import com.barber.demo.domain.Usuario;
import com.barber.demo.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    @Autowired
    private EmpleadoDao empleadoDao;

    @Override
    public Empleado getEmpleadoPorUsuario(Usuario usuario) {
        return empleadoDao.findByUsuario(usuario);
    }
}