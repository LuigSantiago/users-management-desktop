package com.jcaa.usersmanagement.application.usecase;

import com.jcaa.usersmanagement.domain.model.ModeloVehiculo;
import com.jcaa.usersmanagement.domain.ports.PuertoRepositorioModeloVehiculo;
import java.util.List;

public class ListarModelosVehiculoCasoUso {
    private final PuertoRepositorioModeloVehiculo repositorioPuerto;

    public ListarModelosVehiculoCasoUso(PuertoRepositorioModeloVehiculo repositorioPuerto) {
        this.repositorioPuerto = repositorioPuerto;
    }

    public List<ModeloVehiculo> ejecutar() {
        return repositorioPuerto.obtenerTodos();
    }
}