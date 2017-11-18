package logic.persona;

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
    
    @Override
    public boolean equals(Object persona){
        return persona instanceof Persona &&
               nombre.equals(((Persona)persona).getNombre()) &&
               apellido1.equals(((Persona)persona).getApellido1()) &&
               apellido2.equals(((Persona)persona).getApellido2()) &&
               numeroPasaporte.equals(((Persona)persona).getNumeroPasaporte()) &&
               fechaNacimiento.equals(((Persona)persona).getFechaNacimiento());
    }
}