package com.worldcup.adm.util;

import com.worldcup.adm.entity.OperateResult;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponsePageUtil {
    public static String errorPage(ModelMap modelMap, String message) {
        modelMap.put("message", message);
        return "error";
    }
    public static String successPage(ModelMap modelMap, String title, String message) {
        modelMap.put("operateResult", new OperateResult(title, message));
        return "success";
    }
    public static void backAndRefresh(HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write("<script>history.go(-1);location.reload(true)</script>");
    }
}
