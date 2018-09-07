package com.worldcup.adm.controller.user;

import com.worldcup.adm.Constants;
import com.worldcup.adm.entity.LoginUser;
import com.worldcup.adm.service.LoginUserService;
import com.worldcup.adm.util.ParameterUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
@RequestMapping("/user")
public class LoginUserController {
    @Autowired
    private LoginUserService loginUserService;

    @RequestMapping({"","/"})
    public String list(HttpServletRequest request, ModelMap modelMap) {
        Integer page = ParameterUtil.defaultZero(request.getParameter("page"));
        LoginUser user = new LoginUser();
        user.setPage(page);
        modelMap.put("page", page);
        Page<LoginUser> users = loginUserService.listUserByCriteria(user, Sort.by(Sort.Order.asc("id")));
        modelMap.put(Constants.MODEL_MAP_PAGE, users);
        return "user/list";
    }
}
