/**
 * 
 */
package com.augmentum.google.controller;

import com.augmentum.google.model.Admin;
import com.augmentum.google.model.GoogleAccountInfo;
import com.augmentum.google.util.ConstantsUtil;
import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;

/**
 * @author Jason.Zhu
 * @email jasonzhu@augmentum.com.cn
 * @date May 13, 2014 3:02:05 PM
 */
@ControllerBind(controllerKey = "/admin", viewPath = "/WEB-INF/page/common/")
public class LoginController extends Controller {

    public void index() {
        render("index.jsp");
    }

    public void login() {
        render("login.jsp");
    }

    public void doLogin() {
        String username = getPara("username");
        String password = getPara("password");
        String message = null;
        Admin admin = null;
        try {
            admin = Admin.dao.login(username, password);
        } catch (Exception e) {
            message = e.getMessage();
            setAttr("message", message);
            render("login.jsp");
            return;
        }
        setSessionAttr(ConstantsUtil.ADMIN, admin);
        redirect("/admin/google/config");
    }
    
    public void token() {
        String gameProject = getPara("game");
        GoogleAccountInfo token = GoogleAccountInfo.dao.findByGameUnique(gameProject);
        renderJson(token);
    }
}
