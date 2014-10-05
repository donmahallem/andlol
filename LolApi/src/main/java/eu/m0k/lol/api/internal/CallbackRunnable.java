/*
 * Copyright (c) 2014.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.m0k.lol.api.internal;

import java.util.concurrent.Executor;

/**
 * Created by Don on 05.10.2014.
 */
public abstract class CallbackRunnable<T> implements Runnable {
    private final LeagueCallback<T> callback;
    private final Executor callbackExecutor;
    private final ErrorHandler errorHandler;

    CallbackRunnable(LeagueCallback<T> callback, Executor callbackExecutor, ErrorHandler errorHandler) {
        this.callback = callback;
        this.callbackExecutor = callbackExecutor;
        this.errorHandler = errorHandler;
    }

    @SuppressWarnings("unchecked")
    @Override
    public final void run() {
        try {
            final ResponseWrapper wrapper = obtainResponse();
            callbackExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    callback.onSuccess((T) wrapper.responseBody, wrapper.response);
                }
            });
        } catch (LeagueError e) {
            Throwable cause = errorHandler.handleError(e);
            final LeagueError handled = cause == e ? e : LeagueError.unexpectedError(e.getUrl(), cause);
            callbackExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    callback.onFailure(handled);
                }
            });
        }
    }

    public abstract ResponseWrapper obtainResponse();
}

