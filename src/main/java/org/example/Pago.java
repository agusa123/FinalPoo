package org.example;
import java.sql.Date;

public class Pago {
    private int idPago;
    private int idCuota;
    private Date fechaPago;
    private double montoPagado;
    private String metodoPago;


    // Constructor
    public Pago(int idPago, int idCuota, Date fechaPago, double montoPagado, String metodoPago) {
        this.idPago = idPago;
        this.idCuota = idCuota;
        this.fechaPago = fechaPago;
        this.montoPagado = montoPagado;
        this.metodoPago = metodoPago;
    }
    public Pago( int idCuota, Date fechaPago, double montoPagado, String metodoPago) {
        this.idPago = idPago;
        this.idCuota = idCuota;
        this.fechaPago = fechaPago;
        this.montoPagado = montoPagado;
        this.metodoPago = metodoPago;
    }

    // Getters y Setters
    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public int getIdCuota() {
        return idCuota;
    }

    public void setIdCuota(int idCuota) {
        this.idCuota = idCuota;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public double getMontoPagado() {
        return montoPagado;
    }

    public void setMontoPagado(double montoPagado) {
        this.montoPagado = montoPagado;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }
}
