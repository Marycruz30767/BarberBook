package com.barber.demo.controller;

import com.barber.demo.domain.Horario;
import com.barber.demo.domain.Reserva;
import com.barber.demo.domain.Usuario;
import com.barber.demo.service.HorarioService;
import com.barber.demo.service.ReservaService;
import com.barber.demo.service.ServicioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @Autowired
    private ServicioService servicioService;

    @Autowired
    private HorarioService horarioService;

    @GetMapping("/reserva/nueva")
    public String nuevaReserva(Model model) {
        model.addAttribute("servicios", servicioService.getServicios());
        model.addAttribute("horarios", horarioService.getHorarios());
        model.addAttribute("reserva", new Reserva());
        return "reserva/nuevo";
    }

    @PostMapping("/reserva/guardar")
    public String guardarReserva(Reserva reserva, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        reserva.setUsuario(usuario);
        reserva.setEstado("Pendiente");

        Horario horario = reserva.getHorario();
        if (horario != null && horario.getIdHorario() != null) {
            horario = horarioService.getHorario(horario);
            reserva.setFecha(horario.getFecha());
            reserva.setHora(horario.getHora());
            reserva.setEmpleado(horario.getEmpleado());
            horario.setDisponible(false);
            horarioService.save(horario);
        }

       reservaService.save(reserva);

return "redirect:/reserva/confirmacion/" + reserva.getIdReserva();
    }

    @GetMapping("/reserva/mis-reservas")
    public String misReservas(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        var reservas = reservaService.getReservasPorUsuario(usuario);
        model.addAttribute("reservas", reservas);
        return "reserva/listado";
    }

    @GetMapping("/reserva/cancelar/{idReserva}")
    public String cancelar(Reserva reserva) {
        reserva = reservaService.getReserva(reserva);
        reserva.setEstado("Cancelada");
        reservaService.save(reserva);
        return "redirect:/reserva/mis-reservas";
    }

  @GetMapping("/reserva/confirmacion/{idReserva}")
public String confirmacion(Reserva reserva, Model model) {
    reserva = reservaService.getReserva(reserva);
    model.addAttribute("reserva", reserva);
    return "reserva/confirmacion";
}
}