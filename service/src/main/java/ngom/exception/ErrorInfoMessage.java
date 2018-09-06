package ngom.exception;

import lombok.Data;

/**
 * Created by hanaijun on 2018/6/3
 */
@Data
public class ErrorInfoMessage<T> {

    private String code;
    private String message;
    private String url;
    private T data;

}
