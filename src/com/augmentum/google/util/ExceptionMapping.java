/**
 * 
 */
package com.augmentum.google.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jason.Zhu
 * @date   2014-3-13
 * @email jasonzhu@augmentum.com.cn
 */
public class ExceptionMapping extends AbstractInitProps{
	   protected static final String PROPS_NAME = "config_error_code.xml";
	    public static final String HTTP_STATUS_SUFFIX = ".http.status";

	    // protected static InitProps props = new InitProps();
	    protected static Map<String, ExceptionMapping> props = new HashMap<String, ExceptionMapping>();

	    public ExceptionMapping(String propsFileName) {
	        super(propsFileName);
	    }

	    public static ExceptionMapping getInstance() {
	        return getInstance( PROPS_NAME);
	    }

	    public static ExceptionMapping getInstance(String propsFileName) {
	        ExceptionMapping init = props.get(propsFileName);
	        if (init == null) {
	            init = new ExceptionMapping(propsFileName);
	            props.put(propsFileName, init);
	        }

	        return init;
	    }

	    /**
	     * Lookup error code for exception.
	     *
	     * @param ex
	     * @return
	     */
	    public static Integer lookUpErrorCode(Exception ex) {
	        ExceptionMapping map = ExceptionMapping. getInstance();
	        String code = map.getString(ex.getClass().getName());
	        if (code == null) {
	            return null ;
	        } else {
	            return Integer.parseInt(code);
	        }
	    }

	    /**
	     * Lookup error message based on error code.
	     *
	     * @param errorCode
	     * @return
	     */
	    public static String lookUpErrorMessage(Integer errorCode) {
	        ExceptionMapping map = ExceptionMapping. getInstance();

	        String errorMessage = map.getString(String. valueOf(errorCode));

	        return errorMessage;
	    }

	    /**
	     * Lookup http status code to be set for the exception
	     * @param ex
	     * @return
	     */
	    public static int lookUpHttpStatusCode(Exception ex) {
	        ExceptionMapping map = ExceptionMapping. getInstance();
	        // Default is 500
	        return map.getInt(ex.getClass().getName() + HTTP_STATUS_SUFFIX, 500);
	    }

	    /**
	     * Lookup http status code to be set for the error code
	     * @param errorCode
	     * @return
	     */
	    public static int lookUpHttpStatusCode(Integer errorCode) {
	        ExceptionMapping map = ExceptionMapping. getInstance();
	        return map.getInt(errorCode + HTTP_STATUS_SUFFIX, 500);
	    }

}
