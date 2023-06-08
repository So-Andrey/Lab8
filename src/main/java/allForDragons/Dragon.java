package allForDragons;

import application.MyApplication;
import database.DatabaseConnection;
import database.UserAuthentication;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Dragon implements Comparable<Dragon>{
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long age; //Значение поля должно быть больше 0, Поле не может быть null
    private Color color; //Поле может быть null
    private DragonType type; //Поле не может быть null
    private DragonCharacter character; //Поле может быть null
    private DragonHead head;
    private final String creator;
    /** Конструктор для создания нового дракона из консоли */
    public Dragon(String name, Coordinates coordinates, Long age, Color color, DragonType type, DragonCharacter character, DragonHead head) {
        this.id = setId();
        creationDate = new Date();
        this.name = name;
        this.age = age;
        this.coordinates = coordinates;
        this.character = character;
        this.head = head;
        this.type = type;
        this.color = color;
        creator = UserAuthentication.getCurrentUser();
    }
    /** Конструктор для создания нового дракона из базы данных */
    public Dragon(String name, Coordinates coordinates, Long age, Color color, DragonType type, DragonCharacter character, DragonHead head, String creator, Date creationDate, Long id) {
        this.id = id;
        this.creationDate = creationDate;
        this.name = name;
        this.age = age;
        this.coordinates = coordinates;
        this.character = character;
        this.head = head;
        this.type = type;
        this.color = color;
        this.creator = creator;
    }
    /** Метод для генерации id дракона с использованием средств базы данных */
    private long setId() {
        long newId = 0;
        ResultSet resultSet = DatabaseConnection.executePreparedStatement("SELECT nextval(?)", "id");
        try {
            resultSet.next();
            newId = resultSet.getLong(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return newId;
    }
    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Coordinates getCoordinates() {
        return coordinates;
    }
    public long getX() {
        return coordinates.getX();
    }
    public float getY() {
        return coordinates.getY();
    }
    public Long getAge() {
        return age;
    }
    public Color getColor() {
        return color;
    }
    public DragonType getType() {
        return type;
    }
    public DragonCharacter getCharacter() {
        return character;
    }
    public long getCreationTime() {
        return creationDate.getTime();
    }
    public String getCreationDate() {
        return new SimpleDateFormat(MyApplication.getAppLanguage().getString("date_format")).format(creationDate);
    }
    public DragonHead getHead() {
        return head;
    }
    public double getEyesCount() {
        return head.getEyesCount();
    }
    public String getCreator() {
        return creator;
    }
    @Override
    public String toString() {
        return  "ID: " + id + "\n" +
                MyApplication.getAppLanguage().getString("name") + ": " + name + "\n" +
                MyApplication.getAppLanguage().getString("age") + ": " + age + "\n" +
                MyApplication.getAppLanguage().getString("type") + ": " + type + "\n" +
                MyApplication.getAppLanguage().getString("color") + ": " + color + "\n" +
                MyApplication.getAppLanguage().getString("character") + ": " + character + "\n" +
                MyApplication.getAppLanguage().getString("eyes_count") + ": " + head.getEyesCount() + "\n" +
                MyApplication.getAppLanguage().getString("coordinates") + ": (" + coordinates.getX() + "; " + coordinates.getY() + ")\n" +
                MyApplication.getAppLanguage().getString("creator") + ": " + creator + "\n" +
                MyApplication.getAppLanguage().getString("creation_date") + ": " + getCreationDate();
    }
    /** Переопределение метода compareTo для сравнения драконов (по возрасту) */
    @Override
    public int compareTo(Dragon dragon) {
        return (int)Math.signum(this.age - dragon.getAge());
    }
}