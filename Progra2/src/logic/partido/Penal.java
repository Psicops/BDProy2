package logic.partido;

import logic.persona.Jugador;

public class Penal{
    private final String numero;
    private final Jugador jugador;
    private final boolean anotado;
    public static final String ANOTADO_SI = "SI";
    public static final String ANOTADO_NO = "NO";

    public Penal(String numero, Jugador jugador, boolean anotado) {
        this.numero = numero;
        this.jugador = jugador;
        this.anotado = anotado;
    }

    public String getNumero() {
        return numero;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public String isAnotado() {
        String anota = "";
        if(anotado)
            anota = ANOTADO_SI;
        else
            anota = ANOTADO_NO;
        return anota;
    }
    
    @Override
    public String toString(){
        if(anotado)
            return jugador.getNombre()+" "+jugador.getApellido1()
                    +" ("+jugador.getNumeroCamisa()+") ANOTADO";
        else
            return jugador.getNombre()+" "+jugador.getApellido1()
                    +" ("+jugador.getNumeroCamisa()+") NO ANOTADO";
    }
}