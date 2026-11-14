package com.jcaa.usersmanagement.application.usecase;

import com.jcaa.usersmanagement.domain.model.ModeloVehiculo;
import com.jcaa.usersmanagement.domain.ports.PuertoRepositorioModeloVehiculo;
import java.util.Optional;

public class ObtenerModeloVehiculoPorIdCasoUso {
    private final PuertoRepositorioModeloVehiculo repositorioPuerto;

    public ObtenerModeloVehiculoPorIdCasoUso(PuertoRepositorioModeloVehiculo repositorioPuerto) {
        this.repositorioPuerto = repositorioPuerto;
    }

    public Optional<ModeloVehiculo> ejecutar(String id) {
        return repositorioPuerto.buscarPorId(id);
    }
}