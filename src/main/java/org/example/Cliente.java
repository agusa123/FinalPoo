package org.example;

public class Cliente {
    private int idCliente;
    private String nombre;
    private String apellido;
    private int dni;
    private String direccion;
    private String telefono;
    private String correoElectronico;

    public Cliente(int idCliente, String nombre, String apellido, int dni, String direccion, String telefono, String correoElectronico) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellido= apellido;
        this.dni = dni;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
    }

    //geters
    public int getIdCliente() { return idCliente; }
    public String getNombre() { return nombre; }
    public String getApellido(){return apellido;}
    public int getDni(){return dni;}
    public String getDireccion() { return direccion; }
    public String getTelefono() { return telefono; }
    public String getCorreoElectronico() { return correoElectronico; }
    //seters
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public void setDni(int dni) { this.dni = dni; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public void setCorreoElectronico(String correoElectronico) { this.correoElectronico = correoElectronico; }
}
