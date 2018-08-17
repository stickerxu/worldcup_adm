package com.worldcup.adm.util;

import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponsePageUtil {
    public static String errorPage(ModelMap modelMap) {
        return "error";
    }
    public static String successPage(ModelMap modelMap) {
        return "success";
    }
    public static void backAndRefresh(HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write("<script>history.go(-1);</script>");
    }
}
