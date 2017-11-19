package cargaInicialExcel;

import java.sql.Date;

public class Jugador extends Persona{
    public static final String PORTERO = "Portero";
    public static final String DEFENSA = "Defensa";
    public static final String MEDIO = "Mediocampista";
    public static final String DELANTERO = "Delantero";
    
    private final String Puesto;
    private final int numeroCamisa;

    public Jugador(String nombre, String apellido1, String apellido2, 
                   String numeroPasaporte, Date fechaNacimiento, 
                   String Puesto, int numeroCamisa) {
        super(nombre, apellido1, apellido2, numeroPasaporte, fechaNacimiento);
        this.Puesto = Puesto;
        this.numeroCamisa = numeroCamisa;
    }

    @Override
    public String toString() {
        return "Jugador{" + "\nPasaporte: " + super.getNumeroPasaporte() + "\nNombre: " + super.getNombre() +
                "\nApellido1: " + super.getApellido1() +"\nApellido2: " + super.getApellido2() +
                "\nPuesto: " + Puesto + "\nNumero Camisa: " + numeroCamisa + "}";
    }
    
    public String getPuesto() {
        return Puesto;
    }

    public int getNumeroCamisa() {
        return numeroCamisa;
    }
}