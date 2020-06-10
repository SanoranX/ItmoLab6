import java.util.Arrays;

public class Help extends Command{

    public Help() {
        super();
    }

    @Override
    public String execute() {
        final String[] result = {""};
        commands.stream().forEach(x -> {
            result[0] = result[0] + x.toString();
        });
        return result[0];
    }
}
