package com.pokeapp.controller;

import com.pokeapp.entity.Ventas;
import com.pokeapp.service.VentasService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ventas")
public class VentasController {

    private final VentasService ventasService;

    public VentasController(VentasService ventasService) {
        this.ventasService = ventasService;
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

}

