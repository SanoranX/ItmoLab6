public class Save extends Command{
    @Override
    public String execute() {
        routes.save();
        return "Коллекция была сохранена.";
    }
}
