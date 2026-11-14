package com.jcaa.usersmanagement;

import com.jcaa.usersmanagement.application.usecase.CrearModeloVehiculoCasoUso;
import com.jcaa.usersmanagement.application.usecase.ActualizarModeloVehiculoCasoUso;
import com.jcaa.usersmanagement.application.usecase.EliminarModeloVehiculoCasoUso;
import com.jcaa.usersmanagement.application.usecase.ListarModelosVehiculoCasoUso;
import com.jcaa.usersmanagement.application.usecase.ObtenerModeloVehiculoPorIdCasoUso;
import com.jcaa.usersmanagement.domain.model.ModeloVehiculo;
import com.jcaa.usersmanagement.domain.ports.PuertoRepositorioModeloVehiculo;
import com.jcaa.usersmanagement.infrastructure.adapter.RepositorioModeloVehiculoEnMemoria;
import com.jcaa.usersmanagement.infrastructure.config.DependencyContainer;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.UserManagementCli;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Main {

  private static final Logger log = LoggerFactory.getLogger(Main.class);

  public static void main(final String[] args) {
    log.info("Starting CEA Management System...");
    //final DependencyContainer container = new DependencyContainer();

    final PuertoRepositorioModeloVehiculo repoVehiculos = new RepositorioModeloVehiculoEnMemoria();
    final CrearModeloVehiculoCasoUso crearVehiculo = new CrearModeloVehiculoCasoUso(repoVehiculos);
    final ListarModelosVehiculoCasoUso listarVehiculos = new ListarModelosVehiculoCasoUso(repoVehiculos);
    final ObtenerModeloVehiculoPorIdCasoUso buscarVehiculo = new ObtenerModeloVehiculoPorIdCasoUso(repoVehiculos);
    final ActualizarModeloVehiculoCasoUso actualizarVehiculo = new ActualizarModeloVehiculoCasoUso(repoVehiculos);
    final EliminarModeloVehiculoCasoUso eliminarVehiculo = new EliminarModeloVehiculoCasoUso(repoVehiculos);

    try (final Scanner scanner = new Scanner(System.in)) {
      boolean ejecutando = true;
      while (ejecutando) {
        System.out.println("\n==========================================");
        System.out.println("      SISTEMA DE GESTIÓN CEA (MENÚ)       ");
        System.out.println("==========================================");
        System.out.println("1. Gestión de Usuarios (Sistema Base)");
        System.out.println("2. Gestión de Modelos de Vehículos (CRUDL)");
        System.out.println("0. Salir del programa");
        System.out.print("Seleccione una opción: ");

        String opcion = scanner.nextLine().trim();

        switch (opcion) {
          case "1":
            final DependencyContainer container = new DependencyContainer();
            new UserManagementCli(container.userController(), new ConsoleIO(scanner, System.out)).start();
            break;
          case "2":
            ejecutarMenuVehiculos(scanner, crearVehiculo, listarVehiculos, buscarVehiculo, actualizarVehiculo, eliminarVehiculo);
            break;
          case "0":
            ejecutando = false;
            System.out.println("Saliendo del sistema...");
            break;
          default:
            System.out.println("Opción no válida. Intente nuevamente.");
        }
      }
    }
  }

  private static void ejecutarMenuVehiculos(
      Scanner scanner,
      CrearModeloVehiculoCasoUso crear,
      ListarModelosVehiculoCasoUso listar,
      ObtenerModeloVehiculoPorIdCasoUso buscar,
      ActualizarModeloVehiculoCasoUso actualizar,
      EliminarModeloVehiculoCasoUso eliminar) {

    boolean enMenuVehiculos = true;
    while (enMenuVehiculos) {
      System.out.println("\n--- GESTIÓN DE MODELOS DE VEHÍCULOS ---");
      System.out.println("1. Crear Modelo de Vehículo");
      System.out.println("2. Listar Todos los Modelos");
      System.out.println("3. Buscar Modelo por ID");
      System.out.println("4. Actualizar Modelo de Vehículo");
      System.out.println("5. Eliminar Modelo por ID");
      System.out.println("0. Volver al Menú Principal");
      System.out.print("Seleccione una opción: ");

      String op = scanner.nextLine().trim();

      try {
        switch (op) {
          case "1":
            System.out.print("ID del vehículo (ej. V01): ");
            String id = scanner.nextLine().trim();
            System.out.print("Marca: ");
            String marca = scanner.nextLine().trim();
            System.out.print("Modelo: ");
            String modelo = scanner.nextLine().trim();
            System.out.print("Cilindrada (cc): ");
            int cilindrada = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Precio: ");
            double precio = Double.parseDouble(scanner.nextLine().trim());

            ModeloVehiculo creado = crear.ejecutar(id, marca, modelo, cilindrada, precio);
            System.out.println("-> Vehículo registrado: " + creado);
            break;

          case "2":
            List<ModeloVehiculo> lista = listar.ejecutar();
            if (lista.isEmpty()) {
              System.out.println("-> No hay vehículos registrados en memoria.");
            } else {
              System.out.println("-> Listado de vehículos:");
              lista.forEach(v -> System.out.println("   * " + v));
            }
            break;

          case "3":
            System.out.print("Ingrese el ID a buscar: ");
            String idBuscar = scanner.nextLine().trim();
            Optional<ModeloVehiculo> encontrado = buscar.ejecutar(idBuscar);
            if (encontrado.isPresent()) {
              System.out.println("-> Encontrado: " + encontrado.get());
            } else {
              System.out.println("-> No se encontró ningún vehículo con ID: " + idBuscar);
            }
            break;

          case "4":
            System.out.print("ID del vehículo a actualizar: ");
            String idAct = scanner.nextLine().trim();
            if (buscar.ejecutar(idAct).isEmpty()) {
              System.out.println("-> No existe un vehículo con ese ID.");
              break;
            }
            System.out.print("Nueva Marca: ");
            String nMarca = scanner.nextLine().trim();
            System.out.print("Nuevo Modelo: ");
            String nModelo = scanner.nextLine().trim();
            System.out.print("Nueva Cilindrada (cc): ");
            int nCilindrada = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Nuevo Precio: ");
            double nPrecio = Double.parseDouble(scanner.nextLine().trim());

            ModeloVehiculo actualizado = actualizar.ejecutar(new ModeloVehiculo(idAct, nMarca, nModelo, nCilindrada, nPrecio));
            System.out.println("-> Vehículo actualizado: " + actualizado);
            break;

          case "5":
            System.out.print("ID del vehículo a eliminar: ");
            String idElim = scanner.nextLine().trim();
            boolean eliminado = eliminar.ejecutar(idElim);
            if (eliminado) {
              System.out.println("-> Vehículo eliminado correctamente.");
            } else {
              System.out.println("-> No se encontró el ID especificado.");
            }
            break;

          case "0":
            enMenuVehiculos = false;
            break;

          default:
            System.out.println("Opción no válida.");
        }
      } catch (NumberFormatException e) {
        System.out.println("-> Error: Cilindrada y Precio deben ser valores numéricos.");
      }
    }
  }
}