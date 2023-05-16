package commands.concreteCommand;

import allForDragons.DragonsCollection;
import commands.Command;
import commands.CommandArgsChecker;

public class InfoCommand implements Command {
    /** Метод, выводящий информацию о коллекции
     * @see CommandArgsChecker#commandArgsChecker(int)
     * @see DragonsCollection#getInfo() */
    @Override
    public void execute() {
        CommandArgsChecker.commandArgsChecker(0);
        DragonsCollection.getInfo();
    }
    @Override
    public String description() {
        return "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
    }
}