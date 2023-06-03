package commands.concreteCommand;

import allForDragons.DragonsCollection;
import application.TableController;
import commands.Command;
import commands.CommandArgsChecker;
import database.DatabaseConnection;
import database.UserAuthentication;
import java.util.stream.Collectors;

public class ClearCommand implements Command {
    /**Метод, очищающий коллекцию
     * @see CommandArgsChecker#commandArgsChecker(int)
     * @see DatabaseConnection#executeStatement(String)
     * @see DragonsCollection#updateFromDB() */
    @Override
    public void execute() {
        CommandArgsChecker.commandArgsChecker(0);
        DatabaseConnection.executeStatement("delete from dragons where creator = '" + UserAuthentication.getCurrentUser() + "'");
        DragonsCollection.updateFromDB();
        TableController.addToDisappear(DragonsCollection.getDragons().stream().filter(dragon -> dragon.getCreator().equals(UserAuthentication.getCurrentUser())).collect(Collectors.toSet()));
    }
    public void executeFromGUI() {
        TableController.addToDisappear(DragonsCollection.getDragons().stream().filter(dragon -> dragon.getCreator().equals(UserAuthentication.getCurrentUser())).collect(Collectors.toSet()));
        DatabaseConnection.executeStatement("delete from dragons where creator = '" + UserAuthentication.getCurrentUser() + "'");
        DragonsCollection.updateFromDB();
    }
    @Override
    public String description() {
        return "clear : очистить коллекцию";
    }
}
