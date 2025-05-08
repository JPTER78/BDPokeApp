package com.pokeapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@IdClass(FavoritosId.class)
public class Favoritos {

    @Id
    @NotBlank
    @Column(nullable = false, length = 50)
    private String emailUsuario;

    @Id
    @NotBlank
    @Column(nullable = false, length = 100)
    private String idCarta;

    @ManyToOne
    @JoinColumn(name = "emailUsuario", referencedColumnName = "email", insertable = false, updatable = false)
    private Usuarios usuarios;

    public Favoritos() {}

    public Favoritos(String emailUsuario, String idCarta) {
        this.emailUsuario = emailUsuario;
        this.idCarta = idCarta;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getIdCarta() {
        return idCarta;
    }

    public void setIdCarta(String idCarta) {
        this.idCarta = idCarta;
    }

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuario(Usuarios usuarios) {
        this.usuarios = usuarios;
    }
}
