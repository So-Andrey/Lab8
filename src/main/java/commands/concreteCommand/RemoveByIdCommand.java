package commands.concreteCommand;

import allForDragons.Dragon;
import allForDragons.DragonsCollection;
import commands.Command;
import commands.CommandArgsChecker;
import commands.Invoker;
import database.DatabaseConnection;
import database.UserAuthentication;
import java.util.List;

public class RemoveByIdCommand implements Command {
    /** Метод, удаляющий дракона по значению id
     * @param id дракона, которого надо удалить
     * @see DatabaseConnection#executeStatement(String)
     * @see DragonsCollection#updateFromDB() */
    private void removerById(long id) {
        List<Dragon> matchedDragon = DragonsCollection.getDragons().stream().filter((dragon -> dragon.getId() == id)).toList();
        if (matchedDragon.isEmpty()) {
            System.out.println("Такого дракона не существует");
        } else {
            int beforeSize = DragonsCollection.getDragons().size();
            DatabaseConnection.executeStatement("delete from dragons where id = " + matchedDragon.get(0).getId() + " and creator = '" + UserAuthentication.getCurrentUser() + "'");
            DragonsCollection.updateFromDB();
            if (beforeSize == DragonsCollection.getDragons().size()) {
                System.out.println("Вы не можете удалить этого дракона, так как он создан другим пользователем");
            } else {
                System.out.println("Дракон успешно удалён");
            }
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
    @Override
    public String description() {
        return "remove_by_id id : удалить элемент из коллекции по его id";
    }
}