/*
 * Copyright (c) 2014.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.m0k.lol.api.model;

public enum Locale {
    ENGLISH_US("en_US"), SPANISH("es_ES"), FRENCH("fr_FR"), GERMAN("de_DE"), ITALIAN("it_IT"),
    POLISH("pl_PL"), GREEK("el_GR"), ROMANIAN("ro_RO"), PORTUGUESE_BRAZIL("pt_BR"), TURKISH("tr_TR"),
    THAI("th_TH"), VIETNAMESE("vn_VN"), INDONESIAN("id_ID"), RUSSIAN("ru_RU"), KOREAN("ko_KR"),
    CHINESE_CHINA("ch_ZN"), CHINESE_TAIWAN("ch_TW");

    private final String mLocal;

    Locale(String local) {
        this.mLocal = local;
    }

    public String getLocal() {
        return mLocal;
    }

    @Override
    public String toString() {
        return mLocal;
    }
}
