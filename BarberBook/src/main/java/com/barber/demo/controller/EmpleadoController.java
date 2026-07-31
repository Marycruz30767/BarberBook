package com.barber.demo.controller;

import com.barber.demo.domain.Empleado;
import com.barber.demo.domain.Reserva;
import com.barber.demo.domain.Usuario;
import com.barber.demo.service.EmpleadoService;
import com.barber.demo.service.ReservaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private ReservaService reservaService;

    @GetMapping("/empleado/citas")
    public String citasAsignadas(Model model, HttpSession session) {

        Usuario usuario = (Usuario) session.getAttribute("usuario");
        Empleado empleado = empleadoService.getEmpleadoPorUsuario(usuario);

        var reservas = reservaService.getReservasPorEmpleado(empleado);

        model.addAttribute("reservas", reservas);

        return "empleado/citas";
    }

    @PostMapping("/empleado/cita/estado")
    public String actualizarEstado(
            @RequestParam("idReserva") Integer idReserva,
            @RequestParam("estado") String estado) {

        Reserva reserva = new Reserva();
        reserva.setIdReserva(idReserva);

        reserva = reservaService.getReserva(reserva);
        reserva.setEstado(estado);

        reservaService.save(reserva);

        return "redirect:/empleado/citas";
    }
}