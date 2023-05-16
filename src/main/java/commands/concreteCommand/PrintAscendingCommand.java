package commands.concreteCommand;

import allForDragons.DragonsCollection;
import commands.Command;
import commands.CommandArgsChecker;

public class PrintAscendingCommand implements Command {
    /** Метод, выводящий драконов в порядке возрастания значения возраста */
    private void ascendingPrinter() {
        DragonsCollection.getDragons().stream().sorted().forEachOrdered(System.out::println);
    }
    /** Метод, выполняющий команду с помощью ascendingPrinter
     * @see PrintAscendingCommand#ascendingPrinter()
     * @see CommandArgsChecker#commandArgsChecker(int) */
    @Override
    public void execute() {
        CommandArgsChecker.commandArgsChecker(0);
        if (DragonsCollection.getDragons().isEmpty()) {
            System.out.println("Коллекция пуста");
        } else {
            ascendingPrinter();
        }
    }
    @Override
    public String description() {
        return "print_ascending : вывести элементы коллекции в порядке возрастания";
    }
}