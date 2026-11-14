package com.jcaa.usersmanagement.application.usecase;

import com.jcaa.usersmanagement.domain.model.ModeloVehiculo;
import com.jcaa.usersmanagement.domain.ports.PuertoRepositorioModeloVehiculo;

public class CrearModeloVehiculoCasoUso {
    private final PuertoRepositorioModeloVehiculo repositorioPuerto;

    public CrearModeloVehiculoCasoUso(PuertoRepositorioModeloVehiculo repositorioPuerto) {
        this.repositorioPuerto = repositorioPuerto;
    }

    public ModeloVehiculo ejecutar(String id, String marca, String modelo, int cilindrada, double precio) {
        ModeloVehiculo vehiculo = new ModeloVehiculo(id, marca, modelo, cilindrada, precio);
        return repositorioPuerto.guardar(vehiculo);
    }
}