package com.worldcup.adm.config.security;

import com.worldcup.adm.util.ParameterUtil;
import org.springframework.security.authentication.BadCredentialsException;
import javax.servlet.http.HttpServletRequest;

public class AdminTotpAuthenticationDetails {

    private Integer code;

    public AdminTotpAuthenticationDetails(HttpServletRequest request) {
        String dynamicCode = request.getParameter("dynamicCode");
        if (null == dynamicCode) {
            throw new BadCredentialsException(
                    "动态密码有误");
        }
        try {
            this.code = ParameterUtil.parseInteger(dynamicCode);
        } catch (NumberFormatException e) {
            this.code = null;
        }
    }

    public Integer getCode() {
        return this.code;
    }
}
