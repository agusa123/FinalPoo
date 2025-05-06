package org.example;
import java.util.Date;

public class Cuota {
    private int idCuota;
    private int idPrestamo;
    private int numeroCuota;
    private Date fechaVencimiento;
    private double amortizacion;
    private double interes;
    private double iva;
    private double totalCuota;
    private String estado; // 'pendiente', 'pagada', 'vencida'

    // Constructor vacío
    public Cuota() {}

    // Constructor completo
    public Cuota(int idCuota, int idPrestamo, int numeroCuota, Date fechaVencimiento,
                 double amortizacion, double interes, double iva, double totalCuota, String estado) {
        this.idCuota = idCuota;
        this.idPrestamo = idPrestamo;
        this.numeroCuota = numeroCuota;
        this.fechaVencimiento = fechaVencimiento;
        this.amortizacion = amortizacion;
        this.interes = interes;
        this.iva = iva;
        this.totalCuota = totalCuota;
        this.estado = estado;
    }

    // Getters y Setters
    public int getIdCuota() {
        return idCuota;
    }

    public void setIdCuota(int idCuota) {
        this.idCuota = idCuota;
    }

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public int getNumeroCuota() {
        return numeroCuota;
    }

    public void setNumeroCuota(int numeroCuota) {
        this.numeroCuota = numeroCuota;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public double getAmortizacion() {
        return amortizacion;
    }

    public void setAmortizacion(double amortizacion) {
        this.amortizacion = amortizacion;
    }

    public double getInteres() {
        return interes;
    }

    public void setInteres(double interes) {
        this.interes = interes;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getTotalCuota() {
        return totalCuota;
    }

    public void setTotalCuota(double totalCuota) {
        this.totalCuota = totalCuota;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}