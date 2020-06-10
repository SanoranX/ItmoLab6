import java.util.stream.Stream;

public class RemoveById extends Command{

    public RemoveById() {

    }

    @Override
    public String execute() {
        try {
            if(routes.checkExistence(Long.parseLong(getArg().split(" ")[0]))) {
                routes.removeIf(x -> x.getId().equals(Long.parseLong(getArg().split(" ")[0])));
                routes.saveTemp();
                return "Объект был удалён";
            }
            else {
                return "Такого объекта в колекции нет";
            }
        }catch (NullPointerException e){
            return "Вы не ввели аргумент";
        }catch (NumberFormatException e){
            return "Скорее всего вы ввели аргумент не в том формате, попробуйте ещё раз";
        }
    }
}
