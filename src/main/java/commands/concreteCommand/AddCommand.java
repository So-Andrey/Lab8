package commands.concreteCommand;

import application.TableController;
import commands.Command;
import allForDragons.*;
import commands.CommandArgsChecker;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

public class AddCommand implements Command {
    /**Метод, добавляющий в коллекцию нового дракона
     * @see CommandArgsChecker#commandArgsChecker(int)
     * @see DragonAdder#dragonAdder()
     * @see DragonAdder#dragonToAdderToDB(Dragon) */
    @Override
    public void execute() {
        CommandArgsChecker.commandArgsChecker(0);
        DragonAdder.dragonToAdderToDB(DragonAdder.dragonAdder());
    }
    /** Метод, выполняющий команду add из файла
     * @see DragonAdder#dragonFromFileAdder(Scanner)
     * @see DragonAdder#dragonToAdderToDB(Dragon) */
    protected static void adderFromFile(Scanner scanner) {
        try {
            Dragon dragon = DragonAdder.dragonFromFileAdder(scanner);
            TableController.addToAppear(Set.of(dragon));
            DragonAdder.dragonToAdderToDB(dragon);
        } catch (InputMismatchException ignored) {}
    }
    @Override
    public String description() {
        return "add {element} : добавить новый элемент в коллекцию";
    }
}
