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
        if (existenciaClientePorDni(cliente.getDni())) {
            System.out.println("Ya existe un cliente con el DNI: " + cliente.getDni());
            return;
        }

        String sql = "INSERT INTO Clientes (nombre, apellido, dni, direccion, telefono, correoElectronico) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getApellido());
            stmt.setInt(3, cliente.getDni());
            stmt.setString(4, cliente.getDireccion());
            stmt.setString(5, cliente.getTelefono());
            stmt.setString(6, cliente.getCorreoElectronico());

            int filasInsertadas = stmt.executeUpdate();
            if (filasInsertadas > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
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
        String sql = "UPDATE Clientes SET nombre = ?, apellido = ?, direccion = ?, telefono = ?, correoElectronico = ? WHERE idCliente = ?";

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
        String sql = "UPDATE Clientes SET " + campo + " = ? WHERE idCliente = ?";

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
        String sql = "SELECT * FROM Clientes WHERE idCliente = ?";
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
                        rs.getInt("dni"),
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

    // Este metodo sirve para verificar si un cliente existe por dni. En caso negativo, devuelve null
    public Cliente obtenerClientePorDni(int dni) {
        String sql = "SELECT * FROM Clientes WHERE dni = ?";
        Cliente cliente = null;

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, dni);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                cliente = new Cliente(
                        rs.getInt("idCliente"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getInt("dni"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getString("correoElectronico")
                );
            }else {
                return null;
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println("❌ Error al obtener cliente por ID: " + e.getMessage());
        }

        return cliente;
    }
    public List<Cliente> listarClientes() {
        String sql = "SELECT * FROM Clientes";
        List<Cliente> listaClientes = new ArrayList<>();

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getInt("idCliente"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getInt("dni"),
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

    public boolean existenciaClientePorDni(int dni) {
        String sql = "SELECT COUNT(*) FROM Clientes WHERE dni = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, dni);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int cantidad = rs.getInt(1);
                    return cantidad > 0; // true si ya existe un cliente con ese DNI
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al verificar existencia del cliente: " + e.getMessage());
        }

        return false;
    }

}
