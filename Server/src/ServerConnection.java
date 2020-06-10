import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerConnection implements Runnable {
    Routes routes;
    Socket connection;

    public ServerConnection(Routes routes, Socket connection){
        this.routes = routes;
        this.connection = connection;
        init();
    }
    /*public void startServer() throws IOException {
        while (true) {
            try (ServerSocket server = new ServerSocket(8800)) {
                System.out.println("Ожидание подключения клиентов");
                Socket incoming = server.accept();
                //Routes routes = new Routes("this", this);
                //routes.read(path);
                System.out.println("Соединение пришло.");
                System.out.println("Клиент: " + incoming + " подсоединился.");
                toServer = new ObjectInputStream(incoming.getInputStream());
                fromServer = new ObjectOutputStream(incoming.getOutputStream());
                init();
                while (true) {
                    ClientRequest a = (ClientRequest) toServer.readObject();
                    System.out.println("От клиента пришла команда: " + a.getCommand());
                    System.out.println("С аргументами: " + a.getArgument());
                    if(!a.getCommand().equals("exit")) {
                        Command execute = Command.getCommand(a.getCommand());
                        execute.setArg(a.getArgument());
                        fromServer.writeObject(execute.execute());
                    }
                    else{
                        fromServer.writeObject("Вам запрещено пользоваться данной командой. Данная команда исполняется только на сервере");
                    }
                }
            } catch (SocketException e) { //Для пользователей OC Windows
                e.printStackTrace();
                System.out.println("Соединение с клиентом было разорвано");
                continue;
            }  catch (NoSuchFieldException e){
                fromServer.writeObject("Не было найдено такой команды");
            } catch (IOException | ClassNotFoundException e){ //Для пользователей Unix, Linux
                System.out.println("Соединение с клиентом было разорвано");
            }
        }
    }*/
    public void init(){
        Command.clientInitCommand("help", "Показать лист возможных команд", new Help(), routes);
        Command.clientInitCommand("about", "Показать информацию о программе и разработчике", new About(), routes);
        Command.clientInitCommand("add", "Добавляет новый объект в коллекцию.Аргументы вводятся через запятую", new Add(), routes);
        Command.clientInitCommand("info", "Показывает информацию о всех объектах, хранящихся в коллекции", new Info(), routes);
        Command.clientInitCommand("clear", "Полностью очищает коллекцию", new Clear(), routes);
        Command.clientInitCommand("show", "Выводит все элементы коллекции", new Show(), routes);
        Command.clientInitCommand("average_of_distance", "Выводит среднее значение поля distance всех элементов в коллекции", new AverageOfDistance(), routes);
        Command.clientInitCommand("remove_by_id", "Удаляет элемент из коллекции", new RemoveById(), routes);
        Command.clientInitCommand("update_by_id", "Обновляет объект в коллекции", new UpdateId(), routes);
        Command.clientInitCommand("remove_greater_than", "Удаляет все объекты, id которых выше, чем заданный в аргументе", new RemoveGreater(), routes);
        Command.clientInitCommand("average_of_distance", "Показывает среднее значение поля Distance всех элементов коллекции", new AverageOfDistance(), routes);
        Command.clientInitCommand("history", "Показать историю всех команд", new HistoryCommand(), routes);
        Command.clientInitCommand("count_greater_than_distance", "Показывает кол-во объектов, значение поля Distance которых больше, чем аргумент", new CountDistance(), routes);
    }

    @Override
    public void run() {
        try (ObjectInputStream getFromClient = new ObjectInputStream(connection.getInputStream());
             ObjectOutputStream sendToClient = new ObjectOutputStream(connection.getOutputStream())) {
            while (true){
                try{
                    ClientRequest a = (ClientRequest) getFromClient.readObject();
                    System.out.println("От клиента пришла команда: " + a.getCommand());
                    System.out.println("С аргументами: " + a.getArgument());
                    if(!a.getCommand().equals("exit")) {
                        Command execute = Command.getCommand(a.getCommand());
                        execute.setArg(a.getArgument());
                        Command.history.addCommand(a.getCommand());
                        sendToClient.writeObject(execute.execute());
                    }
                    else{
                        sendToClient.writeObject("Вам запрещено пользоваться данной командой. Данная команда исполняется только на сервере");
                    }
                }catch (IOException | ClassNotFoundException ex) {
                    System.out.println("Клиент отключился.");
                    break;
                }catch (NoSuchFieldException e){
                    sendToClient.writeObject("Такой команды найдено не было");
                }
            }
        }catch (IOException e){
            System.out.println("Клиент отключился от сервера");
        }
    }
}
