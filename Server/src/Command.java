import java.io.Serializable;
import java.util.ArrayList;

public abstract class Command implements Serializable {

    String syntax;
    String helpText;
    private String arg;
    public static History history = new History();

    Routes routes = new Routes(); //Не инициализируется и если воспользоваться ей из клиента то будет Exception

    public static ArrayList<Command> commands = new ArrayList<>(); //Список комманд, которыми мы пользуется во время исполнения кода

    public Command(Routes routes) {
        this.routes = routes;
    }

    public Command() {

    }


    public String getArg(){
        return arg;
    }

    public void setArg(String par){
        this.arg = par;
    }

    //Добавление команды в список сверху
    public void setRoutes(Routes routes){
        this.routes = routes;
    }

    public static void clientInitCommand(String syntax, String helpText, Command e, Routes routes){
        Command command = e;
        command.helpText = helpText;
        command.syntax = syntax;
        command.routes = routes;
        commands.add(command); //Добавил
    }


    public static Command getCommand(String name) throws NoSuchFieldException{
        for (Command command : commands) {
            if (command.syntax.equals(name))
                return command;
        }
        throw new NoSuchFieldException("Такой команды нет");
    }

    public abstract String execute();
    @Override
    public String toString() {
        return syntax + " - " + helpText + "\n";
    }
}
