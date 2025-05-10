package org.example;

import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static DecimalFormat formatoMoneda = new DecimalFormat("#,##0.00");
    public static void main(String[] args) {
        /*
        ClienteDAO clienteDAO = new ClienteDAO();


        // Crear un cliente de prueba
        Cliente cliente1 = new Cliente( "Agustin", "Cabrera", 42357742, "Alte Brown", "261509778", "cabrera@gmail.com");

        // Registrar cliente
        System.out.println("📌 Registrando nuevo cliente:");
        clienteDAO.registrarCliente(cliente1);

        // Listar todos los clientes
        System.out.println("\n📋 Lista de clientes:");
        List<Cliente> clientes = clienteDAO.listarClientes();
        for (Cliente cliente : clientes) {
            System.out.println("- " + cliente.getIdCliente() + ": " + cliente.getApellido() + ", " + cliente.getNombre());
        }

        // Verificar existencia por DNI
        System.out.println("\n🔍 Verificación por DNI:");
        boolean existe = clienteDAO.existenciaClientePorDni(12345678);
        System.out.println("¿Existe cliente con DNI 12345678? " + existe);

        // Editar información del cliente
       System.out.println("\n✏️ Editando información del cliente con ID 1:");
        boolean editado = clienteDAO.editarCliente(1, "Carlos", "Gómez", "Nueva Dirección", "9876543666", "carlos@gmail.com");
        System.out.println("Edición exitosa: " + editado);

        // Obtener cliente por ID
        System.out.println("\n🔎 Buscar cliente por ID:");
        Cliente clientePorId = clienteDAO.obtenerClientePorId(1);
        if (clientePorId != null) {
            System.out.println("Cliente encontrado: " + clientePorId.getApellido() + ", " + clientePorId.getNombre());
        } else {
            System.out.println("No se encontró cliente con ID 2");
        }

        // Editar campo específico
        System.out.println("\n🔧 Editar campo específico del cliente (teléfono):");
        boolean campoEditado = clienteDAO.editarCampoEspecifico(1, "telefono", "2615097778");
        System.out.println("Campo editado exitosamente: " + campoEditado);

        // Listar clientes nuevamente
        System.out.println("\n📋 Lista actualizada de clientes:");
        clientes = clienteDAO.listarClientes();
        for (Cliente cliente : clientes) {
            System.out.println("- " + cliente.getIdCliente() + ": " + cliente.getApellido() + ", " + cliente.getNombre());
        }
        */
        //************************************ Prueba para la clase prestamo y prestamosDAO ***********************************************
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
        boolean saldoActualizado = prestamoDAO.actualizarSaldoPendiente(1, 9000000);
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
        //********************************************* Cuotas y pogos ************************************************/
        /*
        CuotaDAO cuotaDAO = new CuotaDAO();

        cuotaDAO.generarCuotas(1);
        cuotaDAO.generarCuotas(2);
        cuotaDAO.generarCuotas(3);
        cuotaDAO.generarCuotas(4);

        cuotaDAO.listarCuotasPorPrestamo(1);

        Cuota cuota = cuotaDAO.obtenerCuotaPorId(1);
        LocalDate fechaActual = LocalDate.now();
        Date fechaPagoSQL = Date.valueOf(fechaActual);

        PagoDAO pagoDAO = new PagoDAO();
        Pago pago = new Pago(1, fechaPagoSQL, cuota.getTotalCuota(),"Mercado Pago");
        pagoDAO.insertar(pago);

        cuotaDAO.actualizarEstado(1, "Pagada");

        */
        //******************************************* Menu ************************************************

        Scanner scanner = new Scanner(System.in);
        DecimalFormat formatoMoneda = new DecimalFormat("#,##0.00");
        ClienteDAO clienteDAO = new ClienteDAO();
        Cliente cliente;
        int dni;
        System.out.println("==== Bienvenido al sistema de Gestion financiera. ====");
        
        
        boolean runningg = true;

        while (runningg) {
            // Mostrar el menú principal
            System.out.println("==== Menú Principal ====");
            System.out.println("1. Clientes");
            System.out.println("2. Administradores");
            System.out.println("3. Salir del sistema");
            System.out.print("Selecciona una opción: ");

            int opcion = scanner.nextInt();

            // Manejar las opciones seleccionadas
            switch (opcion) {
                case 1:
                    boolean clienteValido = false;

                    while (!clienteValido) {
                        System.out.println("¿Eres un cliente registrado? (si/no o escribe 'volver' para regresar al menú anterior): ");
                        String respuesta = scanner.next().trim().toLowerCase();
                        scanner.nextLine(); // Limpiar el búfer

                        if (respuesta.equals("volver")) {

                            System.out.println("Regresando al menú principal...");
                            break;
                        }

                        if (respuesta.equals("si")) {

                            while (true) {
                                System.out.println("Por favor, ingresa tu DNI (7 u 8 dígitos) o escribe 'volver' para regresar:");
                                String input = scanner.nextLine().trim();

                                if (input.equalsIgnoreCase("volver")) {
                                    System.out.println("Regresando al menú principal...");
                                    clienteValido = true;
                                    break;
                                }

                                try {
                                    dni = Integer.parseInt(input); // Convertir a int

                                    if (esDniValido(dni)) {

                                        if (clienteDAO.existenciaClientePorDni(dni)) {
                                            cliente = clienteDAO.obtenerClientePorDni(dni);
                                            System.out.println("Bienvenido Sr./Sra. " + cliente.getApellido() + " " + cliente.getNombre());
                                            menuClientes(cliente);
                                            clienteValido = true;
                                            break;
                                        } else {
                                            System.out.println("El DNI ingresado no corresponde a ningún cliente registrado. Inténtalo nuevamente o escribe 'volver' para regresar:");
                                        }
                                    } else {
                                        System.out.println("DNI no válido. Asegúrate que tiene 7 u 8 dígitos.");
                                    }
                                } catch (NumberFormatException e) {
                                    System.out.println("El DNI debe contener solo números. Inténtalo nuevamente o escribe 'volver' para regresar:");
                                }
                            }
                        } else if (respuesta.equals("no")) {
                            clienteValido = true;
                            System.out.println("Por favor, procede a registrar tus datos:");
                            boolean registroExitoso = registrarNuevoCliente(scanner, clienteDAO);
                            if (registroExitoso) {
                                System.out.println("Cliente registrado con éxito. Ahora debe ingresar al sistema como Cliente utilizando su DNI.");
                            } else {
                                System.out.println("No se pudo registrar al cliente. Por favor, inténtalo nuevamente.");
                            }

                        } else {
                            System.out.println("Respuesta no válida. Por favor ingresa 'sí', 'no' o 'volver'.");
                        }
                    }
                    break;


                case 2:
                    
                    break;

                case 3:
                    System.out.println("Saliendo del programa. ¡Gracias!");
                    runningg = false;
                    break;

                default:
                    System.out.println("Opción no válida, por favor intenta de nuevo.");
            }
        }



        scanner.close();


    }


    public static void menuClientes(Cliente cliente){
        Scanner scanner = new Scanner(System.in);
        boolean running = true;


        while (running) {
            // Mostrar el menú principal
            System.out.println("==== Menú Cliente ====");
            System.out.println("1. Prestamos");
            System.out.println("2. Datos personales");
            System.out.println("3. Editar datos personales");
            System.out.println("4. Volver al menu principal");
            System.out.print("Selecciona una opción: ");

            int opcion = scanner.nextInt();

            // Manejar las opciones seleccionadas
            switch (opcion) {
                case 1:
                    menuPrestamos(cliente);
                    break;

                case 2:

                    break;

                case 3:

                    break;
                case 4:
                    System.out.println("Volviendo al menu proncipal...");
                    running = false;
                    break;
                default:
                    System.out.println("Opción no válida, por favor intenta de nuevo.");
            }
        }
    }

    public static void menuPrestamos(Cliente cliente){
        Scanner scanner = new Scanner(System.in);
        boolean running = true;


        while (running) {
            // Mostrar el menú principal
            System.out.println("==== Menú Prestamos ====");
            System.out.println("1. Solicitar prestamo");
            System.out.println("2. Gestionar prestamo existente");
            System.out.println("3. Volver al menu cliente");
            System.out.print("Selecciona una opción: ");

            int opcion = scanner.nextInt();

            // Manejar las opciones seleccionadas
            switch (opcion) {
                case 1:
                    solicitarPrestamo(cliente);
                    break;
                case 2:
                    menuPrestamoExistente(cliente);
                    break;
                case 3:
                    System.out.println("Volviendo al menu cliente...");
                    running = false;
                    break;
                default:
                    System.out.println("Opción no válida, por favor intenta de nuevo.");
            }
        }
    }

    public static void solicitarPrestamo(Cliente cliente) {
        Scanner scanner = new Scanner(System.in);
        PrestamoDAO prestamoDAO = new PrestamoDAO();

        System.out.println("Solicitud de Préstamo");
        System.out.println("======================");

        // Verificar si el cliente tiene préstamos activos
        int prestamosActivos = prestamoDAO.cantidadPrestamosActivosDeUnCliente(cliente.getIdCliente());
        if (prestamosActivos >= 2) { // Por ejemplo, no permitir más de 3 préstamos activos
            System.out.println("❌ No puedes solicitar un nuevo préstamo. Tienes " + prestamosActivos + " préstamos activos.");
            return;
        }

        // Solicitar monto del préstamo
        double monto;
        while (true) {
            System.out.println("Ingresa el monto del préstamo (mínimo $1.000.000,00 ; máximo $20.000.000,00, sin puntos, espacios o comas):");
            try {
                String input = scanner.nextLine().trim();
                if (input.contains(".") || input.contains(",")) {
                    System.out.println("❌ El monto no debe contener puntos, comas, ni espacios.");
                    continue;
                }
                monto = Double.parseDouble(input);
                if (monto >= 1000000 && monto <= 20000000) {
                    break;
                } else {
                    System.out.println("❌ El numero ingresado debe estar entre 1000000 y 20000000.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Debes ingresar un monto válido en número.");
            }
        }

        // Solicitar cantidad de cuotas
        int cantidadCuotas;
        while (true) {
            System.out.println("Ingresa la cantidad de cuotas (opciones disponibles: 12, 18, 24, 36):");
            try {
                cantidadCuotas = Integer.parseInt(scanner.nextLine().trim());
                if (cantidadCuotas == 12 || cantidadCuotas == 18 || cantidadCuotas == 24 || cantidadCuotas == 36) {
                    break;
                } else {
                    System.out.println("❌ La cantidad de cuotas debe ser 12, 18, 24 o 36.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Debes ingresar un número válido para las cuotas.");
            }
        }

        // Solicitar tipo de préstamo
        String tipoPrestamo;
        while (true) {
            System.out.println("Ingresa el tipo de préstamo (personal o hipotecario):");
            tipoPrestamo = scanner.nextLine().trim().toLowerCase();
            if (tipoPrestamo.equals("personal") || tipoPrestamo.equals("hipotecario")) {
                break;
            } else {
                System.out.println("❌ Tipo de préstamo no válido. Debe ser 'personal' o 'hipotecario'.");
            }
        }

        // Calcular saldo pendiente inicial (igual al monto)
        double saldoPendiente = monto;

        // El estado inicial del préstamo es "activo"
        String estado = "activo";

        // Crear el objeto Prestamo
        Prestamo nuevoPrestamo = new Prestamo(
                cliente.getIdCliente(),  // ID del cliente
                monto,                  // Monto solicitado
                cantidadCuotas,         // Cantidad de cuotas
                tipoPrestamo,           // Tipo de préstamo
                saldoPendiente,         // Saldo pendiente
                estado                  // Estado inicial del préstamo
        );

        // Registrar el préstamo en la base de datos
        boolean exito = prestamoDAO.crearPrestamo(nuevoPrestamo);

        if (exito) {
            System.out.println("✅ Préstamo registrado con éxito.");
            System.out.println("Detalles del Préstamo:");
            System.out.println("Monto: $" + monto);
            System.out.println("Interés: " + nuevoPrestamo.getTasaInteres() + "%");
            System.out.println("Cuotas: " + cantidadCuotas);
            System.out.println("Tipo: " + tipoPrestamo);
            System.out.println("Estado: " + estado);
        } else {
            System.out.println("❌ Hubo un problema al registrar el préstamo. Por favor, inténtalo nuevamente.");
        }

    }

    public static void menuPrestamoExistente(Cliente cliente) {
        ClienteDAO clienteDAO = new ClienteDAO();
        Scanner scanner = new Scanner(System.in);
        PrestamoDAO prestamoDAO = new PrestamoDAO();
        PagoDAO pagoDAO = new PagoDAO();


        int idElegido = -1;
        List<Prestamo> prestamos = prestamoDAO.obtenerPrestamosActivosPorCliente(cliente.getIdCliente());

        if (prestamoDAO.cantidadPrestamosActivosDeUnCliente(cliente.getIdCliente()) > 1) {
            System.out.println("Usted cuenta con más de un préstamo activo. Escoja el ID del prestamo sobre cuál quiere realizar operaciones.");

            System.out.println("+-------------+--------------+--------------+------------------+---------------------+---------------+");
            System.out.println("| ID Préstamo | Monto        | Tasa Interés | Cantidad Cuotas | Saldo Pendiente      | Estado        |");
            System.out.println("+-------------+--------------+--------------+------------------+---------------------+---------------+");
            for (Prestamo prestamo : prestamos) {
                System.out.printf("| %-11d | %-12.2f | %-12.2f | %-15d | %-20.2f | %-13s |\n",
                        prestamo.getIdPrestamo(), prestamo.getMonto(), prestamo.getTasaInteres(),
                        prestamo.getCantidadCuotas(), prestamo.getSaldoPendiente(), prestamo.getEstado());
            }
            System.out.println("+-------------+--------------+--------------+------------------+---------------------+---------------+");

            boolean idValido = false;

            do {
                System.out.print("Ingrese el ID del préstamo sobre el que desea operar: ");
                // Validar que el ingreso sea un número entero
                if (!scanner.hasNextInt()) {
                    System.out.println("❌ Error: Debe ingresar un número válido.");
                    scanner.next(); // Limpiar entrada inválida
                    continue;
                }

                idElegido = scanner.nextInt();

                // Verificar que el ID ingresado coincida con un préstamo válido
                for (Prestamo prestamo : prestamos) {
                    if (prestamo.getIdPrestamo() == idElegido) {
                        idValido = true;
                        idElegido = prestamo.getIdPrestamo();
                        break;
                    }
                }

                if (!idValido) {
                    System.out.println("❌ Error: El ID ingresado no coincide con ningún préstamo mostrado en la tabla.");
                }

            } while (!idValido);

            System.out.println("✅ Ha seleccionado el préstamo con ID " + idElegido + " para operar.");

        }else if (prestamoDAO.cantidadPrestamosActivosDeUnCliente(cliente.getIdCliente()) == 1) {
            System.out.println("Usted cuenta con el siguiente prestamo activo.");
            System.out.println("+-------------+--------------+--------------+------------------+---------------------+---------------+");
            System.out.println("| ID Préstamo | Monto        | Tasa Interés | Cantidad Cuotas | Saldo Pendiente      | Estado        |");
            System.out.println("+-------------+--------------+--------------+------------------+---------------------+---------------+");
            for (Prestamo prestamo : prestamos) {
                System.out.printf("| %-11d | %-12.2f | %-12.2f | %-15d | %-20.2f | %-13s |\n",
                        prestamo.getIdPrestamo(), prestamo.getMonto(), prestamo.getTasaInteres(),
                        prestamo.getCantidadCuotas(), prestamo.getSaldoPendiente(), prestamo.getEstado());
                idElegido = prestamo.getIdPrestamo();
            }
            System.out.println("+-------------+--------------+--------------+------------------+---------------------+---------------+");
        }else {
            System.out.println("Usted no tiene ningun prestamo activo. ¿¡Que es pera para solicitar un prestamo?!");
            return;
        }
        limpiarConsola();

        // Iniciar el menú una vez que el DNI sea válido
        boolean subMenuRunning = true;

        while (subMenuRunning) {
            limpiarConsola();
            System.out.println("=== Seleccione una opcion ===");
            System.out.println("1. Ver informacion general del prestamo");
            System.out.println("2. Ver cuotas pendientes");
            System.out.println("3. Ver cuotas pagadas");
            System.out.println("4. Pagar la siguiente cuota");
            System.out.println("5. Volver al menú prestamos");
            System.out.print("Selecciona una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea
            Prestamo prestamoSeleccionado = prestamoDAO.obtenerPrestamoPorId(idElegido);
            CuotaDAO cuotaDAO = new CuotaDAO();
            MoraDAO moraDAO = new MoraDAO();
            switch (opcion) {
                case 1:
                    System.out.println("+-------------+--------------+--------------+------------------+---------------------+---------------+");
                    System.out.println("| ID Préstamo | Monto        | Tasa Interés | Cantidad Cuotas | Saldo Pendiente      | Estado        |");
                    System.out.println("+-------------+--------------+--------------+------------------+---------------------+---------------+");
                    System.out.printf("| %-11d | %-12.2f | %-12.2f | %-15d | %-20.2f | %-13s |\n",
                            prestamoSeleccionado.getIdPrestamo(), prestamoSeleccionado.getMonto(), prestamoSeleccionado.getTasaInteres(),
                            prestamoSeleccionado.getCantidadCuotas(), prestamoSeleccionado.getSaldoPendiente(), prestamoSeleccionado.getEstado());
                    System.out.println("+-------------+--------------+--------------+------------------+---------------------+---------------+");
                    System.out.println(" ");
                    System.out.println("Al dia de la fecha: " + LocalDate.now());
                    double monto = prestamoSeleccionado.getMonto();
                    double saldoPendiente = prestamoSeleccionado.getSaldoPendiente();
                    double pagado = monto - saldoPendiente;
                    System.out.println("Ha pagado una suma de $" + formatoMoneda.format(pagado));
                    System.out.println("Le resta pagar una suma de $" + formatoMoneda.format(saldoPendiente)+ " de amortizacion. (Debe tener en cuenta intereses y el IVA en cada cuota.)");
                    System.out.println("El estado es del prestamo es:  " + prestamoSeleccionado.getEstado());

                    if(prestamoSeleccionado.getEstado().equals("moroso")){
                        System.out.println("Para verificar que cuotas estan en mora ingrese a la seccion de 'Ver cuotas pendientes'");
                        System.out.println("Porfavor regularice su deuda cuanto antes para evitar mayores recargos.");
                    }

                    break;

                case 2:
                    List<Cuota> cuotasImpagas = cuotaDAO.listarCuotasImpagasPorPrestamo(idElegido);

                    if (cuotasImpagas.isEmpty()) {
                        System.out.println("Usted no cuenta con cuotas pendientes al dia de la fecha.");
                    } else {
                        // Formato para la fecha
                        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

                        // Encabezado de la tabla
                        System.out.println("+----------+-----------------+----------------+--------------+-------------+-------------+--------------+");
                        System.out.println("| Nº Cuota | Fecha Vencim.   | Amortización   | Interés      | IVA         | Total Cuota  | Estado      |");
                        System.out.println("+----------+-----------------+----------------+--------------+-------------+--------------+-------------+");

                        // Iterar sobre las cuotas y mostrar sus datos
                        for (Cuota cuota : cuotasImpagas) {
                            System.out.printf("| %-8d | %-15s | %-14.2f | %-12.2f | %-11.2f | %-12.2f | %-11s |\n",

                                    cuota.getNumeroCuota(),
                                    formatoFecha.format(cuota.getFechaVencimiento()),
                                    cuota.getAmortizacion(),
                                    cuota.getInteres(),
                                    cuota.getIva(),
                                    cuota.getTotalCuota(),
                                    cuota.getEstado()
                            );
                        }
                        // Pie de la tabla
                        System.out.println("+----------+-----------------+----------------+--------------+-------------+--------------+-------------+");

                        mostrarSubmenuCuotasPendientes(scanner, cuotasImpagas, moraDAO, formatoFecha);

                    }
                    break;
                case 3:
                    List<Cuota> cuotasPagas = cuotaDAO.listarCuotasPagasPorPrestamo(idElegido);

                    if (cuotasPagas.isEmpty()) {
                        System.out.println("Usted no cuenta con cuotas pagadas al dia de la fecha.");
                    } else {
                        // Formato para la fecha
                        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

                        // Encabezado de la tabla
                        System.out.println("+----------+-----------------+----------------+--------------+-------------+-------------+--------------+");
                        System.out.println("| Nº Cuota | Fecha Vencim.   | Amortización   | Interés      | IVA         | Total Cuota  | Estado      |");
                        System.out.println("+----------+-----------------+----------------+--------------+-------------+--------------+-------------+");

                        // Iterar sobre las cuotas y mostrar sus datos
                        for (Cuota cuota : cuotasPagas) {
                            System.out.printf("| %-8d | %-15s | %-14.2f | %-12.2f | %-11.2f | %-12.2f | %-11s |\n",

                                    cuota.getNumeroCuota(),
                                    formatoFecha.format(cuota.getFechaVencimiento()),
                                    cuota.getAmortizacion(),
                                    cuota.getInteres(),
                                    cuota.getIva(),
                                    cuota.getTotalCuota(),
                                    cuota.getEstado()
                            );
                        }
                        // Pie de la tabla
                        System.out.println("+----------+-----------------+----------------+--------------+-------------+--------------+-------------+");

                        mostrarSubmenuCuotasPagas(scanner, cuotasPagas, pagoDAO, formatoFecha);

                    }
                    break;
                case 4:

                    List<Cuota> cuotasSinPagar = cuotaDAO.listarCuotasImpagasPorPrestamo(idElegido);
                    Cuota primeraCuotaImpaga = new Cuota();
                    if (cuotasSinPagar.isEmpty()) {
                        System.out.println("No hay cuotas impagas.");
                    } else {
                        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
                        primeraCuotaImpaga = cuotasSinPagar.get(0); // Obtener la primera cuota de la lista

                        // Mostrar la información de la primera cuota impaga
                        System.out.println("+----------+-----------+----------+");
                        System.out.println("| Nº Cuota | Fecha Vto | Monto    |");
                        System.out.println("+----------+-----------+----------+");
                        System.out.printf("| %-8d | %-9s | %-8.2f |\n",
                                primeraCuotaImpaga.getNumeroCuota(),
                                formatoFecha.format(primeraCuotaImpaga.getFechaVencimiento()),
                                primeraCuotaImpaga.getTotalCuota());
                        System.out.println("+----------+-----------+----------+");

                    }

                    String respuesta;

                    do {
                        System.out.println("¿Desea pagar la cuota? (S/N): ");
                        respuesta = scanner.nextLine().trim().toUpperCase();

                        if (respuesta.equals("S")) {
                            Date fechaPago = new Date(System.currentTimeMillis());
                            double montoPagado = primeraCuotaImpaga.getTotalCuota();
                            System.out.println("Seleccione el método de pago:");
                            System.out.println("1. Efectivo");
                            System.out.println("2. Tarjeta Debito/Credito");
                            System.out.println("3. Transferencia");
                            int opcionMetodoPago = scanner.nextInt();
                            scanner.nextLine(); // Consumir el salto de línea
                            String metodoPago = switch (opcionMetodoPago) {
                                case 1 -> "Efectivo";
                                case 2 -> "Tarjeta Debito/Credito";
                                case 3 -> "Transferencia";
                                default -> {
                                    System.out.println("Opción no válida. Se seleccionará 'Efectivo' por defecto.");
                                    yield "Efectivo";
                                }
                            };

                            Pago pago = new Pago(
                                    primeraCuotaImpaga.getIdCuota(),
                                    fechaPago,
                                    montoPagado,
                                    metodoPago
                            );
                            System.out.println("Procesando el pago...");
                            if (pagoDAO.pagar(pago)) {
                                System.out.println("✅ Pago realizado exitosamente.");
                                // Actualizar el estado de la cuota a "PAGADA"
                                if (cuotaDAO.actualizarEstado(primeraCuotaImpaga.getIdCuota(), "PAGADA")) {
                                    System.out.println("✅ Estado de la cuota actualizado a 'PAGADA'.");
                                } else {
                                    System.out.println("❌ Error al actualizar el estado de la cuota.");
                                }
                                Prestamo prestamo = prestamoDAO.obtenerPrestamoPorId(idElegido);
                                // Actualizar el saldo pendiente del préstamo
                                double nuevoSaldoPendiente = prestamo.getSaldoPendiente() - primeraCuotaImpaga.getAmortizacion();
                                if (prestamoDAO.actualizarSaldoPendiente(idElegido, nuevoSaldoPendiente)) {
                                    System.out.println("✅ Saldo pendiente del préstamo actualizado.");
                                    break;
                                } else {
                                    System.out.println("❌ Error al actualizar el saldo pendiente del préstamo.");
                                }

                            } else {
                                System.out.println("❌ Ocurrió un error al procesar el pago. Intente de nuevo.");
                            }

                            break;
                        } else if (respuesta.equals("N")) {
                            System.out.println("El pago ha sido cancelado.");
                            break;
                        } else {
                            System.out.println("Respuesta no válida, intente nuevamente.");
                        }
                    } while (true);
                    break;

                case 5:
                    System.out.println("Volviendo al menú prestamos...");
                    subMenuRunning = false;
                    break;

                default:
                    System.out.println("Opción no válida, por favor intenta de nuevo.");
            }

        }

    }

    public static void mostrarSubmenuCuotasPendientes(Scanner scanner, List<Cuota> cuotasImpagas, MoraDAO moraDAO, SimpleDateFormat formatoFecha) {
        int opcionSubmenu = 0;
        do {
            // Mostrar el submenú
            System.out.println("\n--- SUBMENÚ DE CUOTAS PENDIENTES---");
            System.out.println("1. Ver detalle de cuotas en Mora");
            System.out.println("2. Volver");
            System.out.print("Seleccione una opción: ");
            opcionSubmenu = scanner.nextInt();

            switch (opcionSubmenu) {
                case 1:
                    System.out.println("+----------+-----------------+-----------------+--------------+-----------+-----------+");
                    System.out.println("| Nº Cuota | Fecha Vencim.   | Monto Penalidad | Monto Cuota  | Total     | Estado    |");
                    System.out.println("+----------+-----------------+-----------------+--------------+-----------+-----------+");
                    for (Cuota cuota : cuotasImpagas) {
                        if (cuota.getEstado().equals("morosa")) {
                            double penalidad = moraDAO.calcularPenalidad(cuota);
                            double total = cuota.getTotalCuota() + penalidad;
                            System.out.printf("| %-8d | %-15s | %-15.2f | %-12.2f | %-9.2f | %-9s |\n",
                                    cuota.getNumeroCuota(),
                                    formatoFecha.format(cuota.getFechaVencimiento()),
                                    penalidad,
                                    cuota.getTotalCuota(),
                                    total,
                                    cuota.getEstado());
                        }
                    }
                    break;

                case 2:
                    System.out.println("Volviendo atras...");
                    break;

                default:
                    System.out.println("Opción no válida, intente nuevamente.");
                    break;
            }
        } while (opcionSubmenu != 2);
    }

    public static void mostrarSubmenuCuotasPagas(Scanner scanner, List<Cuota> cuotasPagas, PagoDAO pagoDAO, SimpleDateFormat formatoFecha) {
        int opcionSubmenu = 0;
        do {
            // Mostrar el submenú
            System.out.println("\n--- SUBMENÚ DE CUOTAS PAGAS ---");
            System.out.println("1. Ver constancias de pago");
            System.out.println("2. Volver");
            System.out.print("Seleccione una opción: ");
            opcionSubmenu = scanner.nextInt();

            switch (opcionSubmenu) {
                case 1:
                    System.out.println("+----------+-----------------+-----------------+--------------+");
                    System.out.println("| Nº Cuota | Fecha Pago.     | Monto Pagado.  | Metodo Pago.  |");
                    System.out.println("+----------+-----------------+-----------------+--------------+");
                    for (Cuota cuota : cuotasPagas) {
                        if (cuota.getEstado().equals("pagada")) {
                            int idCuota = cuota.getIdCuota();
                            Pago pago = pagoDAO.obtenerPagoPorIdCuota(idCuota);
                            System.out.printf("| %-8d | %-15s | %-15.2f | %-12s |\n",
                                    cuota.getNumeroCuota(),
                                    formatoFecha.format(pago.getFechaPago()),
                                    pago.getMontoPagado(),
                                    pago.getMetodoPago()
                            );
                        }
                    }
                    System.out.println("+----------+-----------------+-----------------+--------------+");
                    break;

                case 2:
                    System.out.println("Volviendo atras...");
                    break;

                default:
                    System.out.println("Opción no válida, intente nuevamente.");
                    break;
            }
        } while (opcionSubmenu != 2);
    }

    public static void limpiarConsola() {
        // Secuencia ANSI que borra la pantalla y mueve el cursor al tope
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    public static boolean esDniValido(int dni) {
        // Convertir el DNI a una cadena de texto
        String dniStr = String.valueOf(dni);

        // Verificar que la longitud esté entre 7 y 8 caracteres
        return dniStr.length() >= 7 && dniStr.length() <= 8;
    }
    private static boolean esTextoValido(String texto) {
        return texto.matches("^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ\\s]+$");
    }

    private static boolean registrarNuevoCliente(Scanner scanner, ClienteDAO clienteDAO) {
        try {
            // Solicitar y validar el nombre
            String nombre;
            while (true) {
                System.out.println("Ingresa tu nombre:");
                nombre = scanner.nextLine().trim();
                if (nombre.isEmpty()) {
                    System.out.println("El nombre no puede estar vacío. Inténtalo nuevamente.");
                } else if (!esTextoValido(nombre)) {
                    System.out.println("El nombre no puede contener números ni caracteres especiales. Inténtalo nuevamente.");
                } else {
                    break; // Nombre válido
                }
            }

            // Solicitar y validar el apellido
            String apellido;
            while (true) {
                System.out.println("Ingresa tu apellido:");
                apellido = scanner.nextLine().trim();
                if (apellido.isEmpty()) {
                    System.out.println("El apellido no puede estar vacío. Inténtalo nuevamente.");
                } else if (!esTextoValido(apellido)) {
                    System.out.println("El apellido no puede contener números ni caracteres especiales. Inténtalo nuevamente.");
                } else {
                    break; // Apellido válido
                }
            }

            // Solicitar y validar el DNI
            int dni;
            while (true) {
                System.out.println("Ingresa tu DNI (7 u 8 dígitos):");
                String dniInput = scanner.nextLine().trim();

                try {
                    dni = Integer.parseInt(dniInput);
                    if (!esDniValido(dni)) {
                        System.out.println("DNI no válido. Debe tener 7 u 8 dígitos. Intente de nuevo");

                    } else if (clienteDAO.existenciaClientePorDni(dni)) {
                        System.out.println("El DNI ya se encuentra registrado en el sistema. Intente de nuevo");
                    }else {
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("El DNI debe ser un número. Inténtalo nuevamente.");

                }

            }

            // Solicitar y validar la dirección
            String direccion;
            while (true) {
                System.out.println("Ingresa tu dirección:");
                direccion = scanner.nextLine().trim();
                if (direccion.isEmpty()) {
                    System.out.println("La dirección no puede estar vacía. Inténtalo nuevamente.");
                } else {
                    break; // Dirección válida
                }
            }

            // Solicitar y validar el teléfono
            String telefono;
            while (true) {
                System.out.println("Ingresa tu teléfono:");
                telefono = scanner.nextLine().trim();
                if (telefono.isEmpty()) {
                    System.out.println("El teléfono no puede estar vacío. Inténtalo nuevamente.");
                } else {
                    break; // Teléfono válido
                }
            }

            // Solicitar y validar el correo electrónico
            String correoElectronico;
            while (true) {
                System.out.println("Ingresa tu correo electrónico:");
                correoElectronico = scanner.nextLine().trim();
                if (!correoElectronico.contains("@") || correoElectronico.isEmpty()) {
                    System.out.println("El correo electrónico no es válido. Inténtalo nuevamente.");
                } else {
                    break; // Correo válido
                }
            }

            // Crear el objeto Cliente con los datos recopilados
            Cliente nuevoCliente = new Cliente(nombre, apellido, dni, direccion, telefono, correoElectronico);

            // Registrar cliente en la base de datos
            clienteDAO.registrarCliente(nuevoCliente);
            return true;

        } catch (Exception e) {
            System.out.println("Ocurrió un error durante el registro: " + e.getMessage());
            return false;
        }
    }



}