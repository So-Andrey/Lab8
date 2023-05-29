package l10n_i18n;

import java.util.ListResourceBundle;

public class GUILabels_en extends ListResourceBundle {

    private static final Object[][] contents =
    {
            {"coll_type", "Collection type"},
            {"coll_date", "Initialization date"},
            {"coll_amount", "Number of elements"},
            {"recursion", "Recursion!!!"},
            {"no_access", "Do not have access to file"},
            {"not_found", "File not found"},
            {"no_dragon", "There is no such dragon"},
            {"not_your", "This is not your dragon"},
            {"invalid_inp", "Invalid input"},
            {"no_greater", "There is no greater dragons"},
            {"no_lower", "There is no lower dragons"},
            {"amount", "Amount of deleted dragons"},
            {"count_by_head", "The number of dragons with a given number of eyes"},
            {"delete_ps", "P.S. (You can only delete dragons you create)"}

    };

    public Object[][] getContents() {
        return contents;
    }

}
