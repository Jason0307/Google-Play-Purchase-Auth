/**
 * 
 */
package com.augmentum.google.model;

import java.util.Date;

import com.augmentum.google.exception.GoogleException;
import com.augmentum.google.util.ErrorCodeConstant;
import com.augmentum.google.util.MD5Util;
import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Model;

/**
 * @author Jason.Zhu
 * @email jasonzhu@augmentum.com.cn
 * @date May 13, 2014 3:30:39 PM
 */
@TableBind(tableName = "Admin", pkName = "id")
public class Admin extends Model<Admin> {

    private static final long serialVersionUID = 1L;
    public static Admin dao = new Admin();

    /**
     * admin user login
     * 
     * @param username
     * @param password
     * @return
     */
    public Admin login(String username, String password) {

        Admin admin = Admin.dao.findFirst("SELECT * FROM Admin WHERE username = ?", username.trim());
        if (null == admin) {
            throw new GoogleException(ErrorCodeConstant.ADMIN_INVALID);
        }
        
        String md5Pass = MD5Util.md5(password.trim());
        if (!md5Pass.equals(admin.getStr("password"))) {
            throw new GoogleException(ErrorCodeConstant.ADMIN_INVALID);
        }
        admin.set("lastLoginDate", new Date()).update();
        return admin;
    }
}
