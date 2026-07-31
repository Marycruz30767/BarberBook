package com.barber.demo.controller;

import com.barber.demo.service.ReservaService;
import com.barber.demo.service.ServicioService;
import com.barber.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.barber.demo.domain.Reserva;
import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class AdminController {

    @Autowired
    private ReservaService reservaService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ServicioService servicioService;

    @GetMapping("/admin/dashboard")
    public String dashboard(Model model) {

        model.addAttribute("totalReservas",
                reservaService.getTotalReservas());

        model.addAttribute("reservasPendientes",
                reservaService.getCantidadPorEstado("Pendiente"));

        model.addAttribute("reservasProceso",
                reservaService.getCantidadPorEstado("En proceso"));

        model.addAttribute("reservasCompletadas",
                reservaService.getCantidadPorEstado("Completada"));

        model.addAttribute("reservasCanceladas",
                reservaService.getCantidadPorEstado("Cancelada"));

        model.addAttribute("totalUsuarios",
                usuarioService.getTotalUsuarios());

        model.addAttribute("totalServicios",
                servicioService.getTotalServicios());

        return "admin/dashboard";
    }
    @GetMapping("/admin/reportes/citas")
public String reporteCitas(
        @RequestParam(value = "estado", required = false) String estado,
        Model model) {

    List<Reserva> reservas;

    if (estado == null || estado.isBlank() || estado.equals("Todas")) {
        reservas = reservaService.getTodasLasReservas();
    } else {
        reservas = reservaService.getReservasPorEstado(estado);
    }

    model.addAttribute("reservas", reservas);
    model.addAttribute("estadoSeleccionado", estado);

    return "admin/reporte-citas";
}
}