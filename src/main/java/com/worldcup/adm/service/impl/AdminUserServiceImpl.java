package com.worldcup.adm.service.impl;

import com.worldcup.adm.entity.AdminUser;
import com.worldcup.adm.repository.AdminUserRepository;
import com.worldcup.adm.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminUserServiceImpl implements AdminUserService {
    @Autowired
    private AdminUserRepository adminUserRepository;
    @Override
    public AdminUser findFirstByUsername(String username) {
        return adminUserRepository.findFirstByUsername(username);
    }
}
