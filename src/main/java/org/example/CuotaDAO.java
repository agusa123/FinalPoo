package org.example;


import java.sql.*;

import java.util.*;

import static java.lang.Math.round;

public class CuotaDAO {
    private final DatabaseConnection dbConnection;

    public CuotaDAO() {
        this.dbConnection = DatabaseConnection.getInstance();
    }

    // Insertar una nueva cuota
    public boolean insertar(Cuota cuota) {
        String sql = "INSERT INTO Cuotas (idPrestamo, numeroCuota, fechaVencimiento, amortizacion, interes, iva, totalCuota, estado) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, cuota.getIdPrestamo());
            stmt.setInt(2, cuota.getNumeroCuota());
            stmt.setDate(3, new java.sql.Date(cuota.getFechaVencimiento().getTime()));
            stmt.setDouble(4, cuota.getAmortizacion());
            stmt.setDouble(5, cuota.getInteres());
            stmt.setDouble(6, cuota.getIva());
            stmt.setDouble(7, cuota.getTotalCuota());
            stmt.setString(8, cuota.getEstado());

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("❌ Error al insertar cuota: " + e.getMessage());
            return false;
        }
    }

    // Listar todas las cuotas de un préstamo
    public List<Cuota> listarCuotasPorPrestamo(int idPrestamo) {
        List<Cuota> cuotas = new ArrayList<>();
        String sql = "SELECT * FROM Cuotas WHERE idPrestamo = ? ORDER BY numeroCuota";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPrestamo);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Cuota cuota = new Cuota();
                cuota.setIdCuota(rs.getInt("idCuota"));
                cuota.setIdPrestamo(rs.getInt("idPrestamo"));
                cuota.setNumeroCuota(rs.getInt("numeroCuota"));
                cuota.setFechaVencimiento(rs.getDate("fechaVencimiento"));
                cuota.setAmortizacion(rs.getDouble("amortizacion"));
                cuota.setInteres(rs.getDouble("interes"));
                cuota.setIva(rs.getDouble("iva"));
                cuota.setTotalCuota(rs.getDouble("totalCuota"));
                cuota.setEstado(rs.getString("estado"));
                cuotas.add(cuota);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al listar cuotas: " + e.getMessage());
        }

        return cuotas;
    }

    // Listar todas las cuotas inpagas de un prestamo
    public List<Cuota> listarCuotasImpagasPorPrestamo(int idPrestamo) {
        List<Cuota> cuotas = new ArrayList<>();
        String sql = "SELECT * FROM Cuotas WHERE idPrestamo = ? AND estado = 'pendiente' ORDER BY numeroCuota";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPrestamo);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Cuota cuota = new Cuota();
                cuota.setIdCuota(rs.getInt("idCuota"));
                cuota.setIdPrestamo(rs.getInt("idPrestamo"));
                cuota.setNumeroCuota(rs.getInt("numeroCuota"));
                cuota.setFechaVencimiento(rs.getDate("fechaVencimiento"));
                cuota.setAmortizacion(rs.getDouble("amortizacion"));
                cuota.setInteres(rs.getDouble("interes"));
                cuota.setIva(rs.getDouble("iva"));
                cuota.setTotalCuota(rs.getDouble("totalCuota"));
                cuota.setEstado(rs.getString("estado"));
                cuotas.add(cuota);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al listar cuotas: " + e.getMessage());
        }

        return cuotas;
    }

    // Listar todas las cuotas pagas de un préstamo
    public List<Cuota> listarCuotasPagasPorPrestamo(int idPrestamo) {
        List<Cuota> cuotas = new ArrayList<>();
        String sql = "SELECT * FROM Cuotas WHERE idPrestamo = ? AND estado = 'pagada' ORDER BY numeroCuota";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPrestamo);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Cuota cuota = new Cuota();
                cuota.setIdCuota(rs.getInt("idCuota"));
                cuota.setIdPrestamo(rs.getInt("idPrestamo"));
                cuota.setNumeroCuota(rs.getInt("numeroCuota"));
                cuota.setFechaVencimiento(rs.getDate("fechaVencimiento"));
                cuota.setAmortizacion(rs.getDouble("amortizacion"));
                cuota.setInteres(rs.getDouble("interes"));
                cuota.setIva(rs.getDouble("iva"));
                cuota.setTotalCuota(rs.getDouble("totalCuota"));
                cuota.setEstado(rs.getString("estado"));
                cuotas.add(cuota);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al listar cuotas: " + e.getMessage());
        }

        return cuotas;
    }

    // Actualizar el estado de una cuota
    public boolean actualizarEstado(int idCuota, String nuevoEstado) {
        String sql = "UPDATE Cuotas SET estado = ? WHERE idCuota = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nuevoEstado);
            stmt.setInt(2, idCuota);

            int filasActualizadas = stmt.executeUpdate();
            return filasActualizadas > 0;

        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar estado de cuota: " + e.getMessage());
            return false;
        }
    }
    /*
    public boolean generarCuotas(int idPrestamo) {
        String sqlPrestamo = "SELECT monto, tasaInteres, cantidadCuotas FROM Prestamos WHERE idPrestamo = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmtPrestamo = conn.prepareStatement(sqlPrestamo)) {

            stmtPrestamo.setInt(1, idPrestamo);
            ResultSet rs = stmtPrestamo.executeQuery();

            if (!rs.next()) {
                System.err.println("❌ No se encontró el préstamo con id " + idPrestamo);
                return false;
            }else if (existenCuotasPorIdPrestamo(idPrestamo)) {
                System.out.println("Ya existen cuotas para el prestamo con id: "+idPrestamo);
                return false;
            }


            double monto = rs.getDouble("monto");
            double tasaAnual = rs.getDouble("tasaInteres");
            int cantidadCuotas = rs.getInt("cantidadCuotas");

            double tasaMensual = (tasaAnual / 100.0) / 12.0;
            double cuotaFija = (monto * tasaMensual) / (1 - Math.pow(1 + tasaMensual, -cantidadCuotas));
            double saldoPendiente = monto;

            for (int nroCuota = 1; nroCuota <= cantidadCuotas; nroCuota++) {
                double interes = saldoPendiente * tasaMensual;
                double amortizacion = cuotaFija - interes;
                double iva = interes * 0.21;
                double totalCuota = amortizacion + interes + iva;

                // Calcular fecha de vencimiento (1 mes después de la anterior)
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.MONTH, nroCuota);
                java.sql.Date fechaVencimiento = new java.sql.Date(calendar.getTimeInMillis());

                // Crear objeto Cuota
                Cuota cuota = new Cuota();
                cuota.setIdPrestamo(idPrestamo);
                cuota.setNumeroCuota(nroCuota);
                cuota.setFechaVencimiento(fechaVencimiento);
                cuota.setAmortizacion(round(amortizacion));
                cuota.setInteres(round(interes));
                cuota.setIva(round(iva));
                cuota.setTotalCuota(round(totalCuota));
                cuota.setEstado("pendiente");

                // Insertar cuota en la base de datos
                this.insertar(cuota);

                saldoPendiente -= amortizacion;
            }

            System.out.println("✅ Cuotas generadas e insertadas exitosamente.");
            return true;

        } catch (SQLException e) {
            System.err.println("❌ Error al generar cuotas: " + e.getMessage());
            return false;
        }
    }
    // Método para redondear a 2 decimales
    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
    */

    // Método que obtiene los datos del préstamo
    private Map<String, Object> obtenerDatosPrestamo(int idPrestamo) throws SQLException {
        String sql = "SELECT monto, tasaInteres, cantidadCuotas FROM Prestamos WHERE idPrestamo = ?";
        Map<String, Object> datosPrestamo = new HashMap<>();

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPrestamo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    datosPrestamo.put("monto", rs.getDouble("monto"));
                    datosPrestamo.put("tasaInteres", rs.getDouble("tasaInteres"));
                    datosPrestamo.put("cantidadCuotas", rs.getInt("cantidadCuotas"));
                }
            }
        }
        return datosPrestamo;
    }

    // Método que genera las cuotas
    public boolean generarCuotas(int idPrestamo) {
        try {
            // 1. Validar si ya existen cuotas para el préstamo
            if (existenCuotasPorIdPrestamo(idPrestamo)) {
                System.out.println("Ya existen cuotas para el préstamo con id: " + idPrestamo);
                return false;
            }

            // 2. Obtener los datos del préstamo
            Map<String, Object> datosPrestamo = obtenerDatosPrestamo(idPrestamo);

            if (datosPrestamo.isEmpty()) {
                System.err.println("❌ No se encontró el préstamo con id " + idPrestamo);
                return false;
            }

            // 3. Extraer los valores del préstamo
            double monto = (double) datosPrestamo.get("monto");
            double tasaAnual = (double) datosPrestamo.get("tasaInteres");
            int cantidadCuotas = (int) datosPrestamo.get("cantidadCuotas");

            // 4. Configurar los cálculos para las cuotas
            double tasaMensual = (tasaAnual / 100.0) / 12.0; // Convertir tasa anual a mensual
            double cuotaFija = (monto * tasaMensual) / (1 - Math.pow(1 + tasaMensual, -cantidadCuotas));
            double saldoPendiente = monto;

            // 5. Generar las cuotas
            for (int nroCuota = 1; nroCuota <= cantidadCuotas; nroCuota++) {
                double interes = saldoPendiente * tasaMensual;  // Cálculo de interés
                double amortizacion = cuotaFija - interes;      // Cálculo de amortización
                double iva = interes * 0.21;                   // IVA sobre el interés
                double totalCuota = amortizacion + interes + iva;

                // Calcular la fecha de vencimiento (1 mes después por cuota)
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.MONTH, nroCuota);
                java.sql.Date fechaVencimiento = new java.sql.Date(calendar.getTimeInMillis());

                // Crear el objeto Cuota y asignar valores
                Cuota cuota = new Cuota();
                cuota.setIdPrestamo(idPrestamo);
                cuota.setNumeroCuota(nroCuota);
                cuota.setFechaVencimiento(fechaVencimiento);
                cuota.setAmortizacion(round(amortizacion)); // Redondear amortización
                cuota.setInteres(round(interes));           // Redondear interés
                cuota.setIva(round(iva));                   // Redondear IVA
                cuota.setTotalCuota(round(totalCuota));     // Redondear total de la cuota
                cuota.setEstado("pendiente");               // Estado inicial

                // Insertar la cuota en la base de datos
                this.insertar(cuota);

                // Reducir el saldo pendiente
                saldoPendiente -= amortizacion;
            }

            // 6. Cuotas generadas con éxito
            System.out.println("✅ Cuotas generadas e insertadas exitosamente.");
            return true;

        } catch (SQLException e) {
            // Manejar cualquier error SQL
            System.err.println("❌ Error al generar cuotas: " + e.getMessage());
            return false;
        }
    }

    // Obtener una cuota por su idCuota
    public Cuota obtenerCuotaPorId(int idCuota) {
        String sql = "SELECT * FROM Cuotas WHERE idCuota = ?";
        Cuota cuota = null;

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCuota);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cuota = new Cuota();
                    cuota.setIdCuota(rs.getInt("idCuota"));
                    cuota.setIdPrestamo(rs.getInt("idPrestamo"));
                    cuota.setNumeroCuota(rs.getInt("numeroCuota"));
                    cuota.setFechaVencimiento(rs.getDate("fechaVencimiento"));
                    cuota.setAmortizacion(rs.getDouble("amortizacion"));
                    cuota.setInteres(rs.getDouble("interes"));
                    cuota.setIva(rs.getDouble("iva"));
                    cuota.setTotalCuota(rs.getDouble("totalCuota"));
                    cuota.setEstado(rs.getString("estado"));
                }
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al obtener la cuota con ID " + idCuota + ": " + e.getMessage());
        }

        return cuota;
    }

    //verificar si existen cuotas para un prestamo
    public boolean existenCuotasPorIdPrestamo(int idPrestamo) {
        String sql = "SELECT COUNT(*) as total FROM Cuotas WHERE idPrestamo = ?";
        boolean existenCuotas = false;

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPrestamo);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int total = rs.getInt("total");
                    existenCuotas = total > 0; // Si hay al menos una fila, devuelve true
                }
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al verificar cuotas para el ID de Préstamo " + idPrestamo + ": " + e.getMessage());
        }

        return existenCuotas;
    }

}

