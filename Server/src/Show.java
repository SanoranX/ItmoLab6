import java.util.Arrays;

public class Show extends Command{
    public Show(Routes routes){
        super(routes);
    }

    public Show() {

    }
    @Override
    public String execute() {
       /* if (routes.size() == 0)
            return "Пустая коллекция";
        else {
            Object[] out = routes.toArray();
            StringBuilder builder = new StringBuilder();
            for (Object o : out) {
                builder.append(o).append("\n");
            }
            return builder.toString();
        }*/
        if(routes.size() == 0)
            return "Коллекция пуста";
        else {
            final String[] result = new String[1];
            routes.stream().forEach(x -> {
                result[0] = result[0] + x.toString();
            });
            return result[0];
        }
    }
}
