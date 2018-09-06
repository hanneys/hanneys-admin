package ngom.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;

/**
 * 权限控制类
 * Created by hanaijun on 2018/6/3
 */
@Configuration
@Aspect
public class PermissionProxy {



    @Around("@annotation(ngom.config.CheckPermission)")
    public Object check(ProceedingJoinPoint point){
        for (Object arg : point.getArgs()) {
            if (!(arg instanceof HttpServletRequest)) {
                continue;
            }

            HttpServletRequest request = (HttpServletRequest) arg;
            String userId = request.getParameter("userId");
        }
        return "";
    }
}
