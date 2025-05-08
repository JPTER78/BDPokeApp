package com.pokeapp.service;

import com.pokeapp.entity.Favoritos;
import com.pokeapp.repository.FavoritosRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoritosService {

    private final FavoritosRepository favoritosRepository;

    public FavoritosService(FavoritosRepository favoritosRepository) {
        this.favoritosRepository = favoritosRepository;
    }

    public List<Favoritos> obtenerTodos() {
        return favoritosRepository.findAll();
    }

    public List<Favoritos> obtenerPorUsuario(String emailUsuario) {
        return favoritosRepository.findByEmailUsuario(emailUsuario);
    }

    public Optional<Favoritos> obtenerUno(String emailUsuario, String idCarta) {
        return favoritosRepository.findByEmailUsuarioAndIdCarta(emailUsuario, idCarta);
    }

    public Favoritos guardar(Favoritos favorito) {
        return favoritosRepository.save(favorito);
    }

    public void eliminar(String emailUsuario, String idCarta) {
        favoritosRepository.deleteByEmailUsuarioAndIdCarta(emailUsuario, idCarta);
    }

    public List<String> obtenerCartasPorUsuario(String emailUsuario) {
        return favoritosRepository.findIdCartaByEmailUsuario(emailUsuario);
    }

}
