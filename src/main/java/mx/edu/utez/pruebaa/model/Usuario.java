package mx.edu.utez.pruebaa.model;

public class Usuario {
    private int id;
    private String nombre;
    private String contra;
    private String correo;
    private boolean estado;

    public Usuario() {
    }

    public Usuario(int id, String nombre, String contra, String correo, boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.contra = contra;
        this.correo = correo;
        this.estado = estado;
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
}
