import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ClientConnection {
    private static Scanner fromKeyboard;
    private static ObjectOutputStream toServer;
    private static ObjectInputStream fromServer;
    private BufferedReader in; // поток чтения из сокета
    private BufferedWriter out; // поток чтения в сокет
    //Средство подключения к серверу. Если подключение не удалось, отлавливается ошибка и происходит конекшан ещё раз

    public void startServer() throws Exception {
        try (Scanner scanner = new Scanner(System.in)) {
            fromKeyboard = scanner;
            try (Socket socket = new Socket("127.0.0.1", 8800)) {
                socket.setSoTimeout(6000);
                try (ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                     ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream())) {
                    System.out.println("Соединение с сервером было установлено.");
                    toServer = outputStream;
                    fromServer = inputStream;
                    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    while (true) {
                        System.out.print("# ");
                        String input = fromKeyboard.nextLine();
                        if (commandManager(input))
                            System.out.println("Ответ от сервера: " + (String) fromServer.readObject());
                    }
                }
            } catch (IOException e) {
                System.out.println("Не удалось подключится к серверу/Сервер занят, пытаемся заново");
                System.out.println("Подключение");
                startServer();
            }
        }
    }

    public boolean commandManager(String input) throws IOException {
        try {
            if (Validation.validate(input)) {
                if(input.substring(0, input.indexOf(' ')).equals("execute_script")) {
                    executeScript(input.substring(input.indexOf(' ') + 1));
                    return true;
                }
                else {
                    send(new ClientRequest(input.substring(0, input.indexOf(' ')), input.substring(input.indexOf(' ') + 1)));//Если сделать по-другому у нас будет exception
                    return true;
                }
            } else {
                return false;
            }
        } catch (StringIndexOutOfBoundsException e) {
            send(new ClientRequest(input));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void send(Object object) throws IOException {
        toServer.writeObject(object);
        toServer.flush();
    }

    public void executeScript(String arg){
        try{
            File file = new File(arg);
            if (!file.exists()) System.out.println("Файла не существует");
            else if (file.exists() && !file.canRead()) System.out.println("Файл существует, нет прав на чтение.");
            else if (file.exists() && !file.canExecute()) System.out.println("Проверьте файл на выполнение");
            else{
                Scanner scanner = new Scanner(file);
                while(scanner.hasNextLine()){
                    String line = scanner.nextLine();
                    if(commandManager(line))
                        System.out.println((String) fromServer.readObject());
                }
            }
        }catch (FileNotFoundException e){
            System.out.println("Файл не был обнаружен");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
