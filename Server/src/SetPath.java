public class SetPath extends Command{
    public SetPath(){super();}

    @Override
    public String execute() {
        routes.setPath(getArg());
        return "Путь был задан";
    }
}
