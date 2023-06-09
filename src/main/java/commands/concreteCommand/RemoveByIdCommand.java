package commands.concreteCommand;

import allForDragons.Dragon;
import allForDragons.DragonsCollection;
import application.MyApplication;
import application.TableController;
import commands.Command;
import commands.CommandArgsChecker;
import commands.Invoker;
import database.DatabaseConnection;
import database.UserAuthentication;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RemoveByIdCommand implements Command {
    /** Метод, удаляющий дракона по значению id
     * @param id дракона, которого надо удалить
     * @see DatabaseConnection#executeStatement(String)
     * @see DragonsCollection#updateFromDB() */
    private void removerById(long id) {
        List<Dragon> matchedDragon = DragonsCollection.getDragons().stream().filter((dragon -> dragon.getId() == id)).toList();
        if (!matchedDragon.isEmpty()) {
            DatabaseConnection.executeStatement("delete from dragons where id = " + matchedDragon.get(0).getId() + " and creator = '" + UserAuthentication.getCurrentUser() + "'");
            DragonsCollection.updateFromDB();
            TableController.addToDisappear(matchedDragon.stream().filter(dragon -> dragon.getCreator().equals(UserAuthentication.getCurrentUser())).collect(Collectors.toSet()));
        }
    }
    /** Выполняет команду с помощью removerById
     * @see RemoveByIdCommand#removerById(long)
     * @see CommandArgsChecker#commandArgsChecker(int) */
    @Override
    public void execute() {
        CommandArgsChecker.commandArgsChecker(1);
        try {
            removerById(Long.parseLong(Invoker.getSplit()[1]));
        } catch (NumberFormatException ex) {
            throw new NullPointerException();
        }
    }
    public String executeFromGUI(String id) {
        try {
            Long.parseLong(id);
            List<Dragon> matchedDragon = DragonsCollection.getDragons().stream().filter((dragon -> dragon.getId() == Long.parseLong(id))).toList();
            if (matchedDragon.isEmpty()) {
                return MyApplication.getAppLanguage().getString("no_dragon");
            } else {
                int beforeSize = DragonsCollection.getDragons().size();
                DatabaseConnection.executeStatement("delete from dragons where id = " + matchedDragon.get(0).getId() + " and creator = '" + UserAuthentication.getCurrentUser() + "'");
                DragonsCollection.updateFromDB();
                if (beforeSize == DragonsCollection.getDragons().size()) {
                    return MyApplication.getAppLanguage().getString("not_your");
                } else {
                    TableController.addToDisappear(Set.of(matchedDragon.get(0)));
                    return null;
                }
            }
        } catch (NumberFormatException e) {
            return MyApplication.getAppLanguage().getString("invalid_inp");
        }
    }
    @Override
    public String description() {
        return "remove_by_id id : удалить элемент из коллекции по его id";
    }
}