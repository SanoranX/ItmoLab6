public class Read extends Command{
    public Read(){
        super();
    }

    @Override
    public String execute() {
        routes.read(getArg());
        return "Было прочитано";
    }
}
