package commands.concreteCommand;

import commands.Command;
import commands.CommandArgsChecker;
import commands.Invoker;

public class ExitCommand implements Command {
    /** Метод, завершающий работу программы
     * @see CommandArgsChecker#commandArgsChecker(int)
     * @see Invoker#setProgramRunning(boolean) */
    @Override
    public void execute() {
        CommandArgsChecker.commandArgsChecker(0);
        Invoker.setProgramRunning(false);
    }
    @Override
    public String description() {
        return "exit : завершить программу (без сохранения в файл)";
    }
}