package com.pokeapp.controller;

import com.pokeapp.EstadoVenta;
import com.pokeapp.entity.Ventas;
import com.pokeapp.repository.VentasRepository;
import com.pokeapp.service.VentasService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/ventas")
public class VentasController {

    private final VentasService ventasService;
    private final VentasRepository ventasRepository;

    public VentasController(VentasService ventasService, VentasRepository ventasRepository) {
        this.ventasService = ventasService;
        this.ventasRepository = ventasRepository;
    }

    @GetMapping
    public ResponseEntity<List<Ventas>> obtenerTodas() {
        return ResponseEntity.ok(ventasService.obtenerTodas());
    }

    @GetMapping("/vendedor/{email}")
    public ResponseEntity<List<Ventas>> obtenerPorVendedor(@PathVariable String email) {
        return ResponseEntity.ok(ventasService.obtenerPorVendedor(email));
    }

    @GetMapping("/comprador/{email}")
    public ResponseEntity<List<Ventas>> obtenerPorComprador(@PathVariable String email) {
        return ResponseEntity.ok(ventasService.obtenerPorComprador(email));
    }

    @GetMapping("/{emailVendedor}/{idCarta}/{emailComprador}")
    public ResponseEntity<Ventas> obtenerUna(
            @PathVariable String emailVendedor,
            @PathVariable String idCarta,
            @PathVariable String emailComprador) {
        return ventasService.obtenerUna(emailVendedor, idCarta, emailComprador)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Ventas> crear(@RequestBody Ventas venta) {
        return ResponseEntity.ok(ventasService.guardar(venta));
    }

    @DeleteMapping("/{emailVendedor}/{idCarta}/{emailComprador}")
    public ResponseEntity<Void> eliminar(
            @PathVariable String emailVendedor,
            @PathVariable String idCarta,
            @PathVariable String emailComprador) {
        ventasService.eliminar(emailVendedor, idCarta, emailComprador);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/comprador/{email}/cartas")
    public ResponseEntity<List<String>> obtenerCartasCompradas(@PathVariable String email) {
        return ResponseEntity.ok(ventasService.obtenerCartasCompradas(email));
    }

    @GetMapping("/vendedor/{email}/cartas")
    public ResponseEntity<List<String>> obtenerCartasVendidas(@PathVariable String email) {
        return ResponseEntity.ok(ventasService.obtenerCartasVendidasPor(email));
    }

    @PutMapping("/{idCarta}/reservar")
    public ResponseEntity<?> reservarVenta(
            @PathVariable String idCarta,
            @RequestParam String emailComprador) {
        try {
            ventasService.reservarVenta(idCarta, emailComprador);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Error: " + e.getMessage());
        }
    }



}

