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
            {"delete_ps", "P.S. (Sadece kendi yarattığınız ejderhaları silebilirsiniz)"},
            {"name", "İsim"},
            {"id", "ID"},
            {"creator", "Yaratıcı"},
            {"creation_date", "Oluşturma Tarihi"},
            {"age", "Yaş"},
            {"coordinates", "Koordinatlar"},
            {"x", "X Koordinatı"},
            {"y", "Y Koordinatı"},
            {"color", "Renk"},
            {"type", "Tip"},
            {"character", "Karakter"},
            {"eyes_count", "Göz Sayısı"},
            {"if_min", "Eğer min"},
            {"submit", "Gönder"},
            {"too_old", "Çok eski"},
            {"cleaned", "Koleksiyon parçanız temizlendi"},
            {"help", """
                    Harita : haritayı nesnelerle açın
                    Bilgi : koleksiyon hakkındaki bilgileri görüntüler (tür, başlatma tarihi, öğe sayısı)
                    Ekle (Eğer min) : koleksiyona yeni bir öğe ekleyin (eğer en küçükse)
                    Kimliğe göre kaldır : koleksiyondan bir öğeyi kimliğine göre kaldırır
                    Daha büyük kaldır : id ile belirtilenden büyük olan tüm öğeleri koleksiyondan kaldırır.
                    Alt kısmı çıkarın : id ile belirtilenden daha küçük olan tüm öğeleri koleksiyondan kaldırır
                    Temizle : oluşturduğunuz koleksiyon parçasını temizleyin
                    Komut dosyasını çalıştır : komut dosyasını verilen dosyadan okuyun ve çalıştırın
                    Güncelle : nesnenin değerlerini yenile (bunu bir hücreye çift tıklayarak yapabilirsiniz)
                    Kafaya göre say : değeri verilene eşit olan öğelerin sayısını gösterir
                    Ejderhayı yalnızca yaratıcısı sizseniz silebilir veya değiştirebilirsiniz!
                    """},
            {"script_help", """
                    add {eleman} : koleksiyona yeni bir öğe ekler
                    add_if_min {eleman} : değeri koleksiyondaki en küçük elemandan küçükse koleksiyona yeni bir eleman ekler
                    execute_script dosya_adı : belirtilen dosyadan bir betik okur ve çalıştırır. Betik, girdi ile aynı biçimde komutlar içerir
                    clear : koleksiyonu temizle
                    update id {alan} {yeni değer} : koleksiyondaki id'si verilene eşit olan bir elemanın değerini günceller
                    remove_by_id id : koleksiyondan bir öğeyi id'sine göre kaldırır
                    remove_greater id : koleksiyondan verilen değeri aşan tüm öğeleri kaldırır
                    remove_lower id : verilen elemandan daha küçük olan tüm elemanları koleksiyondan kaldırır
                    count_by_head gözler_sayisi : head alan değeri verilen değere eşit olan elemanların sayısını çıktı olarak verir
                    """},
            {"edit", "Sadece kendi yarattığınız ejderhaları düzenleyebilirsiniz"},
            {"search", "Arama"},
            {"add", "EKLE"},
            {"remove", "KIMLIĞE"},
            {"remove_by_id", "KIMLIĞE ID"},
            {"remove_lower", "ALT KISMI ÇIKARIN"},
            {"remove_greater", "DAHA BÜYÜK KALDI"},
            {"clear", "TEMIZLE"},
            {"execute_script", "KOMUT DOSYASINI"},
            {"count_by_head", "KAFAYA GÖRE SAY"},
            {"info", "BILGI"},
            {"help_button", "YARDIM"},
            {"map", "HARITA"},
            {"log_out", "Oturumu Kapat"},
            {"user", "GÜNCEL KULLANICI"},
            {"table", "TABLO"}
    };

    public Object[][] getContents() {
        return contents;
    }

}
