package com.worldcup.adm;

import com.worldcup.adm.config.security.AdminUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;

public class Constants {
    public static final String FILE_SUFFIX_HTML = ".html";
    public static final String WEB_URL = "https://www.wannacorn.com/";
    public static final String MODEL_MAP_PAGE = "pageList";
    public static final AdminUserDetails ADMIN_USER = (AdminUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
}
