package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PagoDAO {
    private final DatabaseConnection dbConnection;

    public PagoDAO() {
        this.dbConnection = DatabaseConnection.getInstance();
    }

    public boolean pagar(Pago pago) {
        String sql = "INSERT INTO Pagos (idCuota, fechaPago, montoPagado, metodoPago) VALUES (?, ?, ?, ?)";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, pago.getIdCuota());
            stmt.setDate(2, pago.getFechaPago());
            stmt.setDouble(3, pago.getMontoPagado());
            stmt.setString(4, pago.getMetodoPago());

            stmt.executeUpdate();
            dbConnection.closeConnection();
            return true;

        } catch (SQLException e) {
            System.err.println("❌ Error al insertar el pago: " + e.getMessage());
            dbConnection.closeConnection();
            return false;
        }
    }

    public List<Pago> obtenerPagosPorIdPrestamo(int idPrestamo) {
        List<Pago> pagos = new ArrayList<>();
        String sql = """
                    SELECT p.idPago, p.idCuota, p.fechaPago, p.montoPagado, p.metodoPago
                    FROM Pagos p
                    JOIN Cuotas c ON p.idCuota = c.idCuota
                    WHERE c.idPrestamo = ?
                """;

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPrestamo);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Pago pago = new Pago(
                            rs.getInt("idPago"),
                            rs.getInt("idCuota"),
                            rs.getDate("fechaPago"),
                            rs.getDouble("montoPagado"),
                            rs.getString("metodoPago")
                    );

                    pagos.add(pago);
                }
            }

            dbConnection.closeConnection();

        } catch (SQLException e) {
            System.err.println("❌ Error al obtener pagos del préstamo ID " + idPrestamo + ": " + e.getMessage());
            dbConnection.closeConnection();
        }

        return pagos;
    }

    public Pago obtenerPagoPorId(int idPago) {
        String sql = "SELECT * FROM Pagos WHERE idPago = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPago);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    dbConnection.closeConnection();
                    return new Pago(
                            rs.getInt("idPago"),
                            rs.getInt("idCuota"),
                            rs.getDate("fechaPago"),
                            rs.getDouble("montoPagado"),
                            rs.getString("metodoPago")
                    );

                }
            }

            dbConnection.closeConnection();

        } catch (SQLException e) {
            System.err.println("❌ Error al obtener el pago ID " + idPago + ": " + e.getMessage());
            dbConnection.closeConnection();
        }

        return null;
    }

    public Pago obtenerPagoPorIdCuota(int idCuota) {
        String sql = "SELECT * FROM Pagos WHERE idCuota = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCuota);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {

                    return new Pago(
                            rs.getInt("idPago"),
                            rs.getInt("idCuota"),
                            rs.getDate("fechaPago"),
                            rs.getDouble("montoPagado"),
                            rs.getString("metodoPago")
                    );
                }
            }
            dbConnection.closeConnection();


        } catch (SQLException e) {
            System.err.println("❌ Error al obtener el pago por ID de cuota " + idCuota + ": " + e.getMessage());
            dbConnection.closeConnection();

        }

        return null;
    }

}

