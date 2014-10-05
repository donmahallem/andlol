/*
 * Copyright (c) 2014.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.m0k.lol.api.internal;

import java.util.concurrent.Executor;

public abstract class LeagueCallbackRunnable<T> implements Runnable {
    private final LeagueCallback<T> callback;
    private final Executor callbackExecutor;
    private final LeagueErrorHandler mLeagueErrorHandler;

    public LeagueCallbackRunnable(LeagueCallback<T> callback, Executor callbackExecutor, LeagueErrorHandler leagueErrorHandler) {
        this.callback = callback;
        this.callbackExecutor = callbackExecutor;
        this.mLeagueErrorHandler = leagueErrorHandler;
    }

    @SuppressWarnings("unchecked")
    @Override
    public final void run() {
        try {
            final LeagueResponseWrapper wrapper = obtainResponse();
            callbackExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    callback.onSuccess((T) wrapper.responseBody, wrapper.response);
                }
            });
        } catch (LeagueError e) {
            Throwable cause = mLeagueErrorHandler.handleError(e);
            final LeagueError handled = cause == e ? e : LeagueError.unexpectedError(e.getUrl(), cause);
            callbackExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    callback.onFailure(handled);
                }
            });
        }
    }

    public abstract LeagueResponseWrapper obtainResponse();
}

