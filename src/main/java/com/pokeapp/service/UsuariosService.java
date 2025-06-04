package com.pokeapp.service;

import com.pokeapp.entity.Usuarios;
import com.pokeapp.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuariosService {

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Usuarios> getAllUsuarios() {
        return usuariosRepository.findAll();
    }

    public Optional<Usuarios> getUsuarioByEmail(String email) {
        return usuariosRepository.findById(email);
    }

    public Usuarios createUsuario(Usuarios usuario) {

        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
        return usuariosRepository.save(usuario);
    }

    public Usuarios updateUsuario(String email, Usuarios updatedUsuario) {
        return usuariosRepository.findById(email)
                .map(usuario -> {
                    usuario.setNombre(updatedUsuario.getNombre());
                    usuario.setApellidos(updatedUsuario.getApellidos());

                    // Encriptamos si la contraseña ha cambiado
                    if (!updatedUsuario.getContrasena().isBlank()) {
                        usuario.setContrasena(passwordEncoder.encode(updatedUsuario.getContrasena()));
                    }

                    return usuariosRepository.save(usuario);
                })
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con email: " + email));
    }

    public void deleteUsuario(String email) {
        usuariosRepository.deleteById(email);
    }

    public boolean login(String email, String rawPassword) {
        Optional<Usuarios> userOpt = usuariosRepository.findById(email);
        if (userOpt.isPresent()) {
            String hashedPassword = userOpt.get().getContrasena();
            return passwordEncoder.matches(rawPassword, hashedPassword);
        }
        return false;
    }

}