public class Exit extends Command{
    @Override
    public String execute() {
        System.exit(0);
        return "Выключено";
    }
}
