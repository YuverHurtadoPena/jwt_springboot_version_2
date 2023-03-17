package com.seguridad_spb_v2.seguridad.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.seguridad_spb_v2.seguridad.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	@Transactional
	@Query(value = "select * FROM  seguridad_spb_v2.usuario where nombre_usuario=?1 or email=?2", nativeQuery = true)
    Optional<Usuario> findByNombreUsuarioOrEmil(String nombreUsuario, String email);
    boolean existsByNombreUsuario(String nombreUsuario);
    boolean existsByEmail(String email);

}
