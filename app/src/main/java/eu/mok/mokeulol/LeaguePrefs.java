/*
 * Copyright (c) 2014.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.mok.mokeulol;

import android.content.Context;
import android.content.SharedPreferences;

public class LeaguePrefs {
    private final String PREFERENCE_NAME = "DEFAULT_SETTINGS";
    private final String KEY_API_TOKEN = "api_token";
    private SharedPreferences mSharedPreferences;

    public LeaguePrefs(Context context) {
        if (context == null)
            throw new NullPointerException("Context must not be null");
        this.mSharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);

    }

    /**
     * Gets the default set api token
     *
     * @return returns the api token or null if not set
     */
    public String getApiToken() {
        return this.mSharedPreferences.getString(KEY_API_TOKEN, null);
    }

    /**
     * sets the default api token
     *
     * @param token sets the api token
     */
    public void setApiToken(String token) {
        SharedPreferences.Editor editor = this.mSharedPreferences.edit();
        editor.putString(KEY_API_TOKEN, token);
        editor.commit();
    }

}
