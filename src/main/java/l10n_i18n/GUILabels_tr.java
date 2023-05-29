package l10n_i18n;

import java.util.ListResourceBundle;

public class GUILabels_tr extends ListResourceBundle {

    private static final Object[][] contents =
    {
            {"coll_type", "Koleksiyon türü"},
            {"coll_date", "Başlatma tarihi"},
            {"coll_amount", "Eleman sayısı"},
            {"recursion", "Özyineleme!!!"},
            {"no_access", "Dosyaya erişiminiz yok"},
            {"not_found", "Dosya bulunamadı"},
            {"no_dragon", "Böyle bir ejderha yok"},
            {"not_your", "Bu senin ejderhan değil"},
            {"invalid_inp", "Geçersiz girdi"},
            {"no_greater", "Daha büyük ejderha yok"},
            {"no_lower", "Daha düşük ejderha yok"},
            {"amount", "Silinen ejderhaların miktarı"},
            {"count_by_head", "Belirli sayıda göze sahip ejderhaların sayısı"},
            {"delete_ps", "P.S. (Sadece kendi yarattığınız ejderhaları silebilirsiniz)"}
    };

    public Object[][] getContents() {
        return contents;
    }

}
