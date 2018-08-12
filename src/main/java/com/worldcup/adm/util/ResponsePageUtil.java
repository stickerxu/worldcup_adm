package com.worldcup.adm.util;

import org.springframework.ui.ModelMap;

public class ResponsePageUtil {
    public static String errorPage(String message, ModelMap modelMap) {
        modelMap.put("message",message);
        return "error";
    }
}
