package com.pokeapp.entity;

import java.io.Serializable;
import java.util.Objects;

public class FavoritosId implements Serializable {

    private String emailUsuario;
    private String idCarta;

    public FavoritosId() {}

    public FavoritosId(String emailUsuario, String idCarta) {
        this.emailUsuario = emailUsuario;
        this.idCarta = idCarta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FavoritosId)) return false;
        FavoritosId that = (FavoritosId) o;
        return Objects.equals(emailUsuario, that.emailUsuario) &&
                Objects.equals(idCarta, that.idCarta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailUsuario, idCarta);
    }
}
