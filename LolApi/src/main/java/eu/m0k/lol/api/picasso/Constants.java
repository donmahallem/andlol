/*
 * Copyright (c) 2014.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.m0k.lol.api.picasso;

/**
 * Created by Don on 30.09.2014.
 */
public interface Constants {
    public final static String VERSION = "4.17.1";
    public final static String SCHEME = "league";
    public final static String SPELL = "spell";
    public final static String SUMMONER_ICON = "summonericon";
    public final static String CHAMPION_ICON = "championicon";
    public final static String CHAMPION_SKIN = "championskin";
    public final static String MASTERY = "mastery";
    public final static String IMAGE = "img";
    public final static String PATH_IMG_SPELL = SCHEME + "://" + IMAGE + "/" + SPELL + "/";
    public final static String PATH_IMG_SUMMONER_ICON = SCHEME + "://" + IMAGE + "/" + SUMMONER_ICON + "/";
    public final static String PATH_IMG_CHAMPION_ICON = SCHEME + "://" + IMAGE + "/" + CHAMPION_ICON + "/";
    public final static String PATH_IMG_CHAMPION_SKIN = SCHEME + "://" + IMAGE + "/" + CHAMPION_SKIN + "/";
    public final static String PATH_IMG_MASTERY = SCHEME + "://" + IMAGE + "/" + MASTERY + "/";
    public final static String LOADING = "loading";
    public final static String PATH_IMG_CHAMPION_LOADING = SCHEME + "://" + IMAGE + "/" + LOADING + "/";
    public final static String SPLASH = "splash";
    public final static String PATH_IMG_CHAMPION_SPLASH = SCHEME + "://" + IMAGE + "/" + SPLASH + "/";
}
