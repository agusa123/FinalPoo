package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PrestamoDAO {
    private final DatabaseConnection dbConnection;
    private ClienteDAO clienteDAO;

    public PrestamoDAO() {
        this.dbConnection = DatabaseConnection.getInstance();
        this.clienteDAO = new ClienteDAO();
    }

    public boolean crearPrestamo(Prestamo prestamo) {
        // 1. Verificar si el cliente existe

        Cliente cliente = clienteDAO.obtenerClientePorId(prestamo.getIdCliente());
        if (cliente == null) {
            System.err.println("❌ No se puede crear el préstamo: el cliente no existe.");
            return false;
        }

        // 2. Insertar el préstamo
        String sql = "INSERT INTO Prestamos (idCliente, monto, tasaInteres, cantidadCuotas, tipoPrestamo, saldoPendiente) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, prestamo.getIdCliente());
            stmt.setDouble(2, prestamo.getMonto());
            stmt.setDouble(3, prestamo.getTasaInteres());
            stmt.setInt(4, prestamo.getCantidadCuotas());
            stmt.setString(5, prestamo.getTipoPrestamo());
            stmt.setDouble(6, prestamo.getSaldoPendiente());

            int filas = stmt.executeUpdate();
            if (filas > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    int idGenerado = rs.getInt(1);
                    prestamo.setIdPrestamo(idGenerado); // Opcional, si querés guardar el ID en memoria
                    System.out.println("✅ Préstamo creado con ID: " + idGenerado);
                }
                rs.close();
                return true;
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al crear el préstamo: " + e.getMessage());
        }

        return false;
    }

    public Prestamo obtenerPrestamoPorId(int idPrestamo) {
        String sql = "SELECT * FROM Prestamos WHERE idPrestamo = ?";
        Prestamo prestamo = null;

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPrestamo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                prestamo = new Prestamo(
                        rs.getInt("idPrestamo"),
                        rs.getInt("idCliente"),
                        rs.getDouble("monto"),
                        rs.getInt("cantidadCuotas"),
                        rs.getString("tipoPrestamo"),
                        rs.getDouble("saldoPendiente")
                );
            }

            rs.close();
        } catch (SQLException e) {
            System.err.println("❌ Error al obtener préstamo por ID: " + e.getMessage());
        }

        return prestamo;
    }

    public List<Prestamo> obtenerPrestamosPorCliente(int idCliente) {
        String sql = "SELECT * FROM Prestamos WHERE idCliente = ?";
        List<Prestamo> prestamos = new ArrayList<>();

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCliente);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Prestamo prestamo = new Prestamo(
                        rs.getInt("idPrestamo"),
                        rs.getInt("idCliente"),
                        rs.getDouble("monto"),
                        rs.getInt("cantidadCuotas"),
                        rs.getString("tipoPrestamo"),
                        rs.getDouble("saldoPendiente")
                );
                prestamos.add(prestamo);
            }

            rs.close();
        } catch (SQLException e) {
            System.err.println("❌ Error al obtener préstamos del cliente: " + e.getMessage());
        }

        return prestamos;
    }

    public boolean actualizarSaldo(int idPrestamo, double nuevoSaldo) {
        String sql = "UPDATE Prestamos SET saldoPendiente = ? WHERE idPrestamo = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, nuevoSaldo);
            stmt.setInt(2, idPrestamo);

            int filasActualizadas = stmt.executeUpdate();
            return filasActualizadas > 0;

        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar el saldo: " + e.getMessage());
            return false;
        }
    }

    public List<Prestamo> listarTodosLosPrestamos() {
        String sql = "SELECT * FROM Prestamos";
        List<Prestamo> prestamos = new ArrayList<>();

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Prestamo prestamo = new Prestamo(
                        rs.getInt("idPrestamo"),
                        rs.getInt("idCliente"),
                        rs.getDouble("monto"),
                        rs.getInt("cantidadCuotas"),
                        rs.getString("tipoPrestamo"),
                        rs.getDouble("saldoPendiente")
                );
                prestamos.add(prestamo);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al listar los préstamos: " + e.getMessage());
        }

        return prestamos;
    }

}


