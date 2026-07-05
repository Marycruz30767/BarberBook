package com.barber.demo.controller;

import com.barber.demo.domain.Servicio;
import com.barber.demo.service.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ServicioController {

    @Autowired
    private ServicioService servicioService;

    @GetMapping("/servicio/listado")
    public String listado(Model model) {
        var servicios = servicioService.getServicios();
        model.addAttribute("servicios", servicios);
        return "servicio/listado";
    }

    @GetMapping("/servicio/nuevo")
    public String nuevo(Servicio servicio) {
        return "servicio/modifica";
    }

    @PostMapping("/servicio/guardar")
    public String guardar(Servicio servicio) {
        servicio.setActivo(true);
        servicioService.save(servicio);
        return "redirect:/servicio/listado";
    }

    @GetMapping("/servicio/modificar/{idServicio}")
    public String modificar(Servicio servicio, Model model) {
        servicio = servicioService.getServicio(servicio);
        model.addAttribute("servicio", servicio);
        return "servicio/modifica";
    }

    @GetMapping("/servicio/eliminar/{idServicio}")
    public String eliminar(Servicio servicio) {
        servicio = servicioService.getServicio(servicio);
        servicio.setActivo(false);
        servicioService.save(servicio);
        return "redirect:/servicio/listado";
    }
}