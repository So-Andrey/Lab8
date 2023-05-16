package allForDragons;

public enum DragonCharacter {
    CUNNING("CUNNING", "1"),
    WISE("WISE", "2"),
    CHAOTIC_EVIL("CHAOTIC_EVIL", "3"),
    FICKLE("FICKLE", "4");
    /** Поле для указания имени характера */
    private final String name;
    /** Поле для указания порядкового номера характера */
    private final String number;
    DragonCharacter(String name, String number) {
        this.name = name;
        this.number = number;
    }
    /** Метод для получения характера по названию или порядковому номеру
     * @param string строка, содержащая название или порядковый номер характера
     * @return возвращает характер, полученный из строки */
    public static DragonCharacter getCharacter(String string) {
        for (DragonCharacter dragonCharacter : DragonCharacter.values()) {
            if (dragonCharacter.number.equals(string)|dragonCharacter.name.equals(string)) return dragonCharacter;
        }
        return null;
    }
}