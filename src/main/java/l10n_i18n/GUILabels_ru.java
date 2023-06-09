package l10n_i18n;

import java.util.ListResourceBundle;

public class GUILabels_ru extends ListResourceBundle {

    private static final Object[][] contents =
    {
            {"coll_type", "Тип коллекции"},
            {"coll_date", "Дата инициализации"},
            {"coll_amount", "Количество элементов"},
            {"recursion", "Рекурсия!!!"},
            {"no_access", "Нет доступа к файлу"},
            {"not_found", "Файл не найден"},
            {"no_dragon", "Такого дракона не существует"},
            {"not_your", "Это не ваш дракон"},
            {"invalid_inp", "Неверный ввод"},
            {"no_greater", "Больших драконов не существует"},
            {"no_lower", "Меньших драконов не существует"},
            {"amount", "Количество удаленных драконов"},
            {"count_by_head", "Количество драконов с заданным числом глаз"},
            {"delete_ps", "P.S. (Вы можете удалять только созданных вами драконов)"},
            {"name", "Имя"},
            {"creator", "Создатель"},
            {"creation_date", "Дата создания"},
            {"age", "Возраст"},
            {"coordinates", "Координаты"},
            {"x", "Координата X"},
            {"y", "Координата Y"},
            {"color", "Цвет"},
            {"type", "Тип"},
            {"character", "Характер"},
            {"eyes_count", "Количество глаз"},
            {"if_min", "Если меньший"},
            {"submit", "Отправить"},
            {"too_old", "Слишком стар"},
            {"cleaned", "Созданная Вами часть коллекции была очищена"},
            {"help", """
                    Карта : открыть карту с объектами
                    Информация : отобразить информацию о коллекции (тип, дата инициализации, количество элементов)
                    Добавить (Если меньший) : добавить новый элемент в коллекцию (если он наименьший).
                    Удалить по ID : удалить элемент из коллекции по его ID
                    Удалить большие : удалить из коллекции все элементы больше указанного по ID.
                    Удалить меньшие : удалить из коллекции все элементы меньше указанного по ID.
                    Очистить : очистить часть коллекции, которую вы создали.
                    Выполнить скрипт : прочитать и выполнить скрипт из заданного файла.
                    Обновить : обновить значения объекта (это можно сделать, дважды щелкнув по ячейке).
                    Количество по глазам : показать количество элементов, значение которых равно заданному.
                    Вы можете удалить или изменить дракона, только если являетесь его создателем!
                    """},
            {"script_help", """
                    add {элемент} : добавить новый элемент в коллекцию
                    add_if_min {элемент} : добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции
                    execute_script имя_файла : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит
                    clear : очистить коллекцию
                    update id {поле} {новое значение} : обновить значение элемента коллекции, id которого равен заданному
                    remove_by_id id : удалить элемент из коллекции по его id
                    remove_greater id : удалить из коллекции все элементы, превышающие заданный
                    remove_lower id : удалить из коллекции все элементы, меньшие, чем заданный
                    count_by_head количество_глаз : вывести количество элементов, значение поля head которых равно заданному
                    """},
            {"edit", "Вы можете изменять только созданных Вами драконов"},
            {"search", "Поиск"},
            {"add", "ДОБАВИТЬ"},
            {"remove", "УДАЛИТЬ"},
            {"remove_by_id", "УДАЛИТЬ ПО ID"},
            {"remove_lower", "УДАЛИТЬ МЕНЬШИЕ"},
            {"remove_greater", "УДАЛИТЬ БОЛЬШИЕ"},
            {"clear", "ОЧИСТИТЬ"},
            {"execute_script", "ВЫПОЛНИТЬ СКРИПТ"},
            {"count_by_head", "СЧИТАТЬ ПО ГЛАЗАМ"},
            {"info", "ИНФОРМАЦИЯ"},
            {"help_button", "ПОМОЩЬ"},
            {"map", "КАРТА"},
            {"log_out", "Выйти"},
            {"user", "ТЕКУЩИЙ ПОЛЬЗОВАТЕЛЬ"},
            {"table", "ТАБЛИЦА"},
            {"login", "Логин"},
            {"password", "Пароль"},
            {"reg","РЕГИСТРАЦИЯ"},
            {"auth", "АВТОРИЗАЦИЯ"},
            {"already_reg", "Уже зарегистрированы? Войти"},
            {"become", "Нет аккаунта? Зарегистрироваться"},
            {"log_in", "Войти"},
            {"login_exists", "Существующий логин"},
            {"date_format", "dd.MM.yyyy HH:mm:ss Z"},
            {"separator", ","}
    };

    public Object[][] getContents() {
        return contents;
    }

}
