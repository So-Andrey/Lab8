package commands.concreteCommand;

import allForDragons.*;
import application.MyApplication;
import commands.Command;
import commands.CommandArgsChecker;
import commands.Invoker;
import database.DatabaseConnection;
import database.UserAuthentication;
import java.util.List;

public class RemoveGreaterCommand implements Command {
    /** Метод, удаляющий из коллекции всех драконов старше заданного
     * @param thisDragon заданный дракон
     * @see DragonsCollection#updateFromDB()
     * @see DatabaseConnection#executeStatement(String) */
    private void removerGreater(Dragon thisDragon) {
        List<Dragon> greaterDragons = DragonsCollection.getDragons().stream().filter(dragon -> dragon.getAge() > thisDragon.getAge()).toList();
        if (!greaterDragons.isEmpty()) {
            greaterDragons.forEach(dragon -> DatabaseConnection.executeStatement("delete from dragons where id = " + dragon.getId() + " and creator = '" + UserAuthentication.getCurrentUser() + "'"));
            DragonsCollection.updateFromDB();
        }
    }
    /** Метод, находящий заданного дракона в коллекции и вызывающий метод removerGreater
     * @see RemoveGreaterCommand#removerGreater(Dragon)
     * @see CommandArgsChecker#commandArgsChecker(int) */
    @Override
    public void execute() {
        CommandArgsChecker.commandArgsChecker(1);
        try {
            List<Dragon> matchedDragons = DragonsCollection.getDragons().stream().filter(dragon -> dragon.getId() == Long.parseLong(Invoker.getSplit()[1])).toList();
            if (!matchedDragons.isEmpty()) removerGreater(matchedDragons.get(0));
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
                List<Dragon> lowerDragons = DragonsCollection.getDragons().stream().filter(dragon -> dragon.getAge() > matchedDragons.get(0).getAge()).toList();
                if (lowerDragons.isEmpty()) {
                    return MyApplication.getAppLanguage().getString("no_greater");
                } else {
                    int beforeSize = DragonsCollection.getDragons().size();
                    lowerDragons.forEach(dragon -> DatabaseConnection.executeStatement("delete from dragons where id = " + dragon.getId() + " and creator = '" + UserAuthentication.getCurrentUser() + "'"));
                    DragonsCollection.updateFromDB();
                    return MyApplication.getAppLanguage().getString("amount") + ": " + (beforeSize - DragonsCollection.getDragons().size()) + "\n" + MyApplication.getAppLanguage().getString("delete_ps");
                }
            }
        } catch (NumberFormatException e) {
            return MyApplication.getAppLanguage().getString("invalid_inp");
        }
    }
    @Override
    public String description() {
        return "remove_greater {element} : удалить из коллекции все элементы, превышающие заданный";
    }
}