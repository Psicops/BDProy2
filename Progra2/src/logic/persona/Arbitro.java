package logic.persona;

import java.sql.Date;

public class Arbitro extends Persona{
    private final String nacionalidad;
    private final Date inicioPuesto;
    
    public Arbitro(String nombre, String apellido1, String apellido2,
                   String numeroPasaporte, Date fechaNacimiento, 
                   String nacionalidad, Date inicioPuesto) {
        super(nombre, apellido1, apellido2, numeroPasaporte, fechaNacimiento);
        this.nacionalidad = nacionalidad;
        this.inicioPuesto = inicioPuesto;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public Date getInicioPuesto() {
        return inicioPuesto;
    }
    
    @Override
    public String toString(){
        return this.getNombre()+" "+this.getApellido1()+" "+this.getApellido2();
    }
    
    public boolean equals(Object arbitro){
        return arbitro instanceof Arbitro &&
               super.equals(arbitro) &&
               nacionalidad.equals(((Arbitro)arbitro).getNacionalidad()) &&
               inicioPuesto.equals(((Arbitro)arbitro).getInicioPuesto());
    }
}