package com.jcaa.usersmanagement.application.usecase;

import com.jcaa.usersmanagement.domain.ports.PuertoRepositorioModeloVehiculo;

public class EliminarModeloVehiculoCasoUso {
    private final PuertoRepositorioModeloVehiculo repositorioPuerto;

    public EliminarModeloVehiculoCasoUso(PuertoRepositorioModeloVehiculo repositorioPuerto) {
        this.repositorioPuerto = repositorioPuerto;
    }

    public boolean ejecutar(String id) {
        return repositorioPuerto.eliminarPorId(id);
    }
}