package com.pokeapp.entity;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Embeddable;

@Embeddable
public class VentasId implements Serializable {

    private String emailVendedor;
    private String idCarta;
    private String emailComprador;

    public VentasId() {}

    public VentasId(String emailVendedor, String idCarta, String emailComprador) {
        this.emailVendedor = emailVendedor;
        this.idCarta = idCarta;
        this.emailComprador = emailComprador;
    }

    public String getEmailVendedor() {
        return emailVendedor;
    }

    public void setEmailVendedor(String emailVendedor) {
        this.emailVendedor = emailVendedor;
    }

    public String getIdCarta() {
        return idCarta;
    }

    public void setIdCarta(String idCarta) {
        this.idCarta = idCarta;
    }

    public String getEmailComprador() {
        return emailComprador;
    }

    public void setEmailComprador(String emailComprador) {
        this.emailComprador = emailComprador;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VentasId that)) return false;
        return Objects.equals(emailVendedor, that.emailVendedor) &&
                Objects.equals(idCarta, that.idCarta) &&
                Objects.equals(emailComprador, that.emailComprador);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailVendedor, idCarta, emailComprador);
    }
}
