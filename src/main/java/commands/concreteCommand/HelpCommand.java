package commands.concreteCommand;

import commands.Command;
import commands.CommandArgsChecker;
import commands.Invoker;

public class HelpCommand implements Command {
    /** Метод, выводящий справку по командам
     * @see CommandArgsChecker#commandArgsChecker(int)
     * @see Invoker#getCommandHashMap() */
    @Override
    public void execute() {
        CommandArgsChecker.commandArgsChecker(0);
        Invoker.getCommandHashMap().values().forEach(command -> System.out.println(command.description()));
    }
    @Override
    public String description() {
        return "help : вывести справку по доступным командам";
    }
}