import java.time.LocalDate;
import java.util.ArrayList;

public class Rover{
    double potenciaInicial;
    double potenciaActual;
    String nombrePila;
    int posicionInicial[2] = {0, 0};
    int posicionActual[2] = {0, 0};
    String codigoRover;
    int recargasDisponibles = 5;
    int deteccionesRealizadas;
    ArrayList<ArrayList<String>> listaMandatos;
    //Metodos Constructores:
    Rover(String nombrePila, String codigoRover, double potencia){
        potenciaInicial = potencia;
        potenciaActual = posicionInicial;
        this.nombrePila = nombrePila;
        this.codigoRover = codigoRover;
        deteccionesRealizadas = 0;
        listaMandatos = new ArrayList();
    }
    Rover(String nombrePila, String codigoRover){
        this(nombrePila, codigoRover, 100.0);
    }
    //Metodos Publicos:



    //Fin Metodos publicos\\


    //Metodos Privados:
    private boolean detectarFugaCalor(){
        double valor = Math.random()
        return valor >= 0.5;
    }
    private boolean haySuficientePotencia(doubel gasto){
        return potenciaActual >= gasto;
    }
    private void restarXActual(){
        this.posicionActual[0] -= 1;
    }
    private void restarYActual(){
        this.posicionActual[1] -= 1;
    }
    private void sumarXActual(){
        this.posicionActual[0] += 1;
    }
    private void sumarYActual(){
        this.posicionActual[1] += 1;
    }
    private void registrarMandato(String tipo, String status){
        LocalDateTime fechaHora = LocalDateTime.now();
        fechaHoraString = fechaHora.toString();

        ArrayList mandato = {fechaHoraString, tipo, status};
        listaMandatos.add(mandato);
    }
    //Fin Metodos privados\\




}