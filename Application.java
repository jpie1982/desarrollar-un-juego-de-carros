import Domain.Auto;
import Domain.Conductor;
import Domain.Juego;
import Domain.Podium;

public class Application {
    public static void main(String[] args) {
    int numCarrile = 3;
    int kilometros = 200;

    Juego juego = new Juego(numCarrile,kilometros,"Pista Los Andes");
        Auto rojo = new Auto(Auto.Color.ROJO,"XX1");
        rojo.agregarConductor(new Conductor("C1", "Juan","jpie1982@gmail.com","Juancho"));
        Auto azul = new Auto(Auto.Color.AZUL,"XX2");
        azul.agregarConductor(new Conductor("C2", "Ana","ana@gmail.com","Anita"));
        Auto negro = new Auto(Auto.Color.NEGRO,"XX3");
        negro.agregarConductor(new Conductor("C3", "Pedro","pedro@gmail.com","Pedrinho"));
        Auto blanco = new Auto(Auto.Color.BLANCO,"XX4");
        blanco.agregarConductor(new Conductor("C4", "Jhon","jhon@gmail.com","Nieves"));

        juego.agregarAutoACarril(1,rojo);
        juego.agregarAutoACarril(2,azul);
        juego.agregarAutoACarril(3,negro);
        juego.agregarAutoACarril(1,blanco);

        Podium podium = juego.iniciarJuego();
        System.out.println(podium);

    }
}