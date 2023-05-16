package commands.concreteCommand;

import allForDragons.DragonsCollection;
import commands.Command;
import commands.CommandArgsChecker;
import database.DatabaseConnection;
import database.UserAuthentication;

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
        System.out.println("Созданная Вами часть коллекции очищена");
    }
    @Override
    public String description() {
        return "clear : очистить коллекцию";
    }
}
