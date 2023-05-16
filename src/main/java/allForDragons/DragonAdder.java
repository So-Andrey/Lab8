package allForDragons;

import commands.Invoker;
import database.DatabaseConnection;
import exceptions.IllegalValueOfXException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class DragonAdder {
    /** Метод, добавляющий нового дракона в базу данных и в коллекцию
     * @see DatabaseConnection#executeStatement(String)
     * @see DragonsCollection#updateFromDB()
     * @param dragon добавляемый дракон */
    public static void dragonToAdderToDB(Dragon dragon) {
        DatabaseConnection.executeStatement("insert into dragons (id, creator, creationDate, name, age, color, type, character, eyesCount, x, y) values ('" + dragon.getId() + "', '" + dragon.getCreator() + "', '" + dragon.getCreationTime() + "', '" + dragon.getName() + "', '" + dragon.getAge() + "', '" + dragon.getColor() + "', '" + dragon.getType() + "', '" + dragon.getCharacter() + "', '" + dragon.getHead().getEyesCount() + "', '" + dragon.getCoordinates().getX() + "', '" + dragon.getCoordinates().getY() + "')");
        DragonsCollection.updateFromDB();
        System.out.println("Новый элемент коллекции добавлен");
    }
    /** Метод для создания дракона с помощью консоли
     * @return Dragon */
    public static Dragon dragonAdder() {
        Scanner sc = new Scanner(System.in);
        String name = "";
        long x = 0;
        float y = 0;
        Color dragonColor = null;
        long age = 0;
        DragonType dragonType = null;
        DragonCharacter dragonCharacter = null;
        double eyesCount = 0;
        int i = 1;
        while (i != 0) {
            try {
                while (i == 1) {
                    System.out.println("Введите имя дракона");
                    name = sc.nextLine();
                    if (name.trim().isEmpty() | name.contains("'")) {
                        throw new InputMismatchException();
                    }
                    ++i;
                }
                while (i == 2) {
                    try {
                        System.out.println("Введите координату X дракона");
                        String s = sc.nextLine();
                        x = Long.parseLong(s);
                        if (x > 610) {
                            throw new IllegalValueOfXException();
                        }
                        ++i;
                    } catch (IllegalValueOfXException illegalValueOfXException) {
                        System.out.println(illegalValueOfXException.getMessage());
                    } catch (NumberFormatException numberFormatException) {
                        throw new InputMismatchException();
                    }
                }
                while (i == 3) {
                    System.out.println("Введите координату Y дракона");
                    String s = sc.nextLine();
                    try {
                        Double.parseDouble(s);
                    } catch (NumberFormatException ex) {
                        throw new InputMismatchException();
                    }
                    y = Float.parseFloat(s);
                    ++i;
                }
                while (i == 4) {
                    System.out.println("Введите возраст дракона (должен быть больше 0)");
                    String s = sc.nextLine();
                    try {
                        age = Long.parseLong(s);
                        if (age <= 0) throw new InputMismatchException();
                    } catch (NumberFormatException numberFormatException) {
                        throw new InputMismatchException();
                    }
                    ++i;
                }
                while (i == 5) {
                    System.out.println("Введите цвет дракона (Цифру или полное название) 1 - GREEN, 2 - ORANGE, 3 - BROWN");
                    String color = sc.nextLine();
                    if (!(color.matches("[1-3]")||color.equals("GREEN")||color.equals("ORANGE")||color.equals("BROWN")||color.isEmpty())) {
                        throw new InputMismatchException();
                    }
                    dragonColor = Color.getColor(color);
                    ++i;
                }
                while (i == 6) {
                    System.out.println("Введите тип дракона (Цифру или полное название) 1 - WATER, 2 - UNDERGROUND, 3 - FIRE");
                    String type = sc.nextLine();
                    if (!(type.matches("[1-3]")||type.equals("WATER")||type.equals("UNDERGROUND")||type.equals("FIRE"))) {
                        throw new InputMismatchException();
                    }
                    dragonType = DragonType.getType(type);
                    ++i;
                }
                while (i == 7) {
                    System.out.println("Введите характер дракона (Цифру или полное название) 1 - CUNNING, 2 - WISE, 3 - CHAOTIC_EVIL, 4 - FICKLE");
                    String character = sc.nextLine();
                    if (!(character.matches("[1-4]")||character.equals("CUNNING")||character.equals("WISE")||character.equals("CHAOTIC_EVIL")||character.equals("FICKLE")||character.isEmpty())) {
                        throw new InputMismatchException();
                    }
                    dragonCharacter = DragonCharacter.getCharacter(character);
                    ++i;
                }
                while (i == 8) {
                    System.out.println("Введите количество глаз дракона");
                    String s = sc.nextLine();
                    try {
                        Double.parseDouble(s);
                    } catch (NumberFormatException numberFormatException){
                        throw new InputMismatchException();
                    }
                    eyesCount = Double.parseDouble(s);
                    ++i;
                }
                i = 0;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("Неверный тип данных");
            }
        }
        return new Dragon(name, new Coordinates(x, y), age, dragonColor, dragonType, dragonCharacter, new DragonHead(eyesCount));
    }
    /** Метод для добавления дракона из файла
     * @see DragonAdder#fieldsReader(Scanner)
     * @see DragonAdder#nameFromFileChecker(String)
     * @see DragonAdder#XCoordinateFromFileChecker(String)
     * @see DragonAdder#YCoordinateFromFileChecker(String)
     * @see DragonAdder#ageFromFileChecker(String)
     * @see DragonAdder#colorFromFileChecker(String)
     * @see DragonAdder#typeFromFileChecker(String)
     * @see DragonAdder#characterFromFileChecker(String)
     * @see DragonAdder#eyesCountFromFileChecker(String)
     * @return Dragon */
    public static Dragon dragonFromFileAdder(Scanner scanner) {
        if (Invoker.getSplit().length != 1) throw new InputMismatchException();
        String[] fields = fieldsReader(scanner);
        return new Dragon(nameFromFileChecker(fields[0]), new Coordinates(XCoordinateFromFileChecker(fields[1]), YCoordinateFromFileChecker(fields[2])), ageFromFileChecker(fields[3]), colorFromFileChecker(fields[4]), typeFromFileChecker(fields[5]), characterFromFileChecker(fields[6]), new DragonHead(eyesCountFromFileChecker(fields[7])));
    }
    /** Метод, считывающий поля дракона из файла
     * @return возвращает массив с полями нового дракона */
    private static String[] fieldsReader(Scanner sc) {
        String[] fields = new String[8];
        for (int i = 0; i < fields.length; ++i) {
            try {
                fields[i] = sc.nextLine();
                if (fields[i].trim().isEmpty()) fields[i] = null;
            } catch (NoSuchElementException noSuchElementException) {
                fields[i] = null;
            }
        }
        return fields;
    }
    /** Метод, проверяющий имя нового дракона
     * @return String name */
    private static String nameFromFileChecker(String name) {
        if (name.trim().isEmpty() | name.contains("'")) {
            throw new InputMismatchException();
        }
        return name;
    }
    /** Метод, проверяющий координату x нового дракона
     * @return long x */
    private static long XCoordinateFromFileChecker(String xString) {
        try {
            Long.parseLong(xString);
        } catch (NumberFormatException numberFormatException) {
            throw new InputMismatchException();
        }
        long x = Long.parseLong(xString);
        if (x > 610) {
            throw new InputMismatchException();
        }
        return x;
    }
    /** Метод, проверяющий координату y нового дракона
     * @return float y */
    private static float YCoordinateFromFileChecker(String yString) {
        try {
            Double.parseDouble(yString);
        } catch (NumberFormatException numberFormatException) {
            throw new InputMismatchException();
        }
        return Float.parseFloat(yString);
    }
    /** Метод, проверяющий возраст нового дракона
     * @return Long age */
    private static Long ageFromFileChecker(String ageString) {
        try {
            if (Long.parseLong(ageString) <= 0) throw new InputMismatchException();
        } catch (NumberFormatException numberFormatException) {
            throw new InputMismatchException();
        }
        return Long.parseLong(ageString);
    }
    /** Метод, проверяющий цвет нового дракона
     * @return Color */
    private static Color colorFromFileChecker(String color) {
        if (!(color.matches("[1-3]")||color.equals("GREEN")||color.equals("ORANGE")||color.equals("BROWN")||color.isEmpty())) {
            throw new InputMismatchException();
        }
        return Color.getColor(color);
    }
    /** Метод, проверяющий тип нового дракона
     * @return Type */
    private static DragonType typeFromFileChecker(String type) {
        if (!(type.matches("[1-3]")||type.equals("WATER")||type.equals("UNDERGROUND")||type.equals("FIRE"))) {
            throw new InputMismatchException();
        }
        return DragonType.getType(type);
    }
    /** Метод, проверяющий характер нового дракона
     * @return DragonCharacter */
    private static DragonCharacter characterFromFileChecker(String character) {
        if (!(character.matches("[1-4]")||character.equals("CUNNING")||character.equals("WISE")||character.equals("CHAOTIC_EVIL")||character.equals("FICKLE")||character.isEmpty())) {
            throw new InputMismatchException();
        }
        return DragonCharacter.getCharacter(character);
    }
    /** Метод, проверяющий количество глаз нового дракона
     * @return Double eyesCount */
    private static Double eyesCountFromFileChecker(String eyesCountString) {
        try {
            Double.parseDouble(eyesCountString);
        } catch (NumberFormatException numberFormatException){
            throw new InputMismatchException();
        }
        return Double.parseDouble(eyesCountString);
    }
}