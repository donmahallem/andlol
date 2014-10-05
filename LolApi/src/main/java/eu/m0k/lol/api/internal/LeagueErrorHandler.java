/*
 * Copyright (c) 2014.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.m0k.lol.api.internal;

/**
 * Created by Don on 05.10.2014.
 */
public interface LeagueErrorHandler {
    public final LeagueErrorHandler DEFAULT = new LeagueErrorHandler() {
        @Override
        public Throwable handleError(LeagueError cause) {
            return cause;
        }
    };

    public Throwable handleError(LeagueError cause);
}