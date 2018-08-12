package com.worldcup.adm.util;

import java.security.SecureRandom;

public class SequenceUtil {
    private static final String NUMBERCHAR = "0123456789";

    // 生成格式为 yyyyMMddHHmmss+3位随机数 的字符串 文件名
    public static String createFileNameStr() {
        StringBuilder sb = new StringBuilder();
        String code = generateNumberString(3);
        sb.append(DateTimeUtil.format(DateTimeUtil.now(), DateTimeUtil.FORMAT_YMDHMS));
        sb.append(code);
        return sb.toString();
    }

    public static String generateNumberString(int length) {
        StringBuilder sb = new StringBuilder();
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < length; i++) {
            sb.append(NUMBERCHAR.charAt(random.nextInt(NUMBERCHAR.length())));
        }
        return sb.toString();
    }
}
