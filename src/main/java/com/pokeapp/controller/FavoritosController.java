package com.pokeapp.controller;

import com.pokeapp.entity.Favoritos;
import com.pokeapp.service.FavoritosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/favoritos")
public class FavoritosController {

    private final FavoritosService favoritosService;

    public FavoritosController(FavoritosService favoritosService) {
        this.favoritosService = favoritosService;
    }   

    @GetMapping
    public ResponseEntity<List<Favoritos>> obtenerTodos() {
        return ResponseEntity.ok(favoritosService.obtenerTodos());
    }

    @GetMapping("/{emailUsuario}")
    public ResponseEntity<List<Favoritos>> obtenerPorUsuario(@PathVariable String emailUsuario) {
        return ResponseEntity.ok(favoritosService.obtenerPorUsuario(emailUsuario));
    }

    @GetMapping("/{emailUsuario}/{idCarta}")
    public ResponseEntity<Favoritos> obtenerUno(
            @PathVariable String emailUsuario,
            @PathVariable String idCarta) {
        Optional<Favoritos> favorito = favoritosService.obtenerUno(emailUsuario, idCarta);
        return favorito.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Favoritos> crear(@RequestBody Favoritos favorito) {
        return ResponseEntity.ok(favoritosService.guardar(favorito));
    }

    @DeleteMapping("/{emailUsuario}/{idCarta}")
    public ResponseEntity<Void> eliminar(
            @PathVariable String emailUsuario,
            @PathVariable String idCarta) {
        favoritosService.eliminar(emailUsuario, idCarta);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{emailUsuario}/cartas")
    public ResponseEntity<List<String>> obtenerCartasFavoritas(@PathVariable String emailUsuario) {
        List<String> cartas = favoritosService.obtenerCartasPorUsuario(emailUsuario);
        return ResponseEntity.ok(cartas);
    }

}
