package l10n_i18n;

import java.util.Locale;
import java.util.ResourceBundle;

public class Test {

    public static void main(String[] args) {

        ResourceBundle en = ResourceBundle.getBundle("l10n_i18n.GUILabels", Locale.US);
        ResourceBundle ru = ResourceBundle.getBundle("l10n_i18n.GUILabels", new Locale("ru", "RU"));
        ResourceBundle fr = ResourceBundle.getBundle("l10n_i18n.GUILabels", Locale.FRANCE);
        ResourceBundle tr = ResourceBundle.getBundle("l10n_i18n.GUILabels", new Locale("tr", "TR"));
        System.out.println("EN " + en.getString("s1") + " or " + en.getString("s2"));
        System.out.println("RU " + ru.getString("s1") + " or " + ru.getString("s2"));
        System.out.println("FR " + fr.getString("s1") + " or " + fr.getString("s2"));
        System.out.println("TR " + tr.getString("s1") + " or " + tr.getString("s2"));

    }

}
