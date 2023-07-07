package org.test.common.util;

import java.util.Arrays;

public class ArrayUtil {
    public static String[] longToString(long[] longs) {
        String[] strings = new String[longs.length];
        for (int i = 0; i < strings.length; i++) {
            strings[i] = Long.toString(longs[i]);
        }
        return strings;
    }
}
