package logic.persona;

import java.sql.Date;

 public class Federativo extends Persona{
     private final String nacionalidad;
     private final String puesto;
     private final Date inicioPuesto;
     public static final String DIRECTOR = "Director";
     public static final String VICE = "Vicepresidente";
     public static final String SECRETARIO = "Secretario";
     public static final String TESORERO = "Tesorero";
     public static final String FISCAL = "Fiscal";
     public static final String VOCAL = "Vocal";
     
     public Federativo(String nombre, String apellido1, String apellido2,
                       String numeroPasaporte, Date fechaNacimiento, 
                       String nacionalidad, String puesto, Date inicioPuesto) {
        super(nombre, apellido1, apellido2, numeroPasaporte, fechaNacimiento);
        this.nacionalidad = nacionalidad;
        this.puesto = puesto;
        this.inicioPuesto = inicioPuesto;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public String getPuesto() {
        return puesto;
    }

    public Date getInicioPuesto() {
        return inicioPuesto;
    }
    
    @Override
    public String toString(){
        return this.getNombre()+" "+this.getApellido1()+" "
              +this.getApellido2()+" ("+this.getPuesto()+")";
    }
    
    @Override
    public boolean equals(Object federativo){
        return federativo instanceof Federativo &&
               super.equals(federativo) &&
               puesto.equals(((Federativo)federativo).getPuesto())&&
               nacionalidad.equals(((Federativo)federativo).getNacionalidad()) &&
               inicioPuesto.equals(((Federativo)federativo).getInicioPuesto());
    }
 }