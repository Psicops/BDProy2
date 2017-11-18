package logic.persona;

import java.sql.Date;

public class Asistente extends Persona{
    private final String nacionalidad;
    private final String puesto;
    private final Date inicioPuesto;
    public static final String ENTRENADOR = "Entrenador";
    public static final String TECNICO = "Técnico";
    public static final String PREP_FISICO = "Preparador Físico";
    public static final String MEDICO = "Médico";
    public static final String PSICO = "Psicólogo";
    public static final String NUTRICION = "Nutricionista";
    public static final String ADMIN = "Administrativo";
    public static final String DELEGADO = "Delegado de Equipo";

    
    public Asistente(String nombre, String apellido1, String apellido2,
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
    
    public boolean equals(Object asistente){
        return asistente instanceof Asistente &&
               super.equals(asistente) &&
               puesto.equals(((Asistente)asistente).getPuesto())&&
               nacionalidad.equals(((Asistente)asistente).getNacionalidad()) &&
               inicioPuesto.equals(((Asistente)asistente).getInicioPuesto());
    }
}