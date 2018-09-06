package ngom.exception;

import lombok.Data;

/**
 * Created by hanaijun on 2018/6/3
 */
@Data
public class NgomAdminException extends RuntimeException{

    /**
     * 错误码
     */
    private String errCode;
    private String message;

    public NgomAdminException(){
        super();
    }
    public NgomAdminException(String message, Throwable cause){
        super(message, cause);
    }

    public NgomAdminException(Throwable cause) {
        super(cause);
    }

    public NgomAdminException(String errCode, String message) {
        super(message);
        setErrCode(errCode);
        setMessage(message);
    }
}
