package com.pokeapp.repository;

import com.pokeapp.entity.Favoritos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FavoritosRepository extends JpaRepository<Favoritos, String> {
    List<Favoritos> findByEmailUsuario(String emailUsuario);
    Optional<Favoritos> findByEmailUsuarioAndIdCarta(String emailUsuario, String idCarta);
    void deleteByEmailUsuarioAndIdCarta(String emailUsuario, String idCarta);

    @Query("SELECT f.idCarta FROM Favoritos f WHERE f.emailUsuario = :emailUsuario")
    List<String> findIdCartaByEmailUsuario(@Param("emailUsuario") String emailUsuario);
}

