package ru.example.sbertest.utils;

public class BooleanUtils {
    public static Integer toInt(Boolean arg) {
        if (arg == null) {
            return 0;
        }
        return arg ? 1 : 0;
    }

    public static Boolean toBoolean(Integer arg) {
        if (arg == null ) {
            return false;
        }
        return arg == 0 ? false : true;
    }
}
