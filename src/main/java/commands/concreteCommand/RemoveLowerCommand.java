package commands.concreteCommand;

import allForDragons.*;
import application.MyApplication;
import application.TableController;
import commands.Command;
import commands.CommandArgsChecker;
import commands.Invoker;
import database.DatabaseConnection;
import database.UserAuthentication;
import java.util.List;
import java.util.stream.Collectors;

public class RemoveLowerCommand implements Command {
    /** Метод, удаляющий из коллекции всех драконов младше заданного
     * @param thisDragon заданный дракон
     * @see DragonsCollection#updateFromDB()
     * @see DatabaseConnection#executeStatement(String) */
    private void removerLower(Dragon thisDragon) {
        List<Dragon> lowerDragons = DragonsCollection.getDragons().stream().filter(dragon -> dragon.getAge() < thisDragon.getAge()).toList();
        if (!lowerDragons.isEmpty()) {
            lowerDragons.forEach(dragon -> DatabaseConnection.executeStatement("delete from dragons where id = " + dragon.getId() + " and creator = '" + UserAuthentication.getCurrentUser() + "'"));
            TableController.addToDisappear(lowerDragons.stream().filter(dragon -> dragon.getCreator().equals(UserAuthentication.getCurrentUser())).collect(Collectors.toSet()));
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
                return MyApplication.getAppLanguage().getString("no_dragon");
            } else {
                List<Dragon> lowerDragons = DragonsCollection.getDragons().stream().filter(dragon -> dragon.getAge() < matchedDragons.get(0).getAge()).toList();
                if (lowerDragons.isEmpty()) {
                    return MyApplication.getAppLanguage().getString("no_lower");
                } else {
                    int beforeSize = DragonsCollection.getDragons().size();
                    lowerDragons.forEach(dragon -> DatabaseConnection.executeStatement("delete from dragons where id = " + dragon.getId() + " and creator = '" + UserAuthentication.getCurrentUser() + "'"));
                    DragonsCollection.updateFromDB();
                    TableController.addToDisappear(lowerDragons.stream().filter(dragon -> dragon.getCreator().equals(UserAuthentication.getCurrentUser())).collect(Collectors.toSet()));
                    return MyApplication.getAppLanguage().getString("amount") + ": " + (beforeSize - DragonsCollection.getDragons().size()) + "\n" + MyApplication.getAppLanguage().getString("delete_ps");
                }
            }
        } catch (NumberFormatException e) {
            return MyApplication.getAppLanguage().getString("invalid_inp");
        }
    }
    @Override
    public String description() {
        return "remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный";
    }
}