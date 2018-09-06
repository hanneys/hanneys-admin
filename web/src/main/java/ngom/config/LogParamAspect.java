package ngom.config;

import lombok.extern.slf4j.Slf4j;
import ngom.utils.FastJsonUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 日志切面处理
 * Created by hanaijun on 2018/6/5
 */
@Component
@Aspect
@Order(-1)
@Slf4j
public class LogParamAspect {


    @Pointcut("execution(public * ngom.web.*.*Controller.*(..))")
    public void webLog(){}



    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String stringBuffer = "\n" +
                "url : " + request.getRequestURL().toString() + "\n" +
                "HTTP_METHOD : " + request.getMethod() + "\n" +
                "IP : " + request.getRemoteAddr() + "\n" +
                "CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName() + "\n" +
                "ARGS : " + FastJsonUtil.toJson(joinPoint.getArgs()) + "\n";
        log.info(stringBuffer);


    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) {
        // 处理完请求，返回内容
        String sb = "\n" +
                "Response : " + FastJsonUtil.toJson(ret);
        log.info(sb);
    }



}
