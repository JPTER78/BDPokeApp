package com.pokeapp.entity;

import com.pokeapp.EstadoVenta;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@IdClass(VentasId.class)
public class Ventas {

    @Id
    @NotBlank
    @Column(nullable = false, length = 50)
    private String emailVendedor;

    @Id
    @NotBlank
    @Column(nullable = false, length = 100)
    private String idCarta;

    @Id
    @NotBlank
    @Column(nullable = true, length = 50)
    private String emailComprador;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoVenta estado;

    @NotBlank
    @Column(nullable = false)
    private String idioma;

    @NotBlank
    @Column(nullable = false)
    private String direccion;

    @Column(nullable = true, columnDefinition = "TIMESTAMP(6)")
    private LocalDateTime fecha;

    // Relaciones
    @ManyToOne
    @JoinColumn(name = "emailVendedor", referencedColumnName = "email", insertable = false, updatable = false)
    private Usuarios vendedor;

    @ManyToOne
    @JoinColumn(name = "emailComprador", referencedColumnName = "email", insertable = false, updatable = false)
    private Usuarios comprador;

    // Constructor vacío
    public Ventas() {}

    // Constructor completo (excepto relaciones)
    public Ventas(String emailVendedor, String idCarta, String emailComprador, EstadoVenta estado, String idioma, String direccion, LocalDateTime fecha) {
        this.emailVendedor = emailVendedor;
        this.idCarta = idCarta;
        this.emailComprador = emailComprador;
        this.estado = estado;
        this.idioma = idioma;
        this.direccion = direccion;
        this.fecha = fecha;
    }

    // Getters y Setters
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

    public EstadoVenta getEstado() {
        return estado;
    }

    public void setEstado(EstadoVenta estado) {
        this.estado = estado;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Usuarios getVendedor() {
        return vendedor;
    }

    public void setVendedor(Usuarios vendedor) {
        this.vendedor = vendedor;
    }

    public Usuarios getComprador() {
        return comprador;
    }

    public void setComprador(Usuarios comprador) {
        this.comprador = comprador;
    }
}
