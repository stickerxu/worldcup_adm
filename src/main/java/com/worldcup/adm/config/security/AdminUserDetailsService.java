package com.worldcup.adm.config.security;

import com.worldcup.adm.entity.AdminUser;
import com.worldcup.adm.service.AdminUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Slf4j
public class AdminUserDetailsService implements UserDetailsService {
    @Autowired
    private AdminUserService adminUserService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AdminUser user = adminUserService.findFirstByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("未找到改用户");
        }
        log.info("用户：{} 正在登录。", user.getUsername());
        AdminUserDetails details = new AdminUserDetails(user);
        return details;
    }
}
