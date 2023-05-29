package l10n_i18n;

import java.util.ListResourceBundle;

public class GUILabels_fr extends ListResourceBundle {

    private static final Object[][] contents =
    {
            {"coll_type", "Type de collection"},
            {"coll_date", "Date d'initialisation"},
            {"coll_amount", "Nombre d'éléments"},
            {"recursion", "Recursion !!!"},
            {"no_access", "N'a pas accès au fichier"},
            {"not_found", "Fichier non trouvé"},
            {"no_dragon", "Ce dragon n'existe pas"},
            {"not_your", "Ce n'est pas votre dragon"},
            {"invalid_inp", "Entrée non valide"},
            {"no_greater", "Il n'y a pas de plus grand dragon"},
            {"no_lower", "Il n'y a pas de dragons inférieurs"},
            {"amount", "Montant des dragons supprimés"},
            {"count_by_head", "Nombre de dragons avec un nombre donné d'yeux"},
            {"delete_ps", "P.S. (Vous ne pouvez supprimer que les dragons que vous créez)"}
    };

    public Object[][] getContents() {
        return contents;
    }

}
