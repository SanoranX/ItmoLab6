import java.io.Serializable;

public class ClientRequest implements Serializable {
    String command;
    String argument;

    public String getArgument() {
        return argument;
    }

    public String getCommand() {
        return command;
    }

    public void setArgument(String argument) {
        this.argument = argument;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public ClientRequest(String command){
        this.command = command;
    }

    public ClientRequest(String command, String argument){
        this.command = command;
        this.argument = argument;
    }
}
