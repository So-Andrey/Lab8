package allForDragons;

import application.MyApplication;
import database.DatabaseConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DragonsCollection {
    /** Статическая коллекция драконов, с которой работают команды в программе */
    private static final LinkedHashSet<Dragon> dragons = new LinkedHashSet<>();
    /** Поле, содержащее дату инициализации коллекции */
    private static final Date dateOfInitialization = new Date();
    public static LinkedHashSet<Dragon> getDragons() {
        return dragons;
    }
    /** Метод, выводящий информацию о коллекции */
    public static String getInfo() {
        return MyApplication.getAppLanguage().getString("coll_type") + ": " + dragons.getClass().getTypeName().split(".util.")[1] + "\n" +
                MyApplication.getAppLanguage().getString("coll_date") + ": " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z").format(dateOfInitialization) + "\n" +
                MyApplication.getAppLanguage().getString("coll_amount") + ": " + dragons.size() + "\n";
    }
    /** Метод для добавления драконов из базы данных */
    public static void putDragonsFromDB() {
        try {
            ResultSet resultSet = DatabaseConnection.executePreparedStatement("SELECT * FROM DRAGONS");
            while (resultSet.next()) {
                try {
                    long id = resultSet.getLong(1);
                    if (id <= 0) throw new IllegalArgumentException();
                    String creator = resultSet.getString(2);
                    if (creator.contains("'")) throw new IllegalArgumentException();
                    Date creationDate = new Date(resultSet.getLong(3));
                    String name = resultSet.getString(4);
                    if (name.contains("'")) throw new IllegalArgumentException();
                    long age = resultSet.getLong(5);
                    if (age <= 0) throw new IllegalArgumentException();
                    String colorString = resultSet.getString(6);
                    Color color;
                    if (colorString.equals("null")) {
                        color = null;
                    } else {
                        color = Color.valueOf(colorString);
                    }
                    DragonType type = DragonType.valueOf(resultSet.getString(7));
                    String characterString = resultSet.getString(8);
                    DragonCharacter character;
                    if (characterString.equals("null")) {
                        character = null;
                    } else {
                        character = DragonCharacter.valueOf(characterString);
                    }
                    double eyesCount = resultSet.getDouble(9);
                    long x = resultSet.getLong(10);
                    if (x > 610) throw new IllegalArgumentException();
                    float y = resultSet.getFloat(11);
                    DragonsCollection.getDragons().add(new Dragon(name, new Coordinates(x, y), age, color, type, character, new DragonHead(eyesCount), creator, creationDate, id));
                } catch (SQLException | NullPointerException | IllegalArgumentException exception) {
                    DatabaseConnection.executeStatement("delete from dragons where id = " + resultSet.getLong(1));
                }
            }
        } catch (SQLException ignored) {}
    }
    /** Метод для обновления данных в коллекции */
    public static void updateFromDB() {
        dragons.clear();
        putDragonsFromDB();
    }
}