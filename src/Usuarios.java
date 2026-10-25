public class Usuarios implements Runnable{
private int pisoOrigen;
private int pisoDestino;
private static int contador=0;
private int id=0;
private Ascensor ascensor;

    public Usuarios(int pisoOrigen, int pisoDestino, Ascensor ascensor) {
        this.pisoOrigen = pisoOrigen;
        this.pisoDestino = pisoDestino;
       id= contador++;
        this.ascensor = ascensor;
    }

    @Override
    public void run() {
        try{
            ascensor.llamar(this);
            ascensor.subir(this);
            ascensor.bajar(this);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public int getPisoOrigen() {
        return pisoOrigen;
    }

    public void setPisoOrigen(int pisoOrigen) {
        this.pisoOrigen = pisoOrigen;
    }

    public int getPisoDestino() {
        return pisoDestino;
    }

    public void setPisoDestino(int pisoDestino) {
        this.pisoDestino = pisoDestino;
    }

    public static int getContador() {
        return contador;
    }

    public static void setContador(int contador) {
        Usuarios.contador = contador;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
