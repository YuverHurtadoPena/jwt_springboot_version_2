package com.seguridad_spb_v2.seguridad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.seguridad_spb_v2.seguridad.entity.Usuario;
import com.seguridad_spb_v2.seguridad.entity.UsuarioPrincipal;
import com.seguridad_spb_v2.seguridad.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Override
    public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
        Usuario usuario =  usuarioRepository.findByNombreUsuarioOrEmil(nombreUsuario,nombreUsuario).get();
        return UsuarioPrincipal.build(usuario);
    }
}