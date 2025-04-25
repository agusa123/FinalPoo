package org.example;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        /*
        ClienteDAO clienteDAO = new ClienteDAO(); // Crear instancia de ClienteDAO

        // Crear un cliente de prueba
        Cliente cliente1 = new Cliente(1, "Juan", "Pérez", 42357742, "123456789", "juan@gmail.com", "uncorreo@gmail");

        // Registrar cliente
        System.out.println("Intentando registrar cliente:");
        clienteDAO.registrarCliente(cliente1);

        // Listar clientes
        System.out.println("\nLista de clientes:");
        List<Cliente> clientes = clienteDAO.listarClientes();
        for (Cliente cliente : clientes) {
            System.out.println(cliente.getApellido());
        }

        // Verificar existencia por DNI
        System.out.println("\nVerificar existencia por DNI:");
        boolean existe = clienteDAO.existenciaClientePorDni(12345678);
        System.out.println("¿Existe cliente con DNI 12345678? " + existe);

        // Editar información del cliente
        System.out.println("\nEditar información del cliente (nombre y apellido):");
        boolean editado = clienteDAO.editarCliente(1, "Carlos", "Gómez", "Antigua Dirección", "9876543666", "carlos@gmail.com");
        System.out.println("Edición exitosa: " + editado);

        // Obtener cliente por ID
        System.out.println("\nBuscar cliente por ID:");
        Cliente clientePorId = clienteDAO.obtenerClientePorId(2);
        if (clientePorId != null) {
            System.out.println("Cliente encontrado: " + clientePorId.getApellido());
        } else {
            System.out.println("No se encontró cliente con ID 1");
        }

        // Editar un campo específico del cliente
        System.out.println("\nEditar un campo específico del cliente:");
        boolean campoEditado = clienteDAO.editarCampoEspecifico(1, "telefono", "111222333");
        System.out.println("Campo editado exitosamente: " + campoEditado);

        // Listar clientes nuevamente tras la edición
        System.out.println("\nLista de clientes tras la edición:");
        clientes = clienteDAO.listarClientes();
        for (Cliente cliente : clientes) {
            System.out.println(cliente.getApellido() + " "+ cliente.getNombre());
        }
        */
        //************************************ Prueba para la clase prestamo y prestamosDAO***********************************************
        /*
        // Crear los préstamos
        Prestamo prestamo1 = new Prestamo( 1, 12000000, 12, "Personal", 12000000);
        Prestamo prestamo2 = new Prestamo( 2, 15000000, 24, "Personal", 15000000);

        // Insertar los préstamos en la base de datos
        PrestamoDAO prestamoDAO = new PrestamoDAO();

        if (prestamoDAO.crearPrestamo(prestamo1)) {
            System.out.println("Préstamo 1 creado exitosamente.");
        } else {
            System.out.println("Error al crear el préstamo 1.");
        }

        if (prestamoDAO.crearPrestamo(prestamo2)) {
            System.out.println("Préstamo 2 creado exitosamente.");
        } else {
            System.out.println("Error al crear el préstamo 2.");

        }


        // Listar todos los préstamos
        System.out.println("\n📋 Listar todos los préstamos:");
        List<Prestamo> prestamos = prestamoDAO.listarTodosLosPrestamos();
        if (prestamos.isEmpty()) {
            System.out.println("No hay préstamos registrados.");
        } else {
            for (Prestamo prestamo : prestamos) {
                System.out.println(prestamo);
            }
        }

        // Obtener préstamo por ID
        System.out.println("\n🔍 Buscar un préstamo por ID (ID = 1):");
        Prestamo prestamoPorId = prestamoDAO.obtenerPrestamoPorId(1);
        if (prestamoPorId != null) {
            System.out.println("Préstamo encontrado: " + prestamoPorId);
        } else {
            System.out.println("No se encontró ningún préstamo con el ID especificado.");
        }

        // Obtener todos los préstamos de un cliente específico
        System.out.println("\n📌 Listar préstamos de cliente con ID = 1:");
        List<Prestamo> prestamosDeCliente = prestamoDAO.obtenerPrestamosPorCliente(1);
        if (prestamosDeCliente.isEmpty()) {
            System.out.println("El cliente especificado no tiene préstamos registrados.");
        } else {
            for (Prestamo prestamo : prestamosDeCliente) {
                System.out.println(prestamo);
            }
        }

        // Actualizar el saldo pendiente de un préstamo
        System.out.println("\n🔄 Actualizar saldo pendiente del préstamo con ID = 1:");
        boolean saldoActualizado = prestamoDAO.actualizarSaldo(1, 9000000); // Ajustar a lo que quieras
        if (saldoActualizado) {
            System.out.println("Saldo actualizado correctamente.");
        } else {
            System.out.println("Hubo un error al actualizar el saldo.");
        }

        // Verificar actualización del saldo mostrando el préstamo actualizado
        System.out.println("\n✅ Verificando el préstamo con ID = 1 tras la actualización:");
        prestamoPorId = prestamoDAO.obtenerPrestamoPorId(1);
        if (prestamoPorId != null) {
            System.out.println("Préstamo actualizado: " + prestamoPorId);
        } else {
            System.out.println("No se encontró ningún préstamo con el ID especificado tras la actualización.");
        }
        */

    }
}