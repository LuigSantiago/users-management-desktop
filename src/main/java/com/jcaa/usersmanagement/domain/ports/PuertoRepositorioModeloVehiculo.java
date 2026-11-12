package com.jcaa.usersmanagement.domain.ports;

import com.jcaa.usersmanagement.domain.model.ModeloVehiculo;
import java.util.List;
import java.util.Optional;

/* Puerto de Salida: Interfaz de Persistencia */
public interface PuertoRepositorioModeloVehiculo {
    ModeloVehiculo guardar(ModeloVehiculo vehiculo);
    Optional<ModeloVehiculo> buscarPorId(String id);
    List<ModeloVehiculo> obtenerTodos();
    ModeloVehiculo actualizar(ModeloVehiculo vehiculo);
    boolean eliminarPorId(String id);
}