package commands.concreteCommand;

import allForDragons.DragonsCollection;
import commands.Command;
import commands.CommandArgsChecker;
import commands.Invoker;

public class CountByHeadCommand implements Command {
    /** Метод, выводящий количество драконов с заданным количеством глаз
     * @param eyesCount заданное количество глаз */
    private void getCountOfDragons(double eyesCount) {
        System.out.println("Количество драконов с заданным количеством глаз: " + DragonsCollection.getDragons().stream().filter(dragon -> dragon.getHead().getEyesCount() == eyesCount).count());
    }
    /** Метод, выводящий количество элементов, значение поля head которых равно заданному с помощью getCountOfDragons
     * @see CountByHeadCommand#getCountOfDragons(double)
     * @see CommandArgsChecker#commandArgsChecker(int) */
    @Override
    public void execute() {
        CommandArgsChecker.commandArgsChecker(1);
        if (DragonsCollection.getDragons().isEmpty()) {
            System.out.println("Коллекция пуста");
        } else {
            try {
                getCountOfDragons(Double.parseDouble(Invoker.getSplit()[1]));
            } catch (NumberFormatException e) {
                throw new NullPointerException();
            }
        }
    }
    @Override
    public String description() {
        return "count_by_head head : вывести количество элементов, значение поля head которых равно заданному";
    }
}
