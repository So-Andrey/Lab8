package exceptions;

public class IllegalValueOfXException extends Exception {
    /** Исключение для обработки неверного значения координаты х дракона */
    public IllegalValueOfXException() {
        super("Максимальное значение координаты Х: 610");
    }
}
