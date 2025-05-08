package com.pokeapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Usuarios {

    @Id
    @Email
    @Column(nullable = false, length = 50)
    private String email;

    @NotBlank
    @Column(nullable = false, length = 30)
    private String nombre;

    @NotBlank
    @Column(nullable = false, length = 50)
    private String apellidos;

    @NotBlank
    @Size(max = 300)
    @Column(nullable = false, length = 300)
    private String contrasena;

    // Constructor vacío requerido por JPA
    public Usuarios() {}

    public Usuarios(String email, String nombre, String apellidos, String contrasena) {
        this.email = email;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.contrasena = contrasena;
    }

    // Getters y Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
