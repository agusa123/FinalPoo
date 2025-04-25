package org.example;
import java.sql.*;

public class CalculadoraDeCuotas {
    private double monto;
    private double tasaAnual;
    private double tasaMensual;
    private int cantidadCuotas;
    private double cuota;
    private int idCliente;
    private String tipoPrestamo;
    private double saldoPrestamo;
    private final DatabaseConnection dbConnection;

    public CalculadoraDeCuotas() {
        this.dbConnection = DatabaseConnection.getInstance();
    }
    private boolean cargarDatosPrestamo(int idPrestamo) {
        String sql = "SELECT idCliente, monto, tasaInteres, cantidadCuotas, tipoPrestamo, saldoPendiente FROM Prestamos WHERE idPrestamo = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPrestamo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                this.idCliente = rs.getInt("idCliente");
                this.monto = rs.getDouble("monto");
                this.tasaAnual = rs.getDouble("tasaInteres");
                this.tasaMensual = (tasaAnual / 100.0) / 12.0;
                this.cantidadCuotas = rs.getInt("cantidadCuotas");
                this.tipoPrestamo = rs.getString("tipoPrestamo");
                this.saldoPrestamo = rs.getDouble("saldoPendiente");
                return true;
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al cargar datos del préstamo: " + e.getMessage());
        }

        return false;
    }
    public Cuota calcularCuota(int idPrestamo) {
        if (!cargarDatosPrestamo(idPrestamo)) {
            System.err.println("❌ No se pudieron cargar los datos del préstamo.");
            return null;
        }

        double cuota = monto * (tasaMensual * Math.pow(1 + tasaMensual, cantidadCuotas)) /
                (Math.pow(1 + tasaMensual, cantidadCuotas) - 1);

        double interesMensual = saldoPrestamo * tasaMensual;
        double iva = interesMensual * 0.21;
        double amortizacion = cuota - interesMensual - iva;

        return new Cuota(cuota, interesMensual, iva, amortizacion);
    }

}
