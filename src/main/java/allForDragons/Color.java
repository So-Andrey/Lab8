package allForDragons;

public enum Color {
    GREEN("GREEN" ,"1"),
    ORANGE("ORANGE","2"),
    BROWN("BROWN","3");
    /** Поле для указания имени цвета */
    private final String name;
    /** Поле для указания порядкового номера цвета */
    private final String number;
    Color(String name, String number) {
        this.name = name;
        this.number = number;
    }
    /** Метод для получения цвета по названию или порядковому номеру
     * @param string строка, содержащая название или порядковый номер цвета
     * @return возвращает цвет, полученный из строки */
    public static Color getColor(String string) {
        for (Color color : Color.values()) {
            if (color.number.equals(string)|color.name.equals(string)) return color;
        }
        return null;
    }
}