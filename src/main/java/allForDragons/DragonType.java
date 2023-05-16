package allForDragons;

public enum DragonType {
    WATER("WATER", "1"),
    UNDERGROUND("UNDERGROUND", "2"),
    FIRE("FIRE", "3");
    /** Поле для указания имени типа */
    private final String name;
    /** Поле для указания порядкового номера типа */
    private final String number;
    DragonType(String name, String number) {
        this.name = name;
        this.number = number;
    }
    /** Метод для получения типа по названию или порядковому номеру
     * @param string строка, содержащая название или порядковый номер типа
     * @return возвращает тип, полученный из строки */
    public static DragonType getType(String string) {
        for (DragonType dragonType : DragonType.values()) {
            if (dragonType.number.equals(string)|dragonType.name.equals(string)) return dragonType;
        }
        return null;
    }
}