/**
 * 
 */
package com.augmentum.google.exception;

import com.augmentum.google.util.ExceptionMapping;

/**
 * @author Jason.Zhu
 * @email jasonzhu@augmentum.com.cn
 * @date May 7, 2014 2:33:48 PM
 */
public class GoogleException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private Integer errorCode;

    public GoogleException() {
        super();

        this.errorCode = 0;
    }

    public GoogleException(Integer errorCode) {

        super(ExceptionMapping.lookUpErrorMessage(errorCode));

        this.errorCode = errorCode;
    }

    public GoogleException(String msg, Integer errorCode) {
        super(msg);

        this.errorCode = errorCode;
    }

    public GoogleException(String msg, Throwable cause, Integer errorCode) {
        super(msg, cause);

        this.errorCode = errorCode;
    }

    public GoogleException(Throwable cause, Integer errorCode) {
        super(cause);

        this.errorCode = errorCode;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public int getHttpStatusCode() {
        ExceptionMapping map = ExceptionMapping.getInstance();
        String code = map.getString(errorCode + ExceptionMapping.HTTP_STATUS_SUFFIX);
        if (code == null) {
            return ExceptionMapping.lookUpHttpStatusCode(this);
        } else {
            return Integer.parseInt(code);
        }
    }

}
