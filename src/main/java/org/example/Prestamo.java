package org.example;

public class Prestamo {
    private int idPrestamo;
    private int idCliente;
    private double monto;
    private double tasaInteres;
    private int cantidadCuotas;
    private String tipoPrestamo;
    private double saldoPendiente;
    private String estado;

    // Constructor completo
    public Prestamo(int idPrestamo, int idCliente, double monto, int cantidadCuotas, String tipoPrestamo, double saldoPendiente, String estado) {
        this.idPrestamo = idPrestamo;
        this.idCliente = idCliente;
        this.monto = monto;
        this.cantidadCuotas = cantidadCuotas;
        this.tasaInteres = calcularTasaDeInteres(cantidadCuotas);
        this.tipoPrestamo = tipoPrestamo;
        this.saldoPendiente = saldoPendiente;
        this.estado = estado;
    }

    // Constructor sin ID (para cuando lo genera la base de datos)
    public Prestamo(int idCliente, double monto, int cantidadCuotas, String tipoPrestamo, double saldoPendiente, String estado) {
        this.idCliente = idCliente;
        this.monto = monto;
        this.cantidadCuotas = cantidadCuotas;
        this.tasaInteres = calcularTasaDeInteres(cantidadCuotas);
        this.tipoPrestamo = tipoPrestamo;
        this.saldoPendiente = saldoPendiente;
        this.estado = estado;
    }

    public static double calcularTasaDeInteres(int cantidadCuotas) {
        if (cantidadCuotas <= 12) {
            return 63.0; // 63%
        } else if (cantidadCuotas > 12) {
            return 70.0; // 70%
        } else if (cantidadCuotas >= 24) {
            return 75.0; // 75%
        } else {
            return 83.0; // 83% para más de 24 cuotas
        }
    }

    // Getters y Setters
    public int getIdPrestamo() { return idPrestamo; }
    public void setIdPrestamo(int idPrestamo) { this.idPrestamo = idPrestamo; }

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public double getMonto() { return monto; }
    public void setMonto(double monto) { this.monto = monto; }

    public double getTasaInteres() { return tasaInteres; }
    public void setTasaInteres(double tasaInteres) { this.tasaInteres = tasaInteres; }

    public int getCantidadCuotas() { return cantidadCuotas; }
    public void setCantidadCuotas(int cantidadCuotas) { this.cantidadCuotas = cantidadCuotas; }

    public String getTipoPrestamo() { return tipoPrestamo; }
    public void setTipoPrestamo(String tipoPrestamo) { this.tipoPrestamo = tipoPrestamo; }

    public double getSaldoPendiente() { return saldoPendiente; }
    public void setSaldoPendiente(double saldoPendiente) { this.saldoPendiente = saldoPendiente; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}