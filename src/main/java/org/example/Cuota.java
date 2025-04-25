package org.example;

public class Cuota {
    private double total;
    private double interes;
    private double iva;
    private double amortizacion;

    public Cuota(double total, double interes, double iva, double amortizacion) {
        this.total = total;
        this.interes = interes;
        this.iva = iva;
        this.amortizacion = amortizacion;
    }

    public void mostrar() {
        System.out.printf("💵 Cuota total: $%.2f\n", total);
        System.out.printf("📈 Interés: $%.2f\n", interes);
        System.out.printf("🧾 IVA (21%%): $%.2f\n", iva);
        System.out.printf("🏦 Amortización: $%.2f\n", amortizacion);
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
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

    public double getAmortizacion() {
        return amortizacion;
    }

    public void setAmortizacion(double amortizacion) {
        this.amortizacion = amortizacion;
    }
}