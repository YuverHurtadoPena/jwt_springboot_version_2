package com.seguridad_spb_v2.seguridad.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seguridad_spb_v2.seguridad.entity.Rol;
import com.seguridad_spb_v2.seguridad.enums.RolNombre;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
}