package org.example;

import java.sql.*;
import java.lang.Object;
import java.lang.Throwable;
import java.lang.Exception;
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
            dbConnection.closeConnection();
            return true;

        } catch (SQLException e) {
            System.err.println("❌ Error al insertar cuota: " + e.getMessage());
            dbConnection.closeConnection();
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

            dbConnection.closeConnection();

        } catch (SQLException e) {
            System.err.println("❌ Error al listar cuotas: " + e.getMessage());
            dbConnection.closeConnection();
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

            dbConnection.closeConnection();

        } catch (SQLException e) {
            System.err.println("❌ Error al listar cuotas: " + e.getMessage());
            dbConnection.closeConnection();
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

            dbConnection.closeConnection();

        } catch (SQLException e) {
            System.err.println("❌ Error al listar cuotas: " + e.getMessage());
            dbConnection.closeConnection();
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
            dbConnection.closeConnection();
            return filasActualizadas > 0;

        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar estado de cuota: " + e.getMessage());
            dbConnection.closeConnection();
            return false;
        }
    }

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

    public List<Cuota> generarCuotas(Prestamo prestamoinst){
       /*
        // 1. Validar si ya existen cuotas
        if (existenCuotasPorIdPrestamo(idPrestamo)) {
            System.out.println("⚠️ Ya existen cuotas para el préstamo con id: " + idPrestamo);
            return Collections.emptyList();
        }

        // 2. Obtener datos del préstamo
        Map<String, Object> datosPrestamo = obtenerDatosPrestamo(idPrestamo);
        if (datosPrestamo.isEmpty()) {
            System.err.println("❌ No se encontró el préstamo con id " + idPrestamo);
            return Collections.emptyList();
        }
        */

        // 3. Extraer datos
        double monto = prestamoinst.getMonto();
        double tasaAnual = prestamoinst.getTasaInteres();
        int cantidadCuotas = prestamoinst.getCantidadCuotas();

        // 4. Calcular cuota fija
        double tasaMensual = (tasaAnual / 100.0) / 12.0;
        double cuotaFija = (monto * tasaMensual) / (1 - Math.pow(1 + tasaMensual, -cantidadCuotas));
        double saldoPendiente = monto;

        List<Cuota> cuotasGeneradas = new ArrayList<>();

        // 5. Generar cuotas
        for (int nroCuota = 1; nroCuota <= cantidadCuotas; nroCuota++) {
            double interes = saldoPendiente * tasaMensual;
            double amortizacion = cuotaFija - interes;
            double iva = interes * 0.21;
            double totalCuota = amortizacion + interes + iva;

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MONTH, nroCuota);
            java.sql.Date fechaVencimiento = new java.sql.Date(calendar.getTimeInMillis());

            Cuota cuota = new Cuota();
            cuota.setIdPrestamo(0);
            cuota.setNumeroCuota(nroCuota);
            cuota.setFechaVencimiento(fechaVencimiento);
            cuota.setAmortizacion(round(amortizacion));
            cuota.setInteres(round(interes));
            cuota.setIva(round(iva));
            cuota.setTotalCuota(round(totalCuota));
            cuota.setEstado("pendiente");

            cuotasGeneradas.add(cuota);

            saldoPendiente -= amortizacion;
        }

        //System.out.println("✅ Cuotas generadas correctamente (sin insertar en base de datos).");
        return cuotasGeneradas;
    }


    public boolean insertarCuotas(List<Cuota> cuotas, int idPrestamo)  {
        PrestamoDAO prestamoDAO = new PrestamoDAO();
        if(!prestamoDAO.existenciaPrestamoPorId(idPrestamo)){
            System.out.println("⚠️ No existe un prestamo con id "+ idPrestamo);
            return false;
        }


        if (cuotas == null || cuotas.isEmpty()) {
            System.out.println("⚠️ No hay cuotas para insertar.");
            return false;
        }

        if (existenCuotasPorIdPrestamo(idPrestamo)) {
            System.out.println("⚠️ Ya existen cuotas para el préstamo con id: " + idPrestamo);
            return false;
        }

        for (Cuota cuota : cuotas) {
            if (cuota.getIdPrestamo() == 0) {
                cuota.setIdPrestamo(idPrestamo);
            }
        }
        for (Cuota cuota : cuotas) {
            this.insertar(cuota); // Ya tenés este método
        }
        //System.out.println("✅ Cuotas insertadas exitosamente en la base de datos.");
        return true;
    }

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

