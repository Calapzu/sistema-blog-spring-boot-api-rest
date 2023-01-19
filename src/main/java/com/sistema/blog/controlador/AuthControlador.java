package com.sistema.blog.controlador;

import com.sistema.blog.dto.LoginDTO;
import com.sistema.blog.dto.RegistroDTO;
import com.sistema.blog.entidades.Rol;
import com.sistema.blog.entidades.Usuario;
import com.sistema.blog.repositorio.RolRepositorio;
import com.sistema.blog.repositorio.UsuarioRespositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthControlador {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRespositorio usuarioRespositorio;

    @Autowired
    private RolRepositorio rolRepositorio;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/iniciarSesion")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDTO loginDTO){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsernameOrEmail(), loginDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("Ha iniciado sesion con exito !!", HttpStatus.OK );
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarUsusario(@RequestBody RegistroDTO registroDTO){
        if (usuarioRespositorio.existsByUsername(registroDTO.getUsername())){
            return new ResponseEntity<>("Ese nombre de usuario ya existe", HttpStatus.BAD_REQUEST);
        }
        if (usuarioRespositorio.existsByEmail(registroDTO.getEmail())){
            return new ResponseEntity<>("Ese email de usuario ya existe", HttpStatus.BAD_REQUEST);
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(registroDTO.getNombre());
        usuario.setUsername(registroDTO.getUsername());
        usuario.setEmail(registroDTO.getEmail());
        usuario.setPassword(passwordEncoder.encode(registroDTO.getPassword()));

        Rol roles = rolRepositorio.findByNombre("ROLE_ADMIN").get();
        usuario.setRoles(Collections.singleton(roles));

        usuarioRespositorio.save(usuario);
        return new ResponseEntity<>("Usuario registrar exitosamente", HttpStatus.OK);
    }
}
