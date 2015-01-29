/*
 * Copyright (c) 2015.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.m0k.lol.api.picasso;

public interface Constants {
    public final static String VERSION = "4.17.1";
    public final static String SCHEME = "league";
    public final static String SPELL = "spell";
    public final static String PROFILE_ICON = "profileicon";
    public final static String CHAMPION_ICON = "championicon";
    public final static String CHAMPION_SKIN = "championskin";
    public final static String MASTERY = "mastery";
    public final static String IMAGE = "img";
    public final static String PATH_IMG_SPELL = SCHEME + "://" + IMAGE + "/" + SPELL + "/";
    public final static String PATH_IMG_PROFILE_ICON = SCHEME + "://" + IMAGE + "/" + PROFILE_ICON + "/";
    public final static String PATH_IMG_CHAMPION_ICON = SCHEME + "://" + IMAGE + "/" + CHAMPION_ICON + "/";
    public final static String PATH_IMG_CHAMPION_SKIN = SCHEME + "://" + IMAGE + "/" + CHAMPION_SKIN + "/";
    public final static String PATH_IMG_MASTERY = SCHEME + "://" + IMAGE + "/" + MASTERY + "/";
    public final static String LOADING = "loading";
    public final static String PATH_IMG_CHAMPION_LOADING = SCHEME + "://" + IMAGE + "/" + LOADING + "/";
    public final static String SPLASH = "splash";
    public final static String PATH_IMG_CHAMPION_SPLASH = SCHEME + "://" + IMAGE + "/" + SPLASH + "/";
    public final static String PASSIVE = "passive";
    public final static String PATH_IMG_PASSIVE = SCHEME + "://" + IMAGE + "/" + PASSIVE + "/";
}
