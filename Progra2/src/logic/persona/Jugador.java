package logic.persona;

import java.sql.Date;

public class Jugador extends Persona{
    public static final String PORTERO = "Portero";
    public static final String DEFENSA = "Defensa";
    public static final String MEDIO = "Mediocampista";
    public static final String DELANTERO = "Delantero";
    
    private final String puesto;
    private final int numeroCamisa;

    public Jugador(String nombre, String apellido1, String apellido2, 
                   String numeroPasaporte, Date fechaNacimiento, 
                   String Puesto, int numeroCamisa) {
        super(nombre, apellido1, apellido2, numeroPasaporte, fechaNacimiento);
        this.puesto = Puesto;
        this.numeroCamisa = numeroCamisa;
    }
    
    public String getPuesto() {
        return puesto;
    }

    public int getNumeroCamisa() {
        return numeroCamisa;
    }
    
    @Override
    public String toString(){
        return this.getNombre()+" "+this.getApellido1()+" "
              +this.getApellido2()+" "+numeroCamisa+" ("+this.getPuesto()+")";
    }
    
    @Override
    public boolean equals(Object jugador){
        return jugador instanceof Jugador &&
               super.equals(jugador) &&
               puesto.equals(((Jugador)jugador).getPuesto())&&
               numeroCamisa == ((Jugador)jugador).getNumeroCamisa();
    }
}