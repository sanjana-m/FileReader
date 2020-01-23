package com.sanjana.utils;

import java.lang.reflect.Array;
import java.util.Collection;

/**
 * This class tries to simplify handling nulls and blanks for Strings and Objects
 */

public class Utils {
    public static boolean isObjectNull(Object obj) {
        return obj == null;
    }

    public static boolean isObjectBlank(Object obj) {
        if(isObjectNull(obj))
            return true;

        if(obj instanceof Array) {
            return Array.getLength(obj) == 0;
        } else if(obj instanceof Collection) {
            return ((Collection<?>) obj).size() == 0;
        }
        return false;
    }

    public static boolean isStringBlank(String str) {
        return str == null || str.isEmpty();
    }
}
