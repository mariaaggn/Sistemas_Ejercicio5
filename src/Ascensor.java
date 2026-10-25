import java.util.LinkedList;

public class Ascensor implements Runnable{
    private int pisoActual;
    private final LinkedList<Usuarios> pendientes = new LinkedList<>();
    private boolean dentro= false;
    private boolean bajando= false;
    private Usuarios turno = null;

    public synchronized void registrarUsuarios(Usuarios u){
        pendientes.add(u);
    }

    public synchronized void llamar(Usuarios u){
        System.out.println("Usuario " + u.getId() + " espera en piso " + u.getPisoOrigen());
    }

    public synchronized void subir(Usuarios u) throws InterruptedException {
        while(turno!= u){
            wait();
        }
        System.out.println("Usuario " + u.getId() + " sube al ascensor");
        dentro=true;
        notifyAll();
    }

    public synchronized void moverHasta(int destino) throws InterruptedException {
        while(pisoActual!=destino){
            if(destino>pisoActual){
                pisoActual++;
            }else{
                pisoActual--;
            }
            System.out.println("Ascensor en el piso " + pisoActual);
            Thread.sleep(500);
        }
    }

    public synchronized void bajar(Usuarios u) throws InterruptedException {
        while(!bajando || turno!=u){
            wait();
        }
        System.out.println("Usuario " + u.getId() + " baja en el piso "  + pisoActual);
        dentro=false;
        notifyAll();
    }

    private synchronized void recoger(Usuarios u) throws InterruptedException {
        turno=u;
        notifyAll();
        while(!dentro){
            wait();
        }
    }

    private synchronized void dejar() throws InterruptedException {
        bajando=true;
        notifyAll();
        while(dentro){
            wait();
        }
        bajando=false;
        turno=null;
        pendientes.removeFirst();
    }

    private  synchronized Usuarios siguiente(){
        return pendientes.peekFirst();
    }
    @Override
    public void run() {
        try{
            Usuarios u = siguiente();
            while(u !=null){
                moverHasta(u.getPisoOrigen());
                recoger(u);
                moverHasta(u.getPisoDestino());
                dejar();
                u = siguiente();
            }
            moverHasta(-1);
            System.out.println("Ascensor en el piso " + pisoActual);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
