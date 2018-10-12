package com.worldcup.adm.config.security;

import com.worldcup.adm.util.GoogleAuthenticatorUtil;
import com.worldcup.adm.util.ParameterUtil;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

public class AdminTotpAuthenticationProvider extends DaoAuthenticationProvider {

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
//        super.additionalAuthenticationChecks(userDetails, authentication);
        AdminUserDetails details = (AdminUserDetails) userDetails;
        //从userDetails对象中获取密钥
        String secretKey = details.getGoogleAuthSecretKey();
        if (ParameterUtil.isBlank(secretKey)) {
                throw new BadCredentialsException(
                        "UserDetails' secretKey is null, please call xujignyao");
        }
        //从authentication中获取Web中输入的动态密码
        AdminTotpAuthenticationDetails totpAuthenticationDetails = (AdminTotpAuthenticationDetails) authentication.getDetails();
        Integer code = totpAuthenticationDetails.getCode();
        if (null == code) {
            throw new BadCredentialsException(
                    "The Authencation Code is not valid");
        }
        try {
            if (!GoogleAuthenticatorUtil.check_code(secretKey, code, System.currentTimeMillis())) {
                throw new BadCredentialsException(
                        "The Authencation Code is not valid");
            }
        } catch (IllegalArgumentException e) {
            throw new InternalAuthenticationServiceException(
                    "Google Authenticator Code verify failed", e);
        }
    }
}
