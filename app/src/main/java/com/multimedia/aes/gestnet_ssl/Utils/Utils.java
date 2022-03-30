package com.multimedia.aes.gestnet_ssl.Utils;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Utils {
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static String escaparString(String s) {
        return s.replace("'", "\'").replace("'", "\"");
    }

}
