import java.util.Arrays;
import java.util.stream.Stream;

public class Add extends Command{

    public Add() {
        super();
    }

    @Override
    public String execute() {
        try {
            System.out.println("Аргумент в команде add " + getArg());
            //System.out.println(arg);
            if(!routes.checkExistence(Long.parseLong(getArg().split(",")[0]))) {
                routes.add(new Route(getArg()));
                routes.saveTemp();
                return "Добавлено";
            }
            else
                return "Невозможно добавить, так как объект с этим айди уже существует";

        }catch (IndexOutOfBoundsException e){
            e.printStackTrace();
            return "Аргумент не был введён";
        }catch (NumberFormatException e){
            return "Один из ваших аргументов введён неверно.";
        }
    }
}
