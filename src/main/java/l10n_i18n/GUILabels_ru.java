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
            {"delete_ps", "P.S. (Вы можете удалять только созданных вами драконов)"}
    };

    public Object[][] getContents() {
        return contents;
    }

}
