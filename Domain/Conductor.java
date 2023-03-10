package Domain;

import java.util.Random;

public class Conductor extends Jugador{
    private final String id;
    private String nombre;

    public Conductor(String id, String nombre, String email,String username) {
        super(id,email,username);
        this.id = id;
        this.nombre = nombre;
    }
    public int lanzarDado(){
        Random r = new Random();
        return r.nextInt(6)+1;
    }

    public String Nombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String Id() {
        return id;
    }

    @Override
    public String toString() {
        return "Conductor{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
