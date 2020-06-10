public class UpdateId extends Command{
    @Override
    public String execute() {
        try {
            if(routes.checkExistence(Long.parseLong(getArg().split(" ")[0]))) {
                routes.removeIf(x -> x.getId().equals(Long.parseLong(getArg().split(" ")[0])));
                routes.add(new Route(getArg().split(" ")[1]));
                routes.saveTemp();
                return "Объект был обновлен.";
            }
            else {
                return "Такого объекта в колекции нет";
            }
        }catch (NullPointerException e){
            return "Вы не ввели аргумент";
        }
    }
}
