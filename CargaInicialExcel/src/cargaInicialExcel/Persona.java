package cargaInicialExcel;

import java.sql.Date;

public abstract class Persona{
    private final String nombre;
    private final String apellido1;
    private final String apellido2;
    private final String numeroPasaporte;
    private final Date fechaNacimiento;

    protected Persona(String nombre, String apellido1, String apellido2, String numeroPasaporte, Date fechaNacimiento) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.numeroPasaporte = numeroPasaporte;
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public String getNumeroPasaporte() {
        return numeroPasaporte;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }
}