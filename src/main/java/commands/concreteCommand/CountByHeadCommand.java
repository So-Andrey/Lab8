package commands.concreteCommand;

import allForDragons.DragonsCollection;
import application.MyApplication;
import commands.Command;
import commands.CommandArgsChecker;
import commands.Invoker;
import java.util.LinkedList;

public class CountByHeadCommand implements Command {
    /** Метод, выводящий количество драконов с заданным количеством глаз
     * @param eyesCount заданное количество глаз */
    private String getCountOfDragons(double eyesCount) {
        return MyApplication.getAppLanguage().getString("count_by_head") + ": " + DragonsCollection.getDragons().stream().filter(dragon -> dragon.getHead().getEyesCount() == eyesCount).count();
    }
    /** Метод, выводящий количество элементов, значение поля head которых равно заданному с помощью getCountOfDragons
     * @see CountByHeadCommand#getCountOfDragons(double)
     * @see CommandArgsChecker#commandArgsChecker(int) */
    @Override
    public void execute() {
        CommandArgsChecker.commandArgsChecker(1);
        if (DragonsCollection.getDragons().isEmpty()) {
            result.add(MyApplication.getAppLanguage().getString("count_by_head") + ": 0");
        } else {
            try {
                result.add(getCountOfDragons(Double.parseDouble(Invoker.getSplit()[1])));
            } catch (NumberFormatException e) {
                throw new NullPointerException();
            }
        }
    }
    private static final LinkedList<String> result = new LinkedList<>();
    public static LinkedList<String> getResult() {
        return result;
    }
    public static void resetResult() {
        result.clear();
    }
    public static boolean isResultEmpty() {
        return result.isEmpty();
    }
    @Override
    public String description() {
        return "count_by_head head : вывести количество элементов, значение поля head которых равно заданному";
    }
}
