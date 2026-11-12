package com.jcaa.usersmanagement.infrastructure.adapter;

import com.jcaa.usersmanagement.domain.model.ModeloVehiculo;
import com.jcaa.usersmanagement.domain.ports.PuertoRepositorioModeloVehiculo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/* Adaptador de Persistencia en Memoria */
public class RepositorioModeloVehiculoEnMemoria implements PuertoRepositorioModeloVehiculo {
    private final Map<String, ModeloVehiculo> baseDeDatos = new HashMap<>();

    @Override
    public ModeloVehiculo guardar(ModeloVehiculo vehiculo) {
        baseDeDatos.put(vehiculo.getId(), vehiculo);
        return vehiculo;
    }

    @Override
    public Optional<ModeloVehiculo> buscarPorId(String id) {
        return Optional.ofNullable(baseDeDatos.get(id));
    }

    @Override
    public List<ModeloVehiculo> obtenerTodos() {
        return new ArrayList<>(baseDeDatos.values());
    }

    @Override
    public ModeloVehiculo actualizar(ModeloVehiculo vehiculo) {
        if (baseDeDatos.containsKey(vehiculo.getId())) {
            baseDeDatos.put(vehiculo.getId(), vehiculo);
            return vehiculo;
        }
        return null;
    }

    @Override
    public boolean eliminarPorId(String id) {
        return baseDeDatos.remove(id) != null;
    }
}