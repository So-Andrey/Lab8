import application.MyApplication;
import database.DatabaseConnection;

public class Main {
    public static void main(String[] args) {
        DatabaseConnection.createTablesIfNotExist();
        MyApplication.open();
    }
}