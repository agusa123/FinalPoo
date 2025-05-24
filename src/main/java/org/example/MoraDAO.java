package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MoraDAO {
    private final DatabaseConnection dbConnection;

    public MoraDAO() {
        this.dbConnection = DatabaseConnection.getInstance();
    }

    //verificar si una cuota entra en mora
    public boolean verificarMora(Cuota cuota) {
        Date hoy = new Date();

        if (cuota.getFechaVencimiento().before(hoy) && !cuota.getEstado().equalsIgnoreCase("pagada")) {
            dbConnection.closeConnection();
            return true; // La cuota está en mora
        }

        return false; // La cuota no está en mora
    }

    //registrar cuota en mora
    private boolean registrarMora(Mora mora) {
        String sql = "INSERT INTO Mora (idCuota, montoPenalidad, fechaRegistro) VALUES (?, ?, ?)";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, mora.getIdCuota());
            stmt.setDouble(2, mora.getMontoPenalidad());
            stmt.setDate(3, new java.sql.Date(System.currentTimeMillis()));

            int filasInsertadas = stmt.executeUpdate(); // Ejecutar la consulta
            dbConnection.closeConnection();
            return filasInsertadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            dbConnection.closeConnection();
            return false;
        }
    }

    public double calcularPenalidad(Cuota cuota) {

        return cuota.getInteres() / 2;
    }

    // para una lista de cuotas
    public void registrarMorasYActualizarPrestamo(List<Cuota> cuotas) {
        PrestamoDAO prestamoDAO = new PrestamoDAO();
        for (Cuota cuota : cuotas) {
            if (verificarMora(cuota)) {
                // Calcular el monto de la penalidad (mitad de los intereses de la cuota)
                double montoPenalidad = calcularPenalidad(cuota);

                Mora mora = new Mora(cuota.getIdCuota(), montoPenalidad, System.currentTimeMillis());
                boolean registrada = registrarMora(mora);

                if (registrada) {
                    boolean saldoActualizado = prestamoDAO.actualizarSaldoPendiente(cuota.getIdPrestamo(), montoPenalidad);

                    if (saldoActualizado) {
                        System.out.println("Mora registrada y saldo actualizado para el préstamo ID: " + cuota.getIdPrestamo());
                    } else {
                        System.err.println("Error al actualizar el saldo del préstamo ID: " + cuota.getIdPrestamo());
                    }
                } else {
                    System.err.println("Error al registrar la mora para la cuota con ID: " + cuota.getIdCuota());
                }
            }
        }

    }

    //para una sola cuota
    public void registrarMoraYActualizarPrestamo(Cuota cuota) {
        PrestamoDAO prestamoDAO = new PrestamoDAO();

        if (verificarMora(cuota)) {
            // Calcular el monto de la penalidad (mitad de los intereses de la cuota)
            double montoPenalidad = calcularPenalidad(cuota);

            Mora mora = new Mora(cuota.getIdCuota(), montoPenalidad, System.currentTimeMillis());
            boolean registrada = registrarMora(mora);

            if (registrada) {
                boolean saldoActualizado = prestamoDAO.actualizarSaldoPendiente(cuota.getIdPrestamo(), montoPenalidad);

                if (saldoActualizado) {
                    System.out.println("Mora registrada y saldo actualizado para el préstamo ID: " + cuota.getIdPrestamo());
                } else {
                    System.err.println("Error al actualizar el saldo del préstamo ID: " + cuota.getIdPrestamo());
                }
            } else {
                System.err.println("Error al registrar la mora para la cuota con ID: " + cuota.getIdCuota());
            }
        } else {
            System.out.println("La cuota con ID " + cuota.getIdCuota() + " no está en mora.");
        }

    }
}
