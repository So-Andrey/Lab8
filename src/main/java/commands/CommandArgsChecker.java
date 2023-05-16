package commands;

public class CommandArgsChecker {
    /** Метод для проверки количества аргументов команды
     * @param amountOfArgs количество аргументов команды */
    public static void commandArgsChecker(int amountOfArgs) {
        if (Invoker.getSplit().length - 1 != amountOfArgs) {
            throw new NullPointerException();
        }
    }
}
