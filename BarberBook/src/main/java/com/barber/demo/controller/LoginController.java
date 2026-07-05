package com.barber.demo.controller;

import com.barber.demo.domain.Usuario;
import com.barber.demo.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login/validar")
    public String validar(Usuario usuario, HttpSession session, Model model) {
        Usuario usuarioEncontrado = usuarioService.getUsuarioPorCorreo(usuario.getCorreo());

        if (usuarioEncontrado != null && usuarioEncontrado.getContrasena().equals(usuario.getContrasena())) {
            session.setAttribute("usuario", usuarioEncontrado);
            return "redirect:/servicio/listado";
        }

        model.addAttribute("error", "Correo o contraseña incorrectos");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}