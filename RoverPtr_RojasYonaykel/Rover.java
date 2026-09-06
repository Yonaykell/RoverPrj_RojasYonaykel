import java.time.LocalDateTime;
import java.util.ArrayList;

public class Rover{
    private double potenciaInicial;
    private double potenciaActual;
    private String nombrePila;
    private int[] posicionInicial = {0, 0};
    private int[] posicionActual = {0, 0};
    private String codigoRover;
    private int recargasDisponibles = 5;
    private int deteccionesRealizadas;
    private ArrayList<ArrayList<String>> listaMandatos;
    
    private static int cantidadRovers;
    
    private static final double POTENCIA_INICIAL_DEFAULT = 100.0;
    private static final double COSTO_DETECCION = 0.25;
    private static final double COSTO_DESPLAZAMIENTO = 0.5;
    private static final double UMBRAL_FUGA_CALOR = 0.5;
    //Metodos Constructores:
    Rover(String nombrePila, String codigoRover, double potencia){
        potenciaInicial = potencia;
        potenciaActual = potencia;
        this.nombrePila = nombrePila;
        this.codigoRover = codigoRover;
        deteccionesRealizadas = 0;
        listaMandatos = new ArrayList<>();
        cantidadRovers ++;
    }
    Rover(String nombrePila, String codigoRover){
        this(nombrePila, codigoRover, POTENCIA_INICIAL_DEFAULT);
    }
    //Metodos Publicos:
    public void desplazarse_adelante(){
        if(haySuficientePotencia(COSTO_DETECCION + COSTO_DESPLAZAMIENTO)){
            if(!(detectarFugaCalor())){
                potenciaActual -= COSTO_DESPLAZAMIENTO;
                sumarYActual();
                registrarMandato("Desplazamiento adelante", "Posible: Todo en orden");
                
            }
            else{
            registrarMandato("Desplazamiento adelante", "No posible: Fuga de calor detectada");
            }
        }
        else{
            registrarMandato("Desplazamiento adelante", "No posible: Potencia insuficiente");
        }
    }
    public void desplazarse_atras(){
        if(haySuficientePotencia(COSTO_DETECCION + COSTO_DESPLAZAMIENTO)){
            if(!(detectarFugaCalor())){
                potenciaActual -= COSTO_DESPLAZAMIENTO;
                restarYActual();
                registrarMandato("Desplazamiento atras", "Posible: Todo en orden");
                
            }
            else{
            registrarMandato("Desplazamiento atras", "No posible: Fuga de calor detectada");
            }
        }
        else{
            registrarMandato("Desplazamiento atras", "No posible: Potencia insuficiente");
        }
    }
    public void desplazarse_derecha(){
        if(haySuficientePotencia(COSTO_DETECCION + COSTO_DESPLAZAMIENTO)){
            if(!(detectarFugaCalor())){
                potenciaActual -= COSTO_DESPLAZAMIENTO;
                sumarXActual();
                registrarMandato("Desplazamiento derecha", "Posible: Todo en orden");
                
            }
            else{
            registrarMandato("Desplazamiento derecha", "No posible: Fuga de calor detectada");
            }
        }
        else{
            registrarMandato("Desplazamiento derecha", "No posible: Potencia insuficiente");
        }
    }
    public void desplazarse_izquierda(){
        if(haySuficientePotencia(COSTO_DETECCION + COSTO_DESPLAZAMIENTO)){
            if(!(detectarFugaCalor())){
                potenciaActual -= COSTO_DESPLAZAMIENTO;
                restarXActual();
                registrarMandato("Desplazamiento izquierda", "Posible: Todo en orden");
                
            }
            else{
            registrarMandato("Desplazamiento izquierda", "No posible: Fuga de calor detectada");
            }
        }
        else{
            registrarMandato("Desplazamiento izquierda", "No posible: Potencia insuficiente");
        }
    }
    public int[] getPosicionActual(){
        return posicionActual.clone();
    }
    public double getPotenciaActual(){
        return potenciaActual;
    }
    public void recargaPotencia(double recarga){
        if(recarga > 0){
            if (recargasDisponibles != 0){
                potenciaActual += recarga;
                registrarMandato("Recarga", "Posible: Todo en orden");
                recargasDisponibles -= 1;
            }
            else{
                registrarMandato("Recarga", "No posible: Sin recargas disponibles"); 
            }
        }
        else{
            registrarMandato("Recarga", "No posible: Parametro invalido");    
        }
    }
    public String toString(){
        String estado = "";
    
        estado += "Codigo del Rover: " + codigoRover + "\n";
        estado += "Nombre de pila: " + nombrePila + "\n";
        estado += "Potencia inicial: " + potenciaInicial + "\n";
        estado += "Potencia disponible: " + potenciaActual + "\n";
        estado += "Recargas disponibles: " + recargasDisponibles + "\n";
        estado += "Detecciones realizadas: " + deteccionesRealizadas + "\n";
        estado += "Posicion inicial: (" + posicionInicial[0] + ", "
                + posicionInicial[1] + ")\n";
        estado += "Posicion actual: (" + posicionActual[0] + ", "
                + posicionActual[1] + ")\n";
    
        estado += "Lista de mandatos:\n";
        for (ArrayList<String> mandato : listaMandatos){
            estado += "  Fecha y hora: " + mandato.get(0) + "\n";
            estado += "  Tipo: " + mandato.get(1) + "\n";
            estado += "  Estatus: " + mandato.get(2) + "\n";
        }
        return estado;
    }
    //Fin Metodos publicos\\
    
    //Metodos Static\\
    static int getCantidadRovers(){
        return cantidadRovers;
    }
    //Fin Metodos Static\\
    
    //Metodos Privados:
    private boolean detectarFugaCalor(){
        potenciaActual -= COSTO_DETECCION;
        deteccionesRealizadas ++;
        double valor = Math.random();
        return valor >= UMBRAL_FUGA_CALOR;
    }
    private boolean haySuficientePotencia(double gasto){
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
        String fechaHoraString = fechaHora.toString();

        ArrayList mandato = new ArrayList<>();
        mandato.add(fechaHoraString);
        mandato.add(tipo);
        mandato.add(status);
        
        listaMandatos.add(mandato);
    }
    //Fin Metodos privados\\
}