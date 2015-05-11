/**
 * 
 */
package com.augmentum.google.interceptor;

import com.augmentum.google.exception.GoogleException;
import com.augmentum.google.util.ConstantsUtil;
import com.augmentum.google.util.JsonResponse;
import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;
import com.jfinal.core.Controller;

/**
 * 
 * @author Jason.Zhu
 * @email jasonzhu@augmentum.com.cn
 * @date May 7, 2014 2:26:05 PM
 */
public class ExceptionInterceptor implements Interceptor {

    @Override
    public void intercept(ActionInvocation ai) {
        Controller controler = ai.getController();
        try {
            ai.invoke();
        } catch (Exception e) {
            int statusCode = -1;
            String errorMessage = "";
            if (e instanceof GoogleException) {
                e.printStackTrace();
                GoogleException ex = (GoogleException) e;
                statusCode = ex.getErrorCode();
                errorMessage = ex.getMessage();

            } else {
                errorMessage = ConstantsUtil.GLOBAL_ERROR;
            }
            controler.renderJson(new JsonResponse<String>(statusCode, null, errorMessage));
        }

    }

}
