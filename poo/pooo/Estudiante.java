import java.util.Date;


public class Estudiante extends Persona {
    private Date fechaDeNacimiento;
    private Estado estado;

    public enum Estado {
        MATRICULADO, INACTIVO, GRADUADO;
    }

    public Estudiante(int id, String nombre, String apellido, Date fechaDeNacimiento, Estado estado) {
        super(id, nombre, apellido);
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.estado = estado;
    }

    public Date getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(Date fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public void cambiarEstado(Estado nuevoEstado) {
        this.estado = nuevoEstado;
    }

    @Override
    public String toString() {
        return "                               estudiantes:       "+"\n"+
                "id: "+getId() + "| nombre: "+getNombre() + "| apellido: "+getApellido() + "| Fecha de nacimiento: "+fechaDeNacimiento+ "| estado: "+estado;
    }
}