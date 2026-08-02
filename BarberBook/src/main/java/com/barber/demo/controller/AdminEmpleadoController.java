package com.barber.demo.controller;

import com.barber.demo.domain.Empleado;
import com.barber.demo.domain.Usuario;
import com.barber.demo.service.EmpleadoService;
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
public class AdminEmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping("/admin/empleados")
    public String listado(Model model, HttpSession session) {
        String redireccion = validarAdmin(session);
        if (redireccion != null) {
            return redireccion;
        }

        model.addAttribute("empleados", empleadoService.getEmpleados());
        return "admin/empleado/listado";
    }

    @GetMapping("/admin/empleados/nuevo")
    public String nuevo(Model model, HttpSession session) {
        String redireccion = validarAdmin(session);
        if (redireccion != null) {
            return redireccion;
        }

        model.addAttribute("usuario", new Usuario());
        return "admin/empleado/nuevo";
    }

    @PostMapping("/admin/empleados/guardar")
    public String guardar(Usuario usuario,
            @RequestParam String especialidad,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        String redireccion = validarAdmin(session);
        if (redireccion != null) {
            return redireccion;
        }

        try {
            empleadoService.registrarEmpleado(usuario, especialidad);
            redirectAttributes.addFlashAttribute("mensaje", "Empleado registrado correctamente.");
            return "redirect:/admin/empleados";
        } catch (IllegalArgumentException | IllegalStateException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/admin/empleados/nuevo";
        }
    }

    @GetMapping("/admin/empleados/eliminar/{idEmpleado}")
    public String desactivar(@PathVariable Integer idEmpleado, HttpSession session) {
        String redireccion = validarAdmin(session);
        if (redireccion != null) {
            return redireccion;
        }

        Empleado filtro = new Empleado();
        filtro.setIdEmpleado(idEmpleado);
        Empleado empleado = empleadoService.getEmpleado(filtro);

        if (empleado != null) {
            empleado.setActivo(false);
            empleadoService.save(empleado);
        }

        return "redirect:/admin/empleados";
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
