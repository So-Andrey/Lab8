package commands.concreteCommand;

import allForDragons.DragonsCollection;
import commands.Command;
import commands.CommandArgsChecker;

public class MaxByHeadCommand implements Command {
    /** Метод, выводящий дракона с максимальным значением head */
    private void maxByHeadPrinter() {
        System.out.println(DragonsCollection.getDragons().stream().max((dragon1, dragon2) -> (int)Math.signum(dragon1.getHead().getEyesCount() - dragon2.getHead().getEyesCount())).get());
    }
    /** Метод, выполняющий команду с помощью maxByHeadPrinter
     * @see MaxByHeadCommand#maxByHeadPrinter()
     * @see CommandArgsChecker#commandArgsChecker(int) */
    @Override
    public void execute() {
        CommandArgsChecker.commandArgsChecker(0);
        if (DragonsCollection.getDragons().size() != 0) {
            maxByHeadPrinter();
        } else {
            System.out.println("Коллекция пуста");
        }
    }
    @Override
    public String description() {
        return "max_by_head : вывести любой объект из коллекции, значение поля head которого является максимальным";
    }
}