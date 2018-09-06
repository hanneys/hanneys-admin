//package ngom.config;
//
//import ngom.exception.ErrorInfo;
//import ngom.exception.NgomAdminException;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * 拦截器验证token
// * Created by hanaijun on 2018/6/3
// */
//public class JwtInterceptor extends HandlerInterceptorAdapter {
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String authHeader = request.getParameter("Authorization");
//        String userId= request.getParameter("userId");
//
//        if (authHeader == null || !authHeader.startsWith("Bearer ") || userId == null) {
//            throw new NgomAdminException(ErrorInfo.SYS_PARAMS_ERROR_CODE,ErrorInfo.SYS_PARAMS_ERROR_INFO);
//        }
//        //取得token
//        String token = authHeader.substring(7);
//
//        try {
//            JwtUtil.checkToken(token);
//            String id = JwtUtil.parseJWT(token).getId();
//            if(!userId.equals(id)){
//                throw new NgomAdminException(ErrorInfo.USER_LOGIN_ERROR_CODE,ErrorInfo.USER_LOGIN_ERROR_INFO);
//            }
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new NgomAdminException(ErrorInfo.USER_LOGIN_ERROR_CODE,ErrorInfo.USER_LOGIN_ERROR_INFO);
//        }
//    }
//
//
//
//
//}
