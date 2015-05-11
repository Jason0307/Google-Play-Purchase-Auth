/**
 * 
 */
package com.augmentum.google.interceptor;

import java.util.Arrays;
import java.util.List;

import com.augmentum.google.model.Admin;
import com.augmentum.google.util.ConstantsUtil;
import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;
import com.jfinal.core.Controller;

/**
 * @author Jason.Zhu
 * @email jasonzhu@augmentum.com.cn
 * @date May 13, 2014 4:07:12 PM
 */
public class GlobalInterceptor implements Interceptor {

    private List<String> IGNORE_URLS = Arrays.asList("token", "login", "doLogin", "logout");

    @Override
    public void intercept(ActionInvocation ai) {
        String actionKey = ai.getActionKey();
        String uri = actionKey.substring(actionKey.lastIndexOf("/") + 1, actionKey.length());
        Controller controller = ai.getController();
        if (IGNORE_URLS.contains(uri)) {
            ai.invoke();
        } else {
            Admin admin = (Admin) controller.getSessionAttr(ConstantsUtil.ADMIN);
            if (null != admin) {
                ai.invoke();
            } else {
                controller.redirect("/admin/login");
            }
        }
    }

}
