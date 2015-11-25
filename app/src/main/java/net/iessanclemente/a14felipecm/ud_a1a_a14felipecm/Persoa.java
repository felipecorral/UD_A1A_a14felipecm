package net.iessanclemente.a14felipecm.ud_a1a_a14felipecm;

/**
 * Created by felipe on 23/11/15.
 */
public class Persoa {
    private String nombre;
    private String descripcion;

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Persoa(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Nome='" + nombre + '\'' +
                ", Descrición='" + descripcion ;
    }
}
