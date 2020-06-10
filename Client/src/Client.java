import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws Exception {
        System.out.println("Новведение, теперь команду можно вводить в любом регистре. (Аргументы всё так же зависимы к регистру) ");
        ClientConnection clientConnection = new ClientConnection();
        clientConnection.startServer();
    }
}
