package logic.partido;

import logic.persona.Jugador;

class Penal{
    private final int numero;
    private final Jugador jugador;
    private final boolean anotado;
    public static final String ANOTADO_SI = "SI";
    public static final String ANOTADO_NO = "NO";

    public Penal(int numero, Jugador jugador, boolean anotado) {
        this.numero = numero;
        this.jugador = jugador;
        this.anotado = anotado;
    }

    public int getNumero() {
        return numero;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public boolean isAnotado() {
        return anotado;
    }
}