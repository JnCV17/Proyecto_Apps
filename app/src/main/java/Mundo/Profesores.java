package Mundo;

public class Profesores {

    private String cedula;
    private String nombre;
    private String apellido;
    private String facultad;
    private String materia;
    private String programa;
    private String rolNombre;
    private String usuario;
    private String contrasenia;
    private boolean isActivo = true;



    public Profesores(String cedula, String nombre, String apellido, String facultad, String materia, String programa, String rolNombre, String usuario, String contrasenia) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.facultad = facultad;
        this.materia = materia;
        this.programa = programa;
        this.rolNombre = rolNombre;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }

    public Profesores(String cedula, String nombre, String apellido, String facultad, String materia, String programa, String rolNombre, String usuario, String contrasenia, boolean isActivo) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.facultad = facultad;
        this.materia = materia;
        this.programa = programa;
        this.rolNombre = rolNombre;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.isActivo = isActivo;
    }

    public Profesores(String id) {

        this.cedula = id;
    }

    public boolean isActivo() {
        return isActivo;
    }

    public void setActivo(boolean activo) {
        isActivo = activo;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public String getRolNombre() {
        return rolNombre;
    }

    public void setRolNombre(String rolNombre) {
        this.rolNombre = rolNombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
}
