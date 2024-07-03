package mx.edu.utez.pruebaa.model;

public class Usuario {
    private int id;
    private String nombre;
    private String contra;
    private String correo;
    private boolean estado;
    private String codigo;

    public Usuario() {
    }

    public Usuario(int id, String nombre, String contra, String correo, boolean estado, String codigo) {
        this.id = id;
        this.nombre = nombre;
        this.contra = contra;
        this.correo = correo;
        this.estado = estado;
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
