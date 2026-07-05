package com.barber.demo.service.impl;

import com.barber.demo.dao.HorarioDao;
import com.barber.demo.domain.Horario;
import com.barber.demo.service.HorarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HorarioServiceImpl implements HorarioService {

    @Autowired
    private HorarioDao horarioDao;

    @Override
    public List<Horario> getHorarios() {
        return horarioDao.findByDisponibleTrue();
    }

    @Override
    public Horario getHorario(Horario horario) {
        return horarioDao.findById(horario.getIdHorario()).orElse(null);
    }

    @Override
    public void save(Horario horario) {
        horarioDao.save(horario);
    }

    @Override
    public void delete(Horario horario) {
        horarioDao.delete(horario);
    }

}