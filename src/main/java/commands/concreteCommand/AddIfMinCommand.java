package commands.concreteCommand;

import allForDragons.*;
import commands.Command;
import commands.CommandArgsChecker;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AddIfMinCommand implements Command {
    /**Метод, добавляющий в коллекцию нового дракона, если его возраст меньше имеющихся в коллекции
     * @see DragonAdder#dragonAdder()
     * @see AddIfMinCommand#ifMinAdder(Dragon)
     * @see CommandArgsChecker#commandArgsChecker(int) */
    @Override
    public void execute() {
        CommandArgsChecker.commandArgsChecker(0);
        ifMinAdder(DragonAdder.dragonAdder());
    }
    /** Метод, выполняющий команду add_if_min из файла
     * @see DragonAdder#dragonFromFileAdder(Scanner)
     * @see AddIfMinCommand#ifMinAdder(Dragon)
     * @param scanner сканер из файла */
    protected static void adderIfMinFromFile(Scanner scanner) {
        try {
            ifMinAdder(DragonAdder.dragonFromFileAdder(scanner));
        } catch (InputMismatchException ignored) {}
    }
    /**Метод, проверяющий возраст дракона и добавляющий его в случае меньшего возраста, чем у существующих драконов
     * @param dragon добавляемый дракон
     * @see DragonAdder#dragonToAdderToDB(Dragon) */
    private static void ifMinAdder(Dragon dragon) {
        if (DragonsCollection.getDragons().isEmpty()) {
            DragonAdder.dragonToAdderToDB(dragon);
        } else {
            if (DragonsCollection.getDragons().stream().noneMatch((dragon1 -> dragon.getAge() >= dragon1.getAge()))) {
                DragonAdder.dragonToAdderToDB(dragon);
            } else {
                System.out.println("Новый элемент не добавлен, так как возраст заданного дракона слишком большой");
            }
        }
    }
    @Override
    public String description() {
        return "add_if_min {element} : добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции";
    }
}
