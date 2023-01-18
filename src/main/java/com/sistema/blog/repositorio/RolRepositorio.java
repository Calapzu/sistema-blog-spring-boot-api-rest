package com.sistema.blog.repositorio;

import com.sistema.blog.entidades.Rol;
import com.sistema.blog.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepositorio extends JpaRepository<Usuario, Long> {

    public Optional<Rol> findByNombre(String nombre);
}
