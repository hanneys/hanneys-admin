package ngom.config;

import ngom.exception.ErrorInfoMessage;
import ngom.exception.NgomAdminException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理
 * Created by hanaijun on 2018/6/3
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Map<String,Object> handleException(Exception e) {
        e.printStackTrace();
        Map<String, Object> map = new HashMap<>();
        map.put("code",-200);
        map.put("message","系统异常");
        return map;
    }



    @ExceptionHandler(value = NgomAdminException.class)
    @ResponseBody
    public ErrorInfoMessage<String> jsonErrorHandler(HttpServletRequest req, NgomAdminException e) throws Exception {
        ErrorInfoMessage<String> r = new ErrorInfoMessage<>();
        r.setMessage(e.getMessage());
        r.setCode(e.getErrCode());
        r.setData("Some Data");
        r.setUrl(req.getRequestURL().toString());
        return r;
    }



}
