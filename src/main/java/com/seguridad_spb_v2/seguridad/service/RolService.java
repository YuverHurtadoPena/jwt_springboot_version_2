package com.seguridad_spb_v2.seguridad.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seguridad_spb_v2.seguridad.entity.Rol;
import com.seguridad_spb_v2.seguridad.enums.RolNombre;
import com.seguridad_spb_v2.seguridad.repository.RolRepository;

@Service
@Transactional
public class RolService {
    @Autowired
    RolRepository rolRepository;

    public Optional<Rol> getByRolNombre(RolNombre rolNombre){
        return rolRepository.findByRolNombre(rolNombre);
    }

    public void save(Rol rol){
        rolRepository.save(rol);
    }

}
