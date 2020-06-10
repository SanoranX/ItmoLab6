import java.util.Scanner;

public class InputManager implements Runnable{
    Routes routes;
    Scanner scanner = new Scanner(System.in);

    public InputManager(Routes routes){
        this.routes = routes;
        init();
    }

    //Внизу хороший, красивый, рабочий код :)
    @Override
    public void run() {
        while(true) {
            System.out.print("# ");
            String input = scanner.nextLine();
            try{
                Command execute = Command.getCommand(input.substring(0,input.indexOf(' ')));
                execute.setArg(input.substring(input.indexOf(' ')+1));
                Command.commands.stream().filter(x -> x.syntax.equals(input.substring(0,input.indexOf(' ')))).forEach(x -> System.out.println(x.execute()));
            }catch (NoSuchFieldException e){
                System.out.println("Такой команды нет");
            }catch (StringIndexOutOfBoundsException e){
                try {
                    Command execute = Command.getCommand(input);
                    System.out.println(execute.execute());
                } catch (NoSuchFieldException x) {
                    System.out.println("Такой команды нет");
                }
            }
        }
    }

    public void init(){
        Command.clientInitCommand("help", "Показать лист возможных команд", new Help(), routes);
        Command.clientInitCommand("about", "Показать информацию о программе и разработчике", new About(), routes);
        Command.clientInitCommand("add", "Добавляет новый объект в коллекцию.Аргументы вводятся через запятую", new Add(), routes);
        Command.clientInitCommand("info", "Показывает информацию о всех объектах, хранящихся в коллекции", new Info(), routes);
        Command.clientInitCommand("clear", "Полностью очищает коллекцию", new Clear(), routes);
        Command.clientInitCommand("show", "Выводит все элементы коллекции", new Show(), routes);
        Command.clientInitCommand("remove_by_id", "Удаляет элемент из коллекции", new RemoveById(), routes);
        Command.clientInitCommand("update_by_id", "Обновляет объект в коллекции", new UpdateId(), routes);
        Command.clientInitCommand("remove_greater_than", "Удаляет все объекты, id которых выше, чем заданный в аргументе", new RemoveGreater(), routes);
        Command.clientInitCommand("average_of_distance", "Показывает среднее значение поля Distance всех элементов коллекции", new AverageOfDistance(), routes);
        Command.clientInitCommand("exit", "Закрывает сервер без сохранения", new Exit(), routes);
        Command.clientInitCommand("set_path", "Изменяет путь к файлу", new SetPath(), routes);
        Command.clientInitCommand("read", "Насильно читает из файла", new Read(), routes);
        Command.clientInitCommand("history", "Выводит последние команды", new HistoryCommand(), routes);
        Command.clientInitCommand("save", "Сохранить коллекцию в файл", new Save(), routes);
        Command.clientInitCommand("count_greater_than_distance", "Показывает кол-во объектов, значение поля Distance которых больше, чем аргумент", new CountDistance(), routes);
    }
}
