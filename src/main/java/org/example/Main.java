package org.example;

import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    static DecimalFormat formatoMoneda = new DecimalFormat("#,##0.00");
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        DecimalFormat formatoMoneda = new DecimalFormat("#,##0.00");
        ClienteDAO clienteDAO = new ClienteDAO();
        Cliente cliente; // Declaramos cliente aquí para que sea accesible en el scope
        int dni; // Declaramos dni aquí

        System.out.println("==== Bienvenido al sistema de Gestion financiera. ====");

        boolean running = true; // Renombré 'runningg' a 'running' para mejor práctica

        while (running) {
            limpiarConsola(); // Limpiamos la consola antes de mostrar el menú principal
            System.out.println("==== Menú Principal ====");
            System.out.println("1. Clientes");
            System.out.println("2. Administradores");
            System.out.println("3. Simulador de prestamos");
            System.out.println("4. Salir del sistema");
            System.out.print("Selecciona una opción: ");

            int opcionPrincipal = -1; // Inicializamos con un valor que no sea ninguna opción válida
            try {
                opcionPrincipal = scanner.nextInt();
            } catch (java.util.InputMismatchException e) {
                System.out.println("❌ Entrada no válida. Por favor, ingresa un número.");
                scanner.nextLine(); // Consumir la entrada incorrecta
                continue; // Volver al inicio del bucle while(running)
            }
            scanner.nextLine(); // IMPORTANTE: Consumir el salto de línea después de nextInt()

            switch (opcionPrincipal) {
                case 1: // Menú Clientes
                    boolean volverAlMenuPrincipal = false;
                    while (!volverAlMenuPrincipal) {
                        limpiarConsola();
                        System.out.println("==== opciones de inicio ====");
                        System.out.println("1. Iniciar sesión");
                        System.out.println("2. Crear cuenta");
                        System.out.println("3. Volver al menú principal");

                        int eleccionCliente = -1;
                        try {
                            eleccionCliente = scanner.nextInt();
                        } catch (java.util.InputMismatchException e) {
                            System.out.println("❌ Entrada no válida. Por favor, ingresa un número.");
                            scanner.nextLine(); // Consumir la entrada incorrecta
                            continue; // Volver al inicio del bucle while(!volverAlMenuPrincipal)
                        }
                        scanner.nextLine(); // IMPORTANTE: Consumir el salto de línea después de nextInt()

                        switch (eleccionCliente) {
                            case 1: // Iniciar Sesión
                                String inputDni;
                                boolean dniValidoLeido = false;
                                while (!dniValidoLeido) { // Bucle para pedir DNI hasta que sea válido o se decida volver
                                    System.out.println("Por favor, ingresa tu DNI (7 u 8 dígitos) o escribe 'volver' para regresar:");
                                    inputDni = scanner.nextLine().trim();

                                    if (inputDni.equalsIgnoreCase("volver")) {
                                        System.out.println("Regresando al menú anterior...");
                                        limpiarConsola();
                                        dniValidoLeido = true; // Salir del bucle interno
                                        break; // Salir del switch del caso 1 y volver al menú cliente
                                    }

                                    try {
                                        dni = Integer.parseInt(inputDni); // Convertir a int

                                        if (esDniValido(dni)) {
                                            if (clienteDAO.existenciaClientePorDni(dni)) {
                                                cliente = clienteDAO.obtenerClientePorDni(dni);
                                                limpiarConsola();
                                                System.out.println("Bienvenido Sr./Sra. " + cliente.getApellido() + " " + cliente.getNombre());
                                                menuClientes(cliente); // Llamar al menú específico del cliente
                                                dniValidoLeido = true; // DNI válido y cliente encontrado, salir del bucle
                                                // No hay break aquí para que vuelva al menú de cliente
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
                                break; // Salir del switch de eleccionCliente y volver al menú Cliente

                            case 2: // Crear Cuenta
                                limpiarConsola();
                                if (registrarNuevoCliente(scanner, clienteDAO)) {
                                    System.out.println("✅ Cliente registrado con éxito. Ahora debes ingresar al sistema como Cliente utilizando su DNI.");
                                    break;
                                } else {
                                    System.out.println("❌ No se registro al cliente.");
                                    break;
                                }

                            case 3: // Volver al menú principal
                                System.out.println("Regresando al menú principal...");
                                volverAlMenuPrincipal = true; // Cambiar la bandera para salir del bucle de cliente
                                break;

                            default:
                                System.out.println("⚠️ Opción inválida. Intente nuevamente.");
                                break;
                        }
                    }
                    break; // Salir del switch principal y volver al menú principal

                case 2: // Administradores
                    limpiarConsola();
                    System.out.print("Ingrese el nombre de usuario administrador del sistema: ");
                    String nombreUsuario = scanner.nextLine(); // Ya se consumió el salto de línea antes de este switch

                    System.out.print("Ingrese la contraseña: ");
                    String contraseña = scanner.nextLine();

                    if (nombreUsuario.equals("admin") && contraseña.equals("admin")) {
                        limpiarConsola();
                        System.out.println("===== Bienvenido al sistema Sr.administrador. ======");
                        menuAdministradores(); // Llama al menú de administradores
                    } else {
                        System.out.println("Usted no tiene acceso al sistema como administrador. Por favor, larguese.");
                        System.out.println("Presiona Enter para continuar...");
                        scanner.nextLine(); // Espera que el usuario presione Enter para continuar
                    }
                    break;
                case 3:

                    CuotaDAO cuotaDAO = new CuotaDAO();
                    PrestamoDAO prestamoDAO = new PrestamoDAO(); // Asumo que tienes una instancia de PrestamoDAO

                    limpiarConsola();
                    System.out.println("Solicitud de Préstamo");
                    System.out.println("======================");

                    double monto = 0;
                    int cuotas = 0;
                    String tipoPrestamo = "";
                    boolean solicitudCancelada = false; // ⭐ Nueva bandera para controlar la cancelación ⭐

                    int pasoActual = 1;

                    while (pasoActual <= 3 && !solicitudCancelada) { // ⭐ El bucle ahora depende de la bandera ⭐
                        limpiarConsola(); // Limpiar al inicio de cada paso para una vista limpia
                        switch (pasoActual) {
                            case 1: // Paso 1: Monto
                                System.out.println("--- SOLICITUD DE PRÉSTAMO (Paso " + pasoActual + " de 3) ---");
                                System.out.println("Ingresa el monto del préstamo (mínimo $1.000.000 ; máximo $20.000.000, sin puntos, comas o espacios):");
                                System.out.println("✱ Escribe 'cancelar' para salir.");
                                String inputMonto = scanner.nextLine().trim();

                                if (esCancelar(inputMonto)) {
                                    solicitudCancelada = true; // ⭐ Marca la bandera y saldrá del while ⭐
                                    break; // Sale del switch
                                }

                                if (inputMonto.contains(".") || inputMonto.contains(",") || inputMonto.contains(" ")) {
                                    System.out.println("❌ El monto no debe contener puntos, comas ni espacios.");
                                    System.out.println("Presiona Enter para continuar...");
                                    scanner.nextLine(); // Esperar al usuario
                                    break; // Permanece en el mismo paso
                                }

                                try {
                                    monto = Double.parseDouble(inputMonto);
                                    if (monto >= 1000000 && monto <= 20000000) {
                                        pasoActual++; // Pasa al siguiente paso
                                    } else {
                                        System.out.println("❌ El monto debe estar entre $1.000.000 y $20.000.000.");
                                        System.out.println("Presiona Enter para continuar...");
                                        scanner.nextLine();
                                    }
                                } catch (NumberFormatException e) {
                                    System.out.println("❌ Debes ingresar un número válido.");
                                    System.out.println("Presiona Enter para continuar...");
                                    scanner.nextLine();
                                }
                                break;

                            case 2: // Paso 2: Cuotas
                                System.out.println("--- SOLICITUD DE PRÉSTAMO (Paso " + pasoActual + " de 3) ---");
                                System.out.println("Ingresa la cantidad de cuotas (opciones: 12, 18, 24, 36):");
                                System.out.println("✱ Escribe 'volver' para regresar al monto.");
                                System.out.println("✱ Escribe 'cancelar' para salir.");
                                String inputCuotas = scanner.nextLine().trim();

                                if (esCancelar(inputCuotas)) {
                                    solicitudCancelada = true; // ⭐ Marca la bandera y saldrá del while ⭐
                                    break;
                                }
                                if (esVolver(inputCuotas)) {
                                    pasoActual--; // Regresa al paso anterior
                                    break;
                                }

                                try {
                                    cuotas = Integer.parseInt(inputCuotas);
                                    if (cuotas == 12 || cuotas == 18 || cuotas == 24 || cuotas == 36) {
                                        pasoActual++; // Pasa al siguiente paso
                                    } else {
                                        System.out.println("❌ Las cuotas deben ser 12, 18, 24 o 36.");
                                        System.out.println("Presiona Enter para continuar...");
                                        scanner.nextLine();
                                    }
                                } catch (NumberFormatException e) {
                                    System.out.println("❌ Debes ingresar un número válido.");
                                    System.out.println("Presiona Enter para continuar...");
                                    scanner.nextLine();
                                }
                                break;

                            case 3: // Paso 3: Tipo de préstamo
                                System.out.println("--- SOLICITUD DE PRÉSTAMO (Paso " + pasoActual + " de 3) ---");
                                System.out.println("Selecciona el tipo de préstamo:");
                                System.out.println("1. Personal");
                                System.out.println("2. Hipotecario");
                                System.out.println("✱ Escribe 'volver' para regresar a cuotas.");
                                System.out.println("✱ Escribe 'cancelar' para salir.");
                                String inputTipo = scanner.nextLine().trim();

                                if (esCancelar(inputTipo)) {
                                    solicitudCancelada = true; // ⭐ Marca la bandera y saldrá del while ⭐
                                    break;
                                }
                                if (esVolver(inputTipo)) {
                                    pasoActual--; // Regresa al paso anterior
                                    break;
                                }

                                if (inputTipo.equals("1")) {
                                    tipoPrestamo = "personal";
                                    pasoActual++; // Pasa al siguiente paso
                                } else if (inputTipo.equals("2")) {
                                    tipoPrestamo = "hipotecario";
                                    pasoActual++; // Pasa al siguiente paso
                                } else {
                                    System.out.println("❌ Opción no válida. Elegí 1 o 2.");
                                    System.out.println("Presiona Enter para continuar...");
                                    scanner.nextLine();
                                }
                                break;
                        }
                    }

                    // ⭐ Solo si la solicitud NO fue cancelada, procedemos a mostrar y confirmar ⭐
                    if (!solicitudCancelada) {
                        // Crear y registrar el préstamo
                        // Nota: Asegúrate de que Prestamo y Cuota sean clases accesibles con sus atributos
                        // y que PrestamoDAO y CuotaDAO tengan los métodos correspondientes.

                        double saldoPendiente = monto; // Asumo que el saldo inicial es el monto total
                        String estadoPrestamo = "pendiente"; // Estado inicial del préstamo (activo o pendiente)
                        // Constructor de Prestamo: idPrestamo, monto, cuotas, tipoPrestamo, saldoPendiente, estado
                        Prestamo nuevoPrestamo = new Prestamo(-1, monto, cuotas, tipoPrestamo, saldoPendiente, estadoPrestamo);

                        List<Cuota> cuotasGeneradas = cuotaDAO.generarCuotas(nuevoPrestamo); // Asumo que generarCuotas está en CuotaDAO
                        limpiarConsola();

                        System.out.println("✅ Estos serían los datos del préstamo:");
                        System.out.println("Monto: $" + String.format("%,.2f", monto)); // Formato de moneda
                        System.out.println("Interés: " + String.format("%.2f", nuevoPrestamo.getTasaInteres()) + "%"); // Asumo getTasaInteres existe
                        System.out.println("Cuotas: " + cuotas);
                        System.out.println("Tipo: " + tipoPrestamo);
                        System.out.println(" ");

                        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

                        System.out.println("+----------+-----------------+----------------+--------------+-------------+--------------+-------------+");
                        System.out.println("| Nº Cuota | Fecha Vencim.   | Amortización   | Interés      | IVA         | Total Cuota  | Estado      |");
                        System.out.println("+----------+-----------------+----------------+--------------+-------------+--------------+-------------+");

                        for (Cuota cuota : cuotasGeneradas) {
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
                        System.out.println("+----------+-----------------+----------------+--------------+-------------+--------------+-------------+");
                    }
                    break;

                case 4:
                    System.out.println("Saliendo del programa. ¡Gracias!");
                    running = false; // Cambia la bandera para salir del bucle principal
                    break;

                default:
                    System.out.println("Opción no válida, por favor intenta de nuevo.");
                    System.out.println("Presiona Enter para continuar...");
                    scanner.nextLine(); // Espera que el usuario presione Enter para continuar
                    break;
            }
        }
        scanner.close();
    }

    public static void menuClientes(Cliente cliente){
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        ClienteDAO clienteDAO = new ClienteDAO();

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
                    limpiarConsola();
                    menuPrestamos(cliente);
                    break;

                case 2:
                    limpiarConsola();
                    System.out.println("==== Datos Personales del Cliente ====");
                    System.out.println("ID Cliente: " + cliente.getIdCliente());
                    System.out.println("Nombre: " + cliente.getNombre());
                    System.out.println("Apellido: " + cliente.getApellido());
                    System.out.println("DNI: " + cliente.getDni());
                    System.out.println("Dirección: " + cliente.getDireccion());
                    System.out.println("Teléfono: " + cliente.getTelefono());
                    System.out.println("Correo Electrónico: " + cliente.getCorreoElectronico());

                    break;
                case 3:
                    limpiarConsola();
                    System.out.println("==== Editar Datos Personales ====");
                    System.out.println("¿Qué campo deseas modificar?");
                    System.out.println("1. Nombre");
                    System.out.println("2. Apellido");
                    System.out.println("3. Dirección");
                    System.out.println("4. Teléfono");
                    System.out.println("5. Correo Electrónico");
                    System.out.println("6. Volver al menu cliente");
                    System.out.print("Selecciona una opción: ");
                    int opcionEditar = scanner.nextInt();
                    scanner.nextLine();

                    String campo = null;
                    String nuevoValor = null;

                    switch (opcionEditar) {
                        case 1:
                            campo = "nombre";
                            System.out.print("Ingresa el nuevo nombre: ");
                            nuevoValor = scanner.nextLine();
                            clienteDAO.editarCampoEspecifico(cliente.getIdCliente(), campo, nuevoValor);
                            cliente.setNombre(nuevoValor);
                            limpiarConsola();
                            break;

                        case 2:
                            campo = "apellido";
                            System.out.print("Ingresa el nuevo apellido: ");
                            nuevoValor = scanner.nextLine();
                            clienteDAO.editarCampoEspecifico(cliente.getIdCliente(), campo, nuevoValor);
                            cliente.setApellido(nuevoValor);
                            limpiarConsola();
                            break;

                        case 3:
                            campo = "direccion";
                            System.out.print("Ingresa la nueva dirección: ");
                            nuevoValor = scanner.nextLine();
                            clienteDAO.editarCampoEspecifico(cliente.getIdCliente(), campo, nuevoValor);
                            cliente.setDireccion(nuevoValor);
                            limpiarConsola();
                            break;

                        case 4:
                            campo = "telefono";
                            System.out.print("Ingresa el nuevo teléfono: ");
                            nuevoValor = scanner.nextLine();
                            clienteDAO.editarCampoEspecifico(cliente.getIdCliente(), campo, nuevoValor);
                            cliente.setTelefono(nuevoValor);
                            limpiarConsola();
                            break;

                        case 5:
                            campo = "correoElectronico";
                            System.out.print("Ingresa el nuevo correo electrónico: ");
                            nuevoValor = scanner.nextLine();
                            clienteDAO.editarCampoEspecifico(cliente.getIdCliente(), campo, nuevoValor);
                            cliente.setCorreoElectronico(nuevoValor);
                            limpiarConsola();
                            break;
                        case 6:
                            System.out.println("Vuelve al menu principal...");
                            limpiarConsola();
                            break;

                        default:
                            System.out.println("Opción no válida, por favor intenta de nuevo.");
                    }

                    if (campo != null && nuevoValor != null) {
                        boolean actualizado = clienteDAO.editarCampoEspecifico(cliente.getIdCliente(), campo, nuevoValor);
                        if (actualizado) {
                            System.out.println("✅ El campo '" + campo + "' ha sido actualizado correctamente.");
                        } else {
                            System.out.println("❌ Hubo un problema al actualizar el campo '" + campo + "'.");
                        }
                    }
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

    public static void menuAdministradores() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        ClienteDAO clienteDAO = new ClienteDAO();
        PrestamoDAO prestamoDAO = new PrestamoDAO();
        List<Prestamo> prestamos = prestamoDAO.listarTodosLosPrestamos();
        List<Cliente> clientes = clienteDAO.listarClientes();

        while (running) {

            System.out.println("==== Menú Administrativo ====");
            System.out.println("1. Listar todos los clientes");
            System.out.println("2. Listar todos los prestamos");
            System.out.println("3. Balance economico");
            System.out.println("4. Volver al menu principal");
            System.out.print("Selecciona una opción: ");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    limpiarConsola();
                    // Imprimir encabezado de la tabla
                    System.out.println("+-------------+--------------+----------------+---------+------------------------------------------+---------------+--------------------------------+");
                    System.out.println("| ID Cliente  | Nombre       | Apellido       | DNI     | Dirección                                | Teléfono      | Correo Electrónico             |");
                    System.out.println("+-------------+--------------+----------------+---------+------------------------------------------+---------------+--------------------------------+");

                    // Imprimir los datos de cada cliente
                    for (Cliente cliente : clientes) {
                        System.out.printf(
                                "| %-11d | %-12s | %-14s | %-7d | %-40s | %-13s | %-30s |\n",
                                cliente.getIdCliente(),
                                cliente.getNombre(),
                                cliente.getApellido(),
                                cliente.getDni(),
                                cliente.getDireccion(),
                                cliente.getTelefono(),
                                cliente.getCorreoElectronico()
                        );
                    }

                    // Imprimir el pie de la tabla
                    System.out.println("+-------------+--------------+----------------+---------+------------------------------------------+---------------+--------------------------------+");
                    break;
                case 2:

                    System.out.println("+-------------+--------------+--------------+------------------+---------------------+---------------+");
                    System.out.println("| ID Préstamo | Monto        | Tasa Interés | Cantidad Cuotas | Saldo Pendiente      | Estado        |");
                    System.out.println("+-------------+--------------+--------------+------------------+---------------------+---------------+");
                    for (Prestamo prestamo : prestamos) {
                        System.out.printf("| %-11d | %-12.2f | %-12.2f | %-15d | %-20.2f | %-13s |\n",
                                prestamo.getIdPrestamo(), prestamo.getMonto(), prestamo.getTasaInteres(),
                                prestamo.getCantidadCuotas(), prestamo.getSaldoPendiente(), prestamo.getEstado());
                    }
                    System.out.println("+-------------+--------------+--------------+------------------+---------------------+---------------+");
                    break;


                case 3:
                    limpiarConsola();
                    menuEstadisticasAdministradores(prestamos, clientes);

                    break;
                case 4:
                    limpiarConsola();
                    System.out.println(" ");
                    System.out.println("volvliendo al menu principal...");
                    System.out.println(" ");

                    running = false;
                    break;
                default:
                    System.out.println("Opción no válida, por favor intenta de nuevo.");
            }
        }
    }

    public static void menuEstadisticasAdministradores(List<Prestamo> prestamos, List<Cliente> clientes) {
        Scanner scanner = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("#,##0.00");
        limpiarConsola();
        while (true) {
            System.out.println("Seleccione una opción de las estadísticas de préstamos:");
            System.out.println("1. Mostrar tabla de todos los préstamos");
            System.out.println("2. Mostrar total del saldo pendiente");
            System.out.println("3. Mostrar total recaudado");
            System.out.println("4. Mostrar cantidad de préstamos activos");
            System.out.println("5. Mostrar cantidad de préstamos cancelados");
            System.out.println("6. Promedio del dinero prestado por cliente");
            System.out.println("7. Intereses totales generados");
            System.out.println("8. Promedio del monto de préstamos activos");
            System.out.println("9. Top 3 clientes más endeudados");
            System.out.println("10. Porcentaje de préstamos activos vs cancelados");
            System.out.println("0. Volver al menú principal");
            System.out.print("Ingrese su opción: ");

            int opcion = scanner.nextInt();
            System.out.println("-------------------------------------------------------------------------------------------------------");

            switch (opcion) {
                case 1:
                    limpiarConsola();
                    System.out.println("+-------------+--------------+--------------+------------------+---------------------+---------------+");
                    System.out.println("| ID Préstamo | Monto        | Tasa Interés | Cantidad Cuotas | Saldo Pendiente      | Estado        |");
                    System.out.println("+-------------+--------------+--------------+------------------+---------------------+---------------+");
                    for (Prestamo prestamo : prestamos) {
                        System.out.printf("| %-11d | %-12.2f | %-12.2f | %-15d | %-20.2f | %-13s |\n",
                                prestamo.getIdPrestamo(), prestamo.getMonto(), prestamo.getTasaInteres(),
                                prestamo.getCantidadCuotas(), prestamo.getSaldoPendiente(), prestamo.getEstado());
                    }
                    System.out.println("+-------------+--------------+--------------+------------------+---------------------+---------------+");
                    break;

                case 2:
                    limpiarConsola();
                    double suma = prestamos.stream()
                            .filter(prestamo -> prestamo.getEstado().equals("activo"))
                            .mapToDouble(Prestamo::getSaldoPendiente)
                            .sum();
                    System.out.println("El dinero total que se debe cancelar a la financiera en carácter de préstamos activos es:");
                    System.out.println("$ " + df.format(suma));
                    break;

                case 3:
                    limpiarConsola();
                    double pagos = prestamos.stream()
                            .filter(prestamo -> prestamo.getEstado().equals("activo"))
                            .mapToDouble(prestamo -> prestamo.getMonto() - prestamo.getSaldoPendiente())
                            .sum();
                    System.out.println("El dinero total recaudado por la financiera en carácter de préstamos activos es:");
                    System.out.println("$ " + df.format(pagos));
                    break;

                case 4:
                    limpiarConsola();
                    long activos = prestamos.stream()
                            .filter(prestamo -> prestamo.getEstado().equals("activo"))
                            .count();
                    System.out.println("Cantidad total de préstamos ACTIVOS: " + activos);
                    break;

                case 5:
                    limpiarConsola();
                    long cancelados = prestamos.stream()
                            .filter(prestamo -> prestamo.getEstado().equals("cancelado"))
                            .count();
                    System.out.println("La cantidad de préstamos CANCELADOS al día de la fecha es: " + cancelados);
                    break;

                case 6:
                    limpiarConsola();
                    double totalPrestado = prestamos.stream().mapToDouble(Prestamo::getMonto).sum();
                    int cantidadClientes = clientes.size();
                    double promedioPrestado = cantidadClientes > 0 ? totalPrestado / cantidadClientes : 0.0;
                    System.out.println("Promedio del dinero prestado por cliente: $ " + df.format(promedioPrestado));
                    break;

                case 7:
                    limpiarConsola();
                    double interesesTotales = prestamos.stream()
                            .filter(prestamo -> prestamo.getEstado().equals("activo") || prestamo.getEstado().equals("cancelado"))
                            .mapToDouble(prestamo -> prestamo.getMonto() * prestamo.getTasaInteres() / 100)
                            .sum();
                    System.out.println("Intereses totales generados: $ " + df.format(interesesTotales));
                    break;

                case 8:
                    limpiarConsola();
                    double totalMontoActivos = prestamos.stream()
                            .filter(prestamo -> prestamo.getEstado().equals("activo"))
                            .mapToDouble(Prestamo::getMonto)
                            .sum();
                    long cantidadActivos = prestamos.stream()
                            .filter(prestamo -> prestamo.getEstado().equals("activo"))
                            .count();
                    double promedioMontoActivo = cantidadActivos > 0 ? totalMontoActivos / cantidadActivos : 0.0;
                    System.out.println("Promedio del monto de préstamos ACTIVOS: $ " + df.format(promedioMontoActivo));
                    break;

                case 9:
                    limpiarConsola();
                    System.out.println("Top 3 clientes más endeudados:");
                    prestamos.stream()
                            .filter(prestamo -> prestamo.getEstado().equals("activo"))
                            .sorted((p1, p2) -> Double.compare(p2.getSaldoPendiente(), p1.getSaldoPendiente()))
                            .limit(3)
                            .forEach(prestamo -> System.out.printf("ID Cliente: %d, Saldo pendiente: $ %s\n",
                                    prestamo.getIdPrestamo(),
                                    df.format(prestamo.getSaldoPendiente())));
                    break;

                case 10:
                    limpiarConsola();
                    int totalPrestamos = prestamos.size();
                    // Calcular la cantidad de préstamos activos y cancelados
                    long activoss = prestamos.stream().filter(prestamo -> prestamo.getEstado().equals("activo")).count();
                    long canceladoss = prestamos.stream().filter(prestamo -> prestamo.getEstado().equals("cancelado")).count();

                    // Calcular los porcentajes
                    double porcentajeActivos = totalPrestamos > 0 ? (activoss * 100.0 / totalPrestamos) : 0.0;
                    double porcentajeCancelados = totalPrestamos > 0 ? (canceladoss * 100.0 / totalPrestamos) : 0.0;

                    // Imprimir resultados
                    System.out.printf("Préstamos Activos: %.2f%%\n", porcentajeActivos);
                    System.out.printf("Préstamos Cancelados: %.2f%%\n", porcentajeCancelados);
                    break;

                case 0:
                    limpiarConsola();
                    System.out.println("Volviendo al menú principal...");
                    return;

                default:
                    System.out.println("Opción no válida. Inténtelo nuevamente.");
            }

            System.out.println("-------------------------------------------------------------------------------------------------------");
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

            /*Esta logica es para actualizar las cuotas en mora del cliente. Antes de que pueda solicitar
             un nuevo prestamo o pagar una cuota verficamos si no esta en mora*/

            PrestamoDAO prestamoDAO = new PrestamoDAO();
            CuotaDAO cuotaDAO = new CuotaDAO();
            MoraDAO moraDao = new MoraDAO();

            List<Prestamo> prestamos = prestamoDAO.obtenerPrestamosPorCliente(cliente.getIdCliente());
            for(Prestamo prestamo:prestamos){
                int idprestamo = prestamo.getIdPrestamo();
                List<Cuota> cuotasImpagas= cuotaDAO.listarCuotasImpagasPorPrestamo(idprestamo);
                moraDao.registrarMorasYActualizarPrestamo(cuotasImpagas);
                //System.out.print("Se verificaron las cuotas morosas del pretamo: "+ idprestamo);
            }
            /*-----------------------------------------------------------------------------------------*/

            int opcion = scanner.nextInt();

            // Manejar las opciones seleccionadas
            switch (opcion) {
                case 1:
                    limpiarConsola();
                    solicitarPrestamo(cliente);
                    break;
                case 2:
                    limpiarConsola();
                    menuPrestamoExistente(cliente);
                    break;
                case 3:
                    limpiarConsola();
                    System.out.println("Volviendo al menu cliente...");
                    running = false;
                    break;
                default:
                    System.out.println("Opción no válida, por favor intenta de nuevo.");
            }
        }
    }

    public static void solicitarPrestamo(Cliente cliente){
        Scanner scanner = new Scanner(System.in);
        PrestamoDAO prestamoDAO = new PrestamoDAO();
        CuotaDAO cuotaDAO = new CuotaDAO();

        limpiarConsola();
        System.out.println("Solicitud de Préstamo");
        System.out.println("======================");

        int prestamosActivos = prestamoDAO.cantidadPrestamosActivosDeUnCliente(cliente.getIdCliente());
        if (prestamosActivos >= 2) {
            System.out.println("❌ No puedes solicitar un nuevo préstamo. Tienes " + prestamosActivos + " préstamos activos.");
            return;
        }

        double monto = 0;
        int cuotas = 0;
        String tipoPrestamo = "";

        int pasoActual = 1;

        while (pasoActual <= 3) {
            switch (pasoActual) {
                case 1:
                    limpiarConsola();
                    System.out.println("Ingresa el monto del préstamo (mínimo $1.000.000 ; máximo $20.000.000, sin puntos, comas o espacios):");
                    System.out.println("✱ Escribe 'cancelar' para salir.");
                    String inputMonto = scanner.nextLine().trim();

                    if (esCancelar(inputMonto)) return;

                    if (inputMonto.contains(".") || inputMonto.contains(",") || inputMonto.contains(" ")) {
                        System.out.println("❌ El monto no debe contener puntos, comas ni espacios.");
                        break;
                    }

                    try {
                        monto = Double.parseDouble(inputMonto);
                        if (monto >= 1000000 && monto <= 20000000) {
                            pasoActual++;
                        } else {
                            System.out.println("❌ El monto debe estar entre 1000000 y 20000000.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("❌ Debes ingresar un número válido.");
                    }
                    break;

                case 2:
                    limpiarConsola();
                    System.out.println("Ingresa la cantidad de cuotas (opciones: 12, 18, 24, 36):");
                    System.out.println("✱ Escribe 'volver' para regresar al monto.");
                    System.out.println("✱ Escribe 'cancelar' para salir.");
                    String inputCuotas = scanner.nextLine().trim();

                    if (esCancelar(inputCuotas)) return;
                    if (esVolver(inputCuotas)) {
                        pasoActual--;
                        break;
                    }

                    try {
                        cuotas = Integer.parseInt(inputCuotas);
                        if (cuotas == 12 || cuotas == 18 || cuotas == 24 || cuotas == 36) {
                            pasoActual++;
                        } else {
                            System.out.println("❌ Las cuotas deben ser 12, 18, 24 o 36.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("❌ Debes ingresar un número válido.");
                    }
                    break;

                case 3:
                    limpiarConsola();
                    System.out.println("Selecciona el tipo de préstamo:");
                    System.out.println("1. Personal");
                    System.out.println("2. Hipotecario");
                    System.out.println("✱ Escribe 'volver' para regresar a cuotas.");
                    System.out.println("✱ Escribe 'cancelar' para salir.");
                    String inputTipo = scanner.nextLine().trim();

                    if (esCancelar(inputTipo)) return;
                    if (esVolver(inputTipo)) {
                        pasoActual--;
                        break;
                    }

                    if (inputTipo.equals("1")) {
                        tipoPrestamo = "personal";
                        pasoActual++;
                    } else if (inputTipo.equals("2")) {
                        tipoPrestamo = "hipotecario";
                        pasoActual++;
                    } else {
                        System.out.println("❌ Opción no válida. Elegí 1 o 2.");
                    }
                    break;
            }
        }

        // Crear y registrar el préstamo
        double saldoPendiente = monto;
        String estado = "activo";
        Prestamo nuevoPrestamo = new Prestamo(cliente.getIdCliente(), monto, cuotas, tipoPrestamo, saldoPendiente, estado);
        List<Cuota> cuotasGeneradas = cuotaDAO.generarCuotas(nuevoPrestamo);

        limpiarConsola();
        System.out.println("✅ Estos serian los datos del prestamo: ");
        System.out.println("Monto: $" + monto);
        System.out.println("Interés: " + nuevoPrestamo.getTasaInteres() + "%");
        System.out.println("Cuotas: " + cuotas);
        System.out.println("Tipo: " + tipoPrestamo);
        System.out.println(" ");

        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

        System.out.println("+----------+-----------------+----------------+--------------+-------------+--------------+-------------+");
        System.out.println("| Nº Cuota | Fecha Vencim.   | Amortización   | Interés      | IVA         | Total Cuota  | Estado      |");
        System.out.println("+----------+-----------------+----------------+--------------+-------------+--------------+-------------+");

        for (Cuota cuota : cuotasGeneradas) {
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

        System.out.println("+----------+-----------------+----------------+--------------+-------------+--------------+-------------+");

        System.out.println("¿Quiere confirmar la solicitud del prestamo?");
        System.out.println("1. Sí");
        System.out.println("2. No");
        int opcion = 0;

        while (opcion != 1 && opcion != 2) {
            try {
                String input = scanner.nextLine().trim();
                opcion = Integer.parseInt(input);

                if (opcion == 1) {

                    int idPrestamo = prestamoDAO.crearPrestamo(nuevoPrestamo);
                    boolean cuotasInsertadas = cuotaDAO.insertarCuotas(cuotasGeneradas, idPrestamo);

                    if (cuotasInsertadas) {
                        System.out.println("✅ Préstamo registrado con éxito.");

                    } else {

                        System.out.println("❌ Error al insertar las cuotas del préstamo.");

                        opcion = 2;
                    }
                } else if (opcion == 2) {
                    System.out.println("Saliendo del flujo de solicitud de préstamo.");

                } else {

                    System.out.println("❌ Opción no válida. Por favor, ingrese 1 para Sí o 2 para No.");
                    System.out.println("¿Quiere confirmar la solicitud del préstamo?");
                    System.out.println("1. Sí");
                    System.out.println("2. No");
                }
            } catch (NumberFormatException e) {

                System.out.println("❌ Debes ingresar un número válido.");
                System.out.println("¿Quiere confirmar la solicitud del préstamo?");
                System.out.println("1. Sí");
                System.out.println("2. No");
            }
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
            limpiarConsola();
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
            limpiarConsola();
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
                    limpiarConsola();
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
                    limpiarConsola();
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
                    limpiarConsola();
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
                    limpiarConsola();
                    List<Cuota> cuotasSinPagar = cuotaDAO.listarCuotasImpagasPorPrestamo(idElegido);
                    Cuota primeraCuotaImpaga = new Cuota();
                    if (cuotasSinPagar.isEmpty()) {
                        System.out.println("No hay cuotas impagas.");
                    } else {
                        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
                        primeraCuotaImpaga = cuotasSinPagar.get(0); // Obtener la primera cuota de la lista

                        // Mostrar la información de la primera cuota impaga
                        System.out.println("+----------+-----------+---------------+");
                        System.out.println("| Nº Cuota | Fecha Vto | Monto         |");
                        System.out.println("+----------+-----------+---------------+");
                        System.out.printf("| %-8d | %-9s | %-13.2f |\n",
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
                                if (cuotaDAO.actualizarEstado(primeraCuotaImpaga.getIdCuota(), "pagada")) {
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

                                // si esta pagando la ultima cuota del prestamo entonces se marca como cancelado.
                                if(prestamo.getCantidadCuotas() == primeraCuotaImpaga.getNumeroCuota()){
                                    prestamoDAO.actualizarEstado(idElegido, "cancelado");
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
                    limpiarConsola();
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
                    limpiarConsola();
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
                    limpiarConsola();
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
                    limpiarConsola();
                    System.out.println("+----------+-----------------+-----------------+-------------------------+");
                    System.out.println("| Nº Cuota | Fecha Pago.     | Monto Pagado.  | Metodo Pago.             |");
                    System.out.println("+----------+-----------------+-----------------+-------------------------+");
                    for (Cuota cuota : cuotasPagas) {
                        if (cuota.getEstado().equals("pagada")) {
                            int idCuota = cuota.getIdCuota();
                            Pago pago = pagoDAO.obtenerPagoPorIdCuota(idCuota);
                            System.out.printf("| %-8d | %-15s | %-15.2f | %-23s |\n",
                                    cuota.getNumeroCuota(),
                                    formatoFecha.format(pago.getFechaPago()),
                                    pago.getMontoPagado(),
                                    pago.getMetodoPago()
                            );
                        }
                    }
                    System.out.println("+----------+-----------------+-----------------+-------------------------+");
                    break;

                case 2:
                    limpiarConsola();
                    System.out.println("Volviendo atras...");
                    break;

                default:
                    System.out.println("Opción no válida, intente nuevamente.");
                    break;
            }
        } while (opcionSubmenu != 2);
    }

    public static void limpiarConsola() {
        try {
            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                // Para Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Para Linux/MacOS
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            String nombre = "", apellido = "", direccion = "", telefono = "", correoElectronico = "";
            int dni = -1;

            int paso = 1;

            while (paso <= 6) {
                limpiarConsola(); // Limpiar consola al inicio de cada paso
                switch (paso) {
                    case 1:
                        System.out.println("--- REGISTRO DE NUEVO CLIENTE (Paso " + paso + " de 6) ---");
                        System.out.println("Ingresa tu nombre (escribe 'cancelar' para salir):");
                        nombre = scanner.nextLine().trim();
                        if (nombre.equalsIgnoreCase("cancelar")) return false;
                        if (nombre.isEmpty()) {
                            System.out.println("⚠️ El nombre no puede estar vacío.");
                            System.out.println("Presiona Enter para continuar...");
                            scanner.nextLine();
                        } else if (!esTextoValido(nombre)) {
                            System.out.println("⚠️ El nombre no puede contener números ni caracteres especiales.");
                            System.out.println("Presiona Enter para continuar...");
                            scanner.nextLine();
                        } else {
                            paso++;
                        }
                        break;

                    case 2:
                        System.out.println("--- REGISTRO DE NUEVO CLIENTE (Paso " + paso + " de 6) ---");
                        System.out.println("Ingresa tu apellido ('volver' para reingresar nombre / 'cancelar' para salir):");
                        apellido = scanner.nextLine().trim();
                        if (apellido.equalsIgnoreCase("cancelar")) return false;
                        if (apellido.equalsIgnoreCase("volver")) {
                            paso--;
                            break; // Rompe el switch, el while continua y limpia la consola
                        }
                        if (apellido.isEmpty()) {
                            System.out.println("⚠️ El apellido no puede estar vacío.");
                            System.out.println("Presiona Enter para continuar...");
                            scanner.nextLine();
                        } else if (!esTextoValido(apellido)) {
                            System.out.println("⚠️ El apellido no puede contener números ni caracteres especiales.");
                            System.out.println("Presiona Enter para continuar...");
                            scanner.nextLine();
                        } else {
                            paso++;
                        }
                        break;

                    case 3:
                        System.out.println("--- REGISTRO DE NUEVO CLIENTE (Paso " + paso + " de 6) ---");
                        System.out.println("Ingresa tu DNI (7 u 8 dígitos) ('volver' para reingresar apellido / 'cancelar' para salir):");
                        String dniInput = scanner.nextLine().trim();
                        if (dniInput.equalsIgnoreCase("cancelar")) return false;
                        if (dniInput.equalsIgnoreCase("volver")) {
                            paso--;
                            break;
                        }
                        try {
                            dni = Integer.parseInt(dniInput);
                            if (!esDniValido(dni)) {
                                System.out.println("⚠️ DNI no válido. Debe tener 7 u 8 dígitos.");
                                System.out.println("Presiona Enter para continuar...");
                                scanner.nextLine();
                            } else if (clienteDAO.existenciaClientePorDni(dni)) {
                                System.out.println("⚠️ El DNI ya se encuentra registrado. Por favor, inicia sesión si ya eres cliente.");
                                System.out.println("Presiona Enter para continuar...");
                                scanner.nextLine();
                            } else {
                                paso++;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("⚠️ El DNI debe ser un número.");
                            System.out.println("Presiona Enter para continuar...");
                            scanner.nextLine();
                        }
                        break;

                    case 4:
                        System.out.println("--- REGISTRO DE NUEVO CLIENTE (Paso " + paso + " de 6) ---");
                        System.out.println("Ingresa tu dirección ('volver' para reingresar DNI / 'cancelar' para salir):");
                        direccion = scanner.nextLine().trim();
                        if (direccion.equalsIgnoreCase("cancelar")) return false;
                        if (direccion.equalsIgnoreCase("volver")) {
                            paso--;
                            break;
                        }
                        if (direccion.isEmpty()) {
                            System.out.println("⚠️ La dirección no puede estar vacía.");
                            System.out.println("Presiona Enter para continuar...");
                            scanner.nextLine();
                        } else {
                            paso++;
                        }
                        break;

                    case 5:
                        System.out.println("--- REGISTRO DE NUEVO CLIENTE (Paso " + paso + " de 6) ---");
                        System.out.println("Ingresa tu teléfono ('volver' para reingresar dirección / 'cancelar' para salir):");
                        telefono = scanner.nextLine().trim();
                        if (telefono.equalsIgnoreCase("cancelar")) return false;
                        if (telefono.equalsIgnoreCase("volver")) {
                            paso--;
                            break;
                        }
                        if (telefono.isEmpty()) {
                            System.out.println("⚠️ El teléfono no puede estar vacío.");
                            System.out.println("Presiona Enter para continuar...");
                            scanner.nextLine();
                        } else {
                            paso++;
                        }
                        break;

                    case 6:
                        System.out.println("--- REGISTRO DE NUEVO CLIENTE (Paso " + paso + " de 6) ---");
                        System.out.println("Ingresa tu correo electrónico ('volver' para reingresar teléfono / 'cancelar' para salir):");
                        correoElectronico = scanner.nextLine().trim();
                        if (correoElectronico.equalsIgnoreCase("cancelar")) return false;
                        if (correoElectronico.equalsIgnoreCase("volver")) {
                            paso--;
                            break;
                        }
                        // Validación de formato de correo más robusta
                        if (!Pattern.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", correoElectronico)) {
                            System.out.println("⚠️ El formato del correo electrónico no es válido. Ej: 'usuario@dominio.com'.");
                            System.out.println("Presiona Enter para continuar...");
                            scanner.nextLine();
                        } else {
                            paso++;
                        }
                        break;
                }
            }

            // Si se completaron todos los pasos y paso es 7, se procede al registro
            Cliente nuevoCliente = new Cliente(nombre, apellido, dni, direccion, telefono, correoElectronico);
            clienteDAO.registrarCliente(nuevoCliente);
            if (clienteDAO.existenciaClientePorDni(nuevoCliente.getDni())) {
                System.out.println("✅ Cliente registrado exitosamente. ¡Bienvenido!");
                System.out.println("Presiona Enter para continuar...");
                scanner.nextLine();
                return true;
            } else {
                System.out.println("❌ Error al registrar el cliente en la base de datos.");
                System.out.println("Presiona Enter para continuar...");
                scanner.nextLine();
                return false;
            }

        } catch (Exception e) {
            System.out.println("❌ Ocurrió un error inesperado durante el registro: " + e.getMessage());
            e.printStackTrace(); // Para depuración, puedes quitarlo en producción
            System.out.println("Presiona Enter para continuar...");
            scanner.nextLine();
            return false;
        }
    }

    private static boolean esCancelar(String input) {
        return input.equalsIgnoreCase("cancelar") || input.equalsIgnoreCase("salir");
    }

    private static boolean esVolver(String input) {
        return input.equalsIgnoreCase("volver");
    }

}
