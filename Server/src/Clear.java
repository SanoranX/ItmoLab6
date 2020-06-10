public class Clear extends Command{
    @Override
    public String execute() {
        routes.clear();
        routes.saveTemp();
        return "Очистка произведена";
    }
}
