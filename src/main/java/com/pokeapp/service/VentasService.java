package com.pokeapp.service;

import com.pokeapp.entity.Ventas;
import com.pokeapp.repository.VentasRepository;
import org.springframework.stereotype.Service;
import com.pokeapp.EstadoVenta;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class VentasService {

    private final VentasRepository ventasRepository;

    public VentasService(VentasRepository ventasRepository) {
        this.ventasRepository = ventasRepository;
    }

    public List<Ventas> obtenerTodas() {
        return ventasRepository.findAll();
    }

    public List<Ventas> obtenerPorVendedor(String emailVendedor) {
        return ventasRepository.findByEmailVendedor(emailVendedor);
    }

    public List<Ventas> obtenerPorComprador(String emailComprador) {
        return ventasRepository.findByEmailComprador(emailComprador);
    }

    public Optional<Ventas> obtenerUna(String emailVendedor, String idCarta, String emailComprador) {
        return ventasRepository.findByEmailVendedorAndIdCartaAndEmailComprador(emailVendedor, idCarta, emailComprador);
    }

    public Ventas guardar(Ventas venta) {
        return ventasRepository.save(venta);
    }

    public void eliminar(String emailVendedor, String idCarta, String emailComprador) {
        ventasRepository.findByEmailVendedorAndIdCartaAndEmailComprador(emailVendedor, idCarta, emailComprador)
                .ifPresent(ventasRepository::delete);
    }

    public List<String> obtenerCartasCompradas(String emailComprador) {
        return ventasRepository.findIdCartaByEmailComprador(emailComprador);
    }

    public List<String> obtenerCartasVendidasPor(String emailVendedor) {
        return ventasRepository.findIdCartaByEmailVendedor(emailVendedor);
    }

    @Transactional
    public void reservarVenta(String idCarta, String emailComprador) {

        Ventas ventaDisponible = ventasRepository.findByIdCartaAndDisponible(idCarta)
                .orElseThrow(() -> new RuntimeException("Carta no disponible"));

        Ventas ventaReservada = new Ventas(
                ventaDisponible.getEmailVendedor(),
                ventaDisponible.getIdCarta(),
                emailComprador,  // Ahora tiene comprador
                EstadoVenta.Comprado,  // Cambiar estado
                ventaDisponible.getIdioma(),
                ventaDisponible.getDireccion(),
                ventaDisponible.getFecha()
        );

        ventasRepository.delete(ventaDisponible);

        ventasRepository.save(ventaReservada);

    }


}
