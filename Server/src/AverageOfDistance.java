public class AverageOfDistance extends Command{

    public AverageOfDistance() {
        super();
    }

    @Override
    public String execute() {
        final Float[] result = {Float.parseFloat("0")};
        routes.stream().forEach(x -> { result[0] = result[0] + x.getDistance(); });
        return String.valueOf((result[0]/routes.size()));
    }
}
