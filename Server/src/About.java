public class About extends Command {
    public About(Routes routes){
        super(routes);
    }

    public About() {

    }

    @Override
    public synchronized String execute() {
        return "Работа номер:9845\nХраним коллекцию: Routes\nВерсия: 1.1\nРазработчик:Рафаилов Илья\nГруппа: Р3110";
    }
}
