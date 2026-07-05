package com.barber.demo.controller;

import com.barber.demo.dao.RolDao;
import com.barber.demo.domain.Rol;
import com.barber.demo.domain.Usuario;
import com.barber.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RolDao rolDao;

    @GetMapping("/registro")
    public String registro(Usuario usuario) {
        return "registro";
    }

    @PostMapping("/registro/guardar")
    public String guardarRegistro(Usuario usuario) {
        Rol rolCliente = rolDao.findById(2).orElse(null);
        usuario.setRol(rolCliente);
        usuario.setActivo(true);
        usuarioService.save(usuario);
        return "redirect:/login";
    }

    @GetMapping("/usuario/perfil")
    public String perfil(Model model, jakarta.servlet.http.HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        model.addAttribute("usuario", usuario);
        return "cliente/perfil";
    }

    @PostMapping("/usuario/guardar")
    public String guardarPerfil(Usuario usuario, jakarta.servlet.http.HttpSession session) {
        Usuario usuarioSesion = (Usuario) session.getAttribute("usuario");

        usuario.setIdUsuario(usuarioSesion.getIdUsuario());
        usuario.setRol(usuarioSesion.getRol());
        usuario.setActivo(true);

        usuarioService.save(usuario);
        session.setAttribute("usuario", usuario);

        return "redirect:/usuario/perfil";
    }
}