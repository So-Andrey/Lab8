package commands;

import commands.concreteCommand.*;
import java.util.HashMap;

public class Invoker {
    /** Поле, содержащее в себе введенные пользователем команду и её аргументы */
    private static String[] split;
    /** Коллекция, через которую осуществляется выполнение команд */
    private static final HashMap<String, Command> commandHashMap = new HashMap<>();
    static {
        commandHashMap.put("help", new HelpCommand());
        commandHashMap.put("info", new InfoCommand());
        commandHashMap.put("show", new ShowCommand());
        commandHashMap.put("add", new AddCommand());
        commandHashMap.put("update", new UpdateCommand());
        commandHashMap.put("remove_by_id", new RemoveByIdCommand());
        commandHashMap.put("clear", new ClearCommand());
        commandHashMap.put("execute_script", new ExecuteScriptCommand());
        commandHashMap.put("add_if_min", new AddIfMinCommand());
        commandHashMap.put("remove_greater", new RemoveGreaterCommand());
        commandHashMap.put("remove_lower", new RemoveLowerCommand());
        commandHashMap.put("max_by_head", new MaxByHeadCommand());
        commandHashMap.put("count_by_head", new CountByHeadCommand());
        commandHashMap.put("print_ascending", new PrintAscendingCommand());
    }
    public static String[] getSplit() {
        return split;
    }
    public static void setSplit(String[] split) {
        Invoker.split = split;
    }
    public static HashMap<String, Command> getCommandHashMap() {
        return commandHashMap;
    }
}