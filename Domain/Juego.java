package Domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class Juego {
private  final Podium podium;
private  final Pista pista;
private Estado estado;

    public Juego(Integer numCarriles, Integer kilometros,String nombreDePista){
        Map<Integer, Carril> carriles = generarCarriles(numCarriles,kilometros);
        pista = new Pista(UUID.randomUUID().toString(), carriles, nombreDePista);
        this.podium = new Podium();
        estado = Estado.NO_INICIADO;
    }
    public void agregarAutoACarril(Integer carril, Auto auto){
        if (Objects.isNull(auto.conductor())){
            throw new IllegalArgumentException("El Auto no tiene conductor");
        }
        pista.agregarAutoACarril(carril, auto);

    }
    public Podium iniciarJuego(){
        validarCarriles();
        iniciarAutos();
        do {
            estado=Estado.INICIDO;
            pista.carriles().forEach(moverEnCarril());
        }while (estado.equals(Estado.INICIDO));
        return podium;
    }

    private Map<Integer, Carril> generarCarriles(Integer numCarriles, Integer kilometros){
        Map<Integer, Carril> carriles = new HashMap<>();
        for (int i = 1; numCarriles>= i ; i++) {
            carriles.put(i, new Carril(i,kilometros));
        }return carriles;
    }


    private void validarCarriles(){
        int cantidadDeAutos = 0;
        for (Carril carril : pista.carriles().values()){
            cantidadDeAutos = carril.autos().size() + cantidadDeAutos;
        }
        if (cantidadDeAutos <= 2){
            throw new IllegalStateException("No tiene la cantidad de autos suficientes");
        }
    }
    private void iniciarAutos(){
        pista.carriles().values().forEach(carril -> carril.autos().forEach(Auto::iniciar));
    }

    private BiConsumer<Integer, Carril> moverEnCarril(){
        return (id, carril) -> carril.autos().forEach(moverAuto(carril));
    }


    private Consumer<Auto> moverAuto(Carril carril){
        return auto -> {
            int dado= auto.conductor().lanzarDado();
            IntStream.range(0, dado*100).forEach(value -> auto.aumentarMetros());
            determinarGanador(carril, auto);
        };
    }

    private void determinarGanador(Carril carril, Auto auto){
        if (auto.estaEnMarcha()){
            double progreso = carril.progresoAuto(auto.placa());
            if(progreso >= 100){
                determinarPodium(auto);
            }
        }
    }

    private void determinarPodium(Auto auto){
        if (Objects.isNull(podium.primero())) {
            podium.setPrimero(auto.conductor());
            auto.pararMarcha();
        } else if (Objects.isNull(podium.segundo())) {
            podium.setSegundo(auto.conductor());
            auto.pararMarcha();
        } else if (Objects.isNull(podium.tercero())) {
            podium.setTercero(auto.conductor());
            auto.pararMarcha();
            finDelJuego();
        }
    }

    private void finDelJuego(){
        estado = Estado.FINALIZADO;
    }


    public enum Estado {
        INICIDO, FINALIZADO, NO_INICIADO
    }
}
