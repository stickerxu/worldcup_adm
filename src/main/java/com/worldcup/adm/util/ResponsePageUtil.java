package com.worldcup.adm.util;

import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponsePageUtil {
    public static String errorPage(String message, ModelMap modelMap) {
        modelMap.put("message",message);
        return "error";
    }

    public static void backAndRefresh(HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write("<script>history.back();</script>");
    }
}
