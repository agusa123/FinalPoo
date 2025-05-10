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
}

