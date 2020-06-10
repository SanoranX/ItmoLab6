import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.IOException;
import java.util.InputMismatchException;


public class Validation {

    private final static String NO_ARG = "Аргумент не был введён";
    private final static String ARG_WRONG_TYPE = "Аргумент введён неверно";
    private final static String TOO_MANY_ARG = "Слишком много аргументов";
    private final static String ARG_OK = "Команда верна, отправляем на сервер";
    private final static String NOT_ENOUGH_ARG = "Недостаточно аргументов для команды";
    //TODO: Добавить в валидацию update_id (тут всё немного сложнее)
    public static boolean validate(String command) {
        switch (command.split(" ")[0]) {
            case "add":
                try {
                    if (command.split(" ")[1].split(",").length == 8) {
                        System.out.println(ARG_OK);
                        return true;
                    }
                    else if(command.split(" ")[1].split(",").length > 8){
                        System.out.println(TOO_MANY_ARG);
                    }
                    else if(command.split(" ")[1].split(",").length < 8){
                        System.out.println(NOT_ENOUGH_ARG);
                    }
                    return false;
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(NO_ARG);
                    return false;
                }
            case "count_greater_than_distance":
                try {
                    Float.parseFloat(command.split(" ")[1]);
                    return true;
                } catch (NumberFormatException e) {
                    System.out.println(ARG_WRONG_TYPE);
                    return false;
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(NO_ARG);
                    return false;
                }
            case "remove_by_id":
                try {
                    Long.parseLong(command.split(" ")[1]);
                } catch (NumberFormatException e) {
                    System.out.println(ARG_WRONG_TYPE);
                    return false;
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(NO_ARG);
                    return false;
                }
            case "remove_greater":
                try {
                    Long.parseLong(command.split(" ")[1]);
                } catch (NumberFormatException e) {
                    System.out.println(ARG_WRONG_TYPE);
                    return false;
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(NO_ARG);
                    return false;
                }
            case "update_id":
                return true;
            case "execute_script":
                try {
                    String temp = command.split(" ")[1];
                    return true;
                }catch (ArrayIndexOutOfBoundsException e){
                    System.out.println(NO_ARG);
                    return false;
                }
            default:
                return true;
        }
    }
}

