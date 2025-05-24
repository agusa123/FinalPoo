package org.example;

public class Mora {
    private int idCuota;
    private double montoPenalidad;
    private double fechaRegistro;

    public Mora(int idCuota, double montoPenalidad, double fechaRegistro) {
        this.idCuota = idCuota;
        this.montoPenalidad = montoPenalidad;
        this.fechaRegistro = fechaRegistro;
    }

    public double getMontoPenalidad() {
        return montoPenalidad;
    }
    public double getFechaRegistro() {
        return fechaRegistro;
    }
    public int getIdCuota() {
        return idCuota;
    }
    public void setIdCuota(int idCuota) {
        this.idCuota = idCuota;
    }
    public void setMontoPenalidad(double montoPenalidad) {
        this.montoPenalidad = montoPenalidad;
    }
    public void setFechaRegistro(double fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

}
