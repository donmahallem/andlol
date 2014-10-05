/*
 * Copyright (c) 2014.
 *
 * Visit https://github.com/donmahallem/andlol for more info!
 *
 * Romanes eunt domus - Brian!
 */

package eu.m0k.lol.api.internal;

import java.util.List;

import eu.m0k.lol.api.network.Parameter;

/**
 * Created by Don on 06.10.2014.
 */
public class Util {
    public static String parameterToString(List<Parameter> parameterList) {
        String parameterString = "";
        for (Parameter parameter : parameterList) {
            if (parameterString.length() != 0)
                parameterString += "&";
            parameterString += parameter.getKey() + "=" + parameter.getValue();
        }
        return parameterString;
    }
}
