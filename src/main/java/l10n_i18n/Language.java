package l10n_i18n;

import java.util.Locale;
import java.util.ResourceBundle;

public class Language {

    public static final ResourceBundle en = ResourceBundle.getBundle("l10n_i18n.GUILabels", Locale.US);

    public static final ResourceBundle ru = ResourceBundle.getBundle("l10n_i18n.GUILabels", new Locale("ru", "RU"));

    public static final ResourceBundle fr = ResourceBundle.getBundle("l10n_i18n.GUILabels", Locale.FRANCE);

    public static final ResourceBundle tr = ResourceBundle.getBundle("l10n_i18n.GUILabels", new Locale("tr", "TR"));

}
