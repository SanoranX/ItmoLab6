public class HistoryCommand extends Command{
    @Override
    public String execute() {
        return "История команд:\n" + Command.history.getStory();
    }
}
