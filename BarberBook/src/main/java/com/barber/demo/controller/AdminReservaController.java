package com.barber.demo.controller;

import com.barber.demo.domain.Usuario;
import com.barber.demo.service.ReservaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AdminReservaController {

    @Autowired
    private ReservaService reservaService;

    @GetMapping("/admin/reservas")
    public String listado(Model model, HttpSession session) {
        String redireccion = validarAdmin(session);
        if (redireccion != null) {
            return redireccion;
        }
        model.addAttribute("reservas", reservaService.getReservas());
        return "admin/reserva/listado";

    }

    @PostMapping("/admin/reservas/estado/{idReserva}")
    public String cambiarEstado(@PathVariable Integer idReserva, @RequestParam String estado, HttpSession session, RedirectAttributes redirectAttributes) {
        String redireccion = validarAdmin(session);
        if (redireccion != null) {
            return redireccion;
        }

        try {
            reservaService.actualizarEstado(idReserva, estado);
            redirectAttributes.addFlashAttribute("mensaje",
                    "El estado de la reserva se actualizó correctamente.");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/admin/reservas";
    }

    private String validarAdmin(HttpSession session) {
        Usuario usuarioSesion = (Usuario) session.getAttribute("usuario");
        if (usuarioSesion == null) {
            return "redirect:/login";
        }
        if (usuarioSesion.getRol() == null || !"ADMIN".equalsIgnoreCase(usuarioSesion.getRol().getNombre())) {
            return "redirect:/servicio/listado";
        }
        return null;
    }
}
