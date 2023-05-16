package commands.concreteCommand;

import commands.Command;
import allForDragons.*;
import commands.CommandArgsChecker;

public class ShowCommand implements Command {
    /** Метод, выводящий все элементы коллекции
     * @see CommandArgsChecker#commandArgsChecker(int)
     * @see Dragon#toString() */
    @Override
    public void execute() {
        CommandArgsChecker.commandArgsChecker(0);
        if (DragonsCollection.getDragons().isEmpty()) {
            System.out.println("Коллекция пуста");
        } else {
            DragonsCollection.getDragons().forEach(System.out::println);
        }
    }
    @Override
    public String description() {
        return "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }
}