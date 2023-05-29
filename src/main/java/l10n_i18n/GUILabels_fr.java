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
            {"delete_ps", "P.S. (Vous ne pouvez supprimer que les dragons que vous créez)"},
            {"name", "Nom"},
            {"creator", "Créateur"},
            {"creation_date", "Date de création"},
            {"age", "L'âge"},
            {"coordinates", "Coordonnées"},
            {"x", "Coordonnée X"},
            {"y", "Coordonnées Y"},
            {"color", "Couleur"},
            {"type", "Type"},
            {"character", "Caractère"},
            {"eyes_count", "Nombre d'yeux"},
            {"if_min", "Si min"},
            {"submit", "Soumettre"},
            {"too_old", "Trop vieux"},
            {"cleaned", "Votre partie de la collection a été nettoyée"},
            {"help", """
                    Carte : ouvrir la carte avec les objets
                    Info : affiche des informations sur la collection (type, date d'initialisation, nombre d'objets)
                    Ajouter (si min) : ajoute un nouvel élément à la collection (si c'est le plus petit)
                    Supprimer par identifiant : supprime un élément de la collection en fonction de son identifiant
                    Retirer le plus grand nombre : retire de la collection tous les éléments plus grands que celui spécifié par id.
                    Retirer la partie inférieure : retire de la collection tous les éléments plus petits que celui spécifié par id.
                    Effacer : efface la partie de la collection que vous avez construite.
                    Exécuter le script : lit et exécute le script à partir du fichier donné.
                    Mise à jour : actualise les valeurs de l'objet (vous pouvez le faire en double-cliquant sur une cellule)
                    Compte par tête : affiche le nombre d'éléments dont la valeur est égale à la valeur donnée.
                    Vous ne pouvez supprimer ou modifier le dragon que si vous êtes son créateur!
                    """},
            {"script_help", """
                    add {élément} : ajoute un nouvel élément à la collection
                    add_if_min {élément} : ajoute un nouvel élément à la collection si sa valeur est inférieure au plus petit élément de la collection
                    execute_script nom_du_fichier : lit et exécute un script à partir du fichier spécifié. Le script contient des commandes sous la même forme que l'entrée
                    clear : efface la collection
                    update id {domaine} {nouvelle valeur} : mettre à jour la valeur d'un élément de la collection dont l'id est égal à l'id donné
                    remove_by_id id : supprimer un élément de la collection en fonction de son id
                    remove_greater id : supprime tous les éléments de la collection qui dépassent l'élément donné
                    remove_lower id : retire de la collection tous les éléments plus petits que l'élément donné
                    count_by_head compte_des_yeux : affiche le nombre d'éléments dont la valeur de l'en-tête est égale à la valeur donnée
                    """},
            {"edit", "Vous ne pouvez modifier que les dragons que vous avez créés"},
            {"search", "Recherche"},
            {"add", "AJOUTER"},
            {"remove", "SUPPRIMER"},
            {"remove_by_id", "SUPPRIMER PAR IDENTIFIANT"},
            {"remove_lower", "RETIRER LA PARTIE INFÉRIEURE"},
            {"remove_greater", "RETIRER LE PLUS GRAND NOMBRE"},
            {"clear", "EFFACER"},
            {"execute_script", "EXÉCUTER LE SCRIPT"},
            {"count_by_head", "COMPTE PAR TÊTE"},
            {"info", "INFO"},
            {"help_button", "AIDE"},
            {"map", "CARTE"},
            {"log_out", "Déconnexion"},
            {"user", "UTILISATEUR ACTUEL"},
            {"table", "TABLEAU"}
    };

    public Object[][] getContents() {
        return contents;
    }

}
