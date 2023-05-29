package commands.concreteCommand;

import allForDragons.*;
import commands.Command;
import commands.CommandArgsChecker;
import commands.Invoker;
import database.DatabaseConnection;
import database.UserAuthentication;
import java.util.List;

public class RemoveLowerCommand implements Command {
    /** Метод, удаляющий из коллекции всех драконов младше заданного
     * @param thisDragon заданный дракон
     * @see DragonsCollection#updateFromDB()
     * @see DatabaseConnection#executeStatement(String) */
    private void removerLower(Dragon thisDragon) {
        List<Dragon> lowerDragons = DragonsCollection.getDragons().stream().filter(dragon -> dragon.getAge() < thisDragon.getAge()).toList();
        if (!lowerDragons.isEmpty()) {
            lowerDragons.forEach(dragon -> DatabaseConnection.executeStatement("delete from dragons where id = " + dragon.getId() + " and creator = '" + UserAuthentication.getCurrentUser() + "'"));
            DragonsCollection.updateFromDB();
        }
    }
    /** Метод, находящий заданного дракона в коллекции и вызывающий метод removerLower
     * @see RemoveLowerCommand#removerLower(Dragon)
     * @see CommandArgsChecker#commandArgsChecker(int) */
    @Override
    public void execute() {
        CommandArgsChecker.commandArgsChecker(1);
        try {
            List<Dragon> matchedDragons = DragonsCollection.getDragons().stream().filter(dragon -> dragon.getId() == Long.parseLong(Invoker.getSplit()[1])).toList();
            if (!matchedDragons.isEmpty()) removerLower(matchedDragons.get(0));
        } catch (NumberFormatException ex) {
            throw new NullPointerException();
        }
    }
    public String executeFromGUI(String id) {
        try {
            Long.parseLong(id);
            List<Dragon> matchedDragons = DragonsCollection.getDragons().stream().filter(dragon -> dragon.getId() == Long.parseLong(id)).toList();
            if (matchedDragons.isEmpty()) {
                return "There is no such dragon";
            } else {
                List<Dragon> lowerDragons = DragonsCollection.getDragons().stream().filter(dragon -> dragon.getAge() < matchedDragons.get(0).getAge()).toList();
                if (lowerDragons.isEmpty()) {
                    return "There is no lower dragons";
                } else {
                    int beforeSize = DragonsCollection.getDragons().size();
                    lowerDragons.forEach(dragon -> DatabaseConnection.executeStatement("delete from dragons where id = " + dragon.getId() + " and creator = '" + UserAuthentication.getCurrentUser() + "'"));
                    DragonsCollection.updateFromDB();
                    return "Amount of deleted dragons: " + (beforeSize - DragonsCollection.getDragons().size()) + "\nP.S. (You can only delete dragons you create)";
                }
            }
        } catch (NumberFormatException e) {
            return "Invalid input";
        }
    }
    @Override
    public String description() {
        return "remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный";
    }
}