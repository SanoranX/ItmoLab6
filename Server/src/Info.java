public class Info extends Command {

    public Info(Routes routes){
        super(routes);
    }

    public Info() {
        super();
    }


    @Override
    public String execute() {
        return ("Коллекция: Routes\nКол-во элементов: " + routes.size());
    }
}
