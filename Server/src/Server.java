import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static String path;
    public static Routes routes = new Routes();

    public static void main(String[] args) {
        try{
            path = args[0];
            routes.read(path);
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Вы не ввели путь к коллекции при запуске, используем чистую коллекцию.");
            path = "";
        }

        Runnable input = new InputManager(routes);
        Thread tInput = new Thread(input);
        tInput.start();
        try (ServerSocket server = new ServerSocket(8800)) {
            while (true) {
                Socket incoming = server.accept();
                System.out.println(incoming + " подключился к серверу.");
                Runnable r = new ServerConnection(routes, incoming);
                Thread t = new Thread(r);
                t.start();
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            System.exit(1);
        }
    }
}
