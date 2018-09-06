package ngom.web.base;

import org.springframework.scheduling.annotation.Async;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hanaijun on 2018/6/3
 */
public abstract class BaseController {

    protected Map<String, Object> resultSuccess(Object mapObject) {
        Map<String, Object> allMap = new HashMap<>();
        allMap.put("success", true);
        if (null != mapObject) {
            allMap.put("data", mapObject);
        }
        return allMap;
    }
    protected Map<String, Object> resultSuccess() {
        Map<String, Object> allMap = new HashMap<>();
        allMap.put("success", true);
        return allMap;
    }

    protected Map<String, Object> resultFailure() {
        Map<String, Object> allMap = new HashMap<>();
        allMap.put("success", false);
        allMap.put("errCode", "");
        allMap.put("message", "");
        return allMap;
    }
    protected Map<String, Object> resultFailure(String errcode,String msg) {
        Map<String, Object> allMap = new HashMap<>();
        allMap.put("success", false);
        allMap.put("errCode", errcode);
        allMap.put("message", msg);
        return allMap;
    }


}
