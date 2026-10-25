public class Main {
    public static void main(String[] args) {
        Ascensor ascensor = new Ascensor();
        Usuarios u1= new Usuarios(0,3,ascensor);
        Usuarios u2= new Usuarios(2,5,ascensor);
        Usuarios u3= new Usuarios(4,0,ascensor);

        ascensor.registrarUsuarios(u1);
        ascensor.registrarUsuarios(u2);
        ascensor.registrarUsuarios(u3);

        new Thread(ascensor).start();
        new Thread(u1).start();
        new Thread(u2).start();
        new Thread(u3).start();
    }
}
