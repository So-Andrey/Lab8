package commands.concreteCommand;

import allForDragons.*;
import commands.Command;
import commands.CommandArgsChecker;
import commands.Invoker;
import java.util.Scanner;

public class UpdateCommand implements Command {
    /** Метод, исполняющий команду update
     * @see DragonUpdater#updateDragon(long)
     * @see CommandArgsChecker#commandArgsChecker(int) */
    @Override
    public void execute() {
        CommandArgsChecker.commandArgsChecker(1);
        try {
            if (!DragonsCollection.getDragons().isEmpty()) {
                DragonUpdater.updateDragon(Long.parseLong(Invoker.getSplit()[1]));
            } else {
                System.out.println("Такого дракона не существует");
            }
        } catch (NumberFormatException ex) {
            throw new NullPointerException();
        }
    }
    /** Метод, обновляющий дракона параметрами из файла
     * @see DragonUpdater#updaterFromFile(Scanner)
     * @param scanner сканер из файла для получения новых параметров */
    protected static void updaterFromFile(Scanner scanner) {
        DragonUpdater.updaterFromFile(scanner);
    }
    @Override
    public String description() {
        return "update id : обновить значение элемента коллекции, id которого равен заданному";
    }
}