package Mundo;

public class Curso {

    private String id;
    private String nombre;
    private String descripcion;
    private String fechaInicio;
    private String fechaFinal;
    private String horario;
    private String fechaParciales;
    private String novedades;

    public Curso(String id, String nombre, String descripcion, String fechaInicio, String fechaFinal, String horario, String fechaParciales, String novedades) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.horario = horario;
        this.fechaParciales = fechaParciales;
        this.novedades = novedades;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getFechaParciales() {
        return fechaParciales;
    }

    public void setFechaParciales(String fechaParciales) {
        this.fechaParciales = fechaParciales;
    }

    public String getNovedades() {
        return novedades;
    }

    public void setNovedades(String novedades) {
        this.novedades = novedades;
    }
}
