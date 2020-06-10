public class RemoveGreater extends Command{

    @Override
    public String execute() {
        routes.stream().filter(x -> x.getId() > Long.parseLong(getArg())).forEach(x -> routes.remove(x));
        routes.saveTemp();
        return "Команда была выполнена";
    }
}
