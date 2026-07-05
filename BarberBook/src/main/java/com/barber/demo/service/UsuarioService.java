package com.barber.demo.service;

import com.barber.demo.domain.Usuario;
import java.util.List;

public interface UsuarioService {

    List<Usuario> getUsuarios();

    Usuario getUsuario(Usuario usuario);

    void save(Usuario usuario);

    void delete(Usuario usuario);
    
    Usuario getUsuarioPorCorreo(String correo);
}