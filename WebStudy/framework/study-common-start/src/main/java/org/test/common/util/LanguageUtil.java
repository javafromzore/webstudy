package org.test.common.util;

public class LanguageUtil {
    public LanguageUtil() {
    }

    public static String get(String s) {
        return s;
    }

    public static String format(String s, Object... args) {
        return String.format(s, args);
    }
}
