package ngom.config;

import java.lang.annotation.*;

/**
 * 检查权限
 * Created by hanaijun on 2018/6/3
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CheckPermission {
}
