public class CountDistance extends Command {
    @Override
    public String execute() {
        long count =
        routes.stream().filter(x -> x.getDistance() > Float.parseFloat(getArg())).count();
        return String.valueOf(count);
    }
}
