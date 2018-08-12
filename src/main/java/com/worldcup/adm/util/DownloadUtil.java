package com.worldcup.adm.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DownloadUtil {
    public static final String PDF="application/pdf";
    public static final String ZIP="application/x-zip-compressed";
    public static final String RAR="application/octet-stream";
    public static final String DOC="application/msword";
    public static final String DOCX="application/vnd.openxmlformats-officedocument.wordprocessingml.document";
    public static final String XLS="application/vnd.ms-excel";
    public static final String XLSX="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    public static final String PPT="application/vnd.ms-powerpoint";
    public static final String PPTX="application/vnd.openxmlformats-officedocument.presentationml.presentation";
    public static final String JPG="image/jpeg";
    public static final String PNG="image/png";
    public static final String TXT="text/plain";
    public static final String HTML="text/html";

    public static void downloadFileStream(HttpServletResponse response, Path path, String contentType) throws IOException {
        response.setContentType(contentType);// 下载东西的格式
        response.setContentLengthLong(path.toFile().length());
        response.addHeader("Content-disposition", "attachment; filename=WannaCorn.html");
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        Files.copy(path, response.getOutputStream());
    }
}
