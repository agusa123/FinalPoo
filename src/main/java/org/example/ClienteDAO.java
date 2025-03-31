package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class ClienteDAO {
    private final DatabaseConnection dbConnection;

    public ClienteDAO() {
        this.dbConnection = DatabaseConnection.getInstance();
    }

    public void registrarCliente(Cliente cliente) {
        String sql = "INSERT INTO Clientes (nombre, apellido, direccion, telefono, correoElectronico) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) { // Permite obtener el ID generado

            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getApellido());
            stmt.setString(3, cliente.getDireccion());
            stmt.setString(4, cliente.getTelefono());
            stmt.setString(5, cliente.getCorreoElectronico());

            int filasInsertadas = stmt.executeUpdate();
            if (filasInsertadas > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) { // Obtener el ID generado
                    if (generatedKeys.next()) {
                        int idGenerado = generatedKeys.getInt(1);
                        System.out.println("Cliente registrado correctamente con ID: " + idGenerado);
                    }
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al registrar cliente: " + e.getMessage());
        }
    }

    public boolean editarCliente(int idCliente, String nombre, String apellido, String direccion, String telefono, String correoElectronico) {
        String sql = "UPDATE clientes SET nombre = ?, apellido = ?, direccion = ?, telefono = ?, correoElectronico = ? WHERE idCliente = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            stmt.setString(2, apellido);
            stmt.setString(3, direccion);
            stmt.setString(4, telefono);
            stmt.setString(5, correoElectronico);
            stmt.setInt(6, idCliente);

            int filasActualizadas = stmt.executeUpdate();
            return filasActualizadas > 0; // Devuelve `true` si al menos una fila fue actualizada
        } catch (SQLException e) {
            System.err.println("Error al actualizar el cliente: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    public boolean editarCampoEspecifico(int idCliente, String campo, String nuevoValor) {
        String sql = "UPDATE clientes SET " + campo + " = ? WHERE idCliente = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nuevoValor);
            stmt.setInt(2, idCliente);
            int filasActualizadas = stmt.executeUpdate();
            return filasActualizadas > 0;

        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar " + campo + ": " + e.getMessage());
            return false;
        }
    }
    // Este metodo sirve para verificar si un cliente existe. En caso negativo, devuelve null
    public Cliente obtenerClientePorId(int idCliente) {
        String sql = "SELECT * FROM clientes WHERE idCliente = ?";
        Cliente cliente = null;

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCliente);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                cliente = new Cliente(
                        rs.getInt("idCliente"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getString("correoElectronico")
                );
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println("❌ Error al obtener cliente por ID: " + e.getMessage());
        }

        return cliente;
    }
    public List<Cliente> listarClientes() {
        String sql = "SELECT * FROM clientes";
        List<Cliente> listaClientes = new ArrayList<>();

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getInt("idCliente"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getString("correoElectronico")
                );
                listaClientes.add(cliente);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al listar clientes: " + e.getMessage());
        }

        return listaClientes;
    }

}
