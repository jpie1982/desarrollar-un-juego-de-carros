package Domain;

import java.util.ArrayList;
import java.util.List;

public class Jugador {
    private final String idJugador;
    private String email;
    private List <Integer> puntos;
    private final String username;

    public Jugador(String idJugador, String email, String username) {
        this.idJugador = idJugador;
        this.email = email;
        this.puntos = new ArrayList<>();
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void agregarPuntos(Integer puntos) {
        this.puntos.add(puntos);
    }

    public Integer Puntos() {
        return puntos.stream().reduce(Integer::sum).orElseThrow();
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "idJugador='" + idJugador + '\'' +
                ", email='" + email + '\'' +
                ", puntos=" + puntos +
                ", username='" + username + '\'' +
                '}';
    }
}
