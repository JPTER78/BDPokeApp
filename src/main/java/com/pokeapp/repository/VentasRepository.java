package com.pokeapp.repository;

import com.pokeapp.entity.Ventas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface VentasRepository extends JpaRepository<Ventas, String> {
    Optional<Ventas> findByEmailVendedorAndIdCartaAndEmailComprador(String emailVendedor, String idCarta, String emailComprador);
    List<Ventas> findByEmailVendedor(String emailVendedor);
    List<Ventas> findByEmailComprador(String emailComprador);

    @Query("SELECT v.idCarta FROM Ventas v WHERE v.emailComprador = :emailComprador")
    List<String> findIdCartaByEmailComprador(String emailComprador);

    @Query("SELECT v.idCarta FROM Ventas v WHERE v.emailVendedor = :emailVendedor")
    List<String> findIdCartaByEmailVendedor(@Param("emailVendedor") String emailVendedor);

    @Query("SELECT v FROM Ventas v WHERE v.idCarta = :idCarta AND v.estado = 'Disponible'")
    Optional<Ventas> findByIdCartaAndDisponible(@Param("idCarta") String idCarta);

    @Modifying
    @Query("UPDATE Ventas v SET v.estado = 'Comprado', v.emailComprador = :emailComprador WHERE v.idCarta = :idCarta")
    int reservarVenta(@Param("idCarta") String idCarta, @Param("emailComprador") String emailComprador);

}
