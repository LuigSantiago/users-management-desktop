package com.jcaa.usersmanagement.application.usecase;

import com.jcaa.usersmanagement.domain.model.ModeloVehiculo;
import com.jcaa.usersmanagement.domain.ports.PuertoRepositorioModeloVehiculo;

public class ActualizarModeloVehiculoCasoUso {
    private final PuertoRepositorioModeloVehiculo repositorioPuerto;

    public ActualizarModeloVehiculoCasoUso(PuertoRepositorioModeloVehiculo repositorioPuerto) {
        this.repositorioPuerto = repositorioPuerto;
    }

    public ModeloVehiculo ejecutar(ModeloVehiculo vehiculo) {
        return repositorioPuerto.actualizar(vehiculo);
    }
}