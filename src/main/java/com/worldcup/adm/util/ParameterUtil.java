package com.worldcup.adm.util;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

public class ParameterUtil {

    public static Integer parseInteger(String param) {
        return isBlank(param) == true ? null : Integer.valueOf(param);
    }

    public static BigDecimal parseBigDecimal(String param) {
        return isBlank(param) == true ? null : new BigDecimal(param);
    }

    public static Double parseDouble(String param) {
        return isBlank(param) == true ? null : Double.valueOf(param);
    }

    public static boolean isBlank(String param) {
        return StringUtils.isBlank(param);
    }

    public static boolean isNotBlank(String param) {
        return !isBlank(param);
    }

    public static Integer defaultZero(String param) {
        return parseInteger(param) == null ? 0 : parseInteger(param);
    }
}
