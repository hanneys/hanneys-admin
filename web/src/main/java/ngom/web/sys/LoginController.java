package ngom.web.sys;

import ngom.config.JwtUtil;
import ngom.config.NoAuthMethod;
import ngom.domain.sys.SysUser_T;
import ngom.dto.sys.SysUser;
import ngom.exception.ErrorInfo;
import ngom.exception.NgomAdminException;
import ngom.sys.SysUserService;
import ngom.web.base.BaseController;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hanaijun on 2018/6/3
 */
@RestController
@RequestMapping("user")
public class LoginController extends BaseController {

    @Autowired
    private SysUserService sysUserService;


    /**
     * @api {POST} /user/login 登录
     * @apiGroup Sys
     * @apiVersion 1.0.1
     * @apiDescription 登录
     * @apiParam {String} email 邮箱
     * @apiParam {String} password 密码
     * @apiParamExample {json} 请求示例：
     *       {
     * 	"email":"xxx@ngom.info",
     * 	"password":"123456"
     * }
     * @apiSuccess {String} success true:正确   false:错误
     * @apiSuccess {String} Authorization 登录token
     * @apiSuccess {Number} userId 用户id
     * @apiSuccessExample {json} 返回样例:
     *                {
     *     "data": {
     *         "Authorization": "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoiaGFuYWlqdW5AbmdvbS5pbmZvIiwicm9sZXMiOiJzeXMiLCJpYXQiOjE1Mjg0NDQ3ODEsImV4cCI6MTUyODQ0NTA4MX0.Hhb1IVomvvjXJf-bO2MAAbUvlgas_n-HcXw0KgD6xnc",
     *         "userId": 1
     *     },
     *     "success": true
     * }
     */
    @RequestMapping("/login")
    @NoAuthMethod
    public Map<String, Object> login(SysUser user, HttpSession httpSession) {

        if (user.getEmail() == null) {
            throw new NgomAdminException(ErrorInfo.USER_EMAIL_EMPTY_ERR_CODE, ErrorInfo.USER_EMAIL_EMPTY_ERR_INFO);
        }
        if(user.getPassword()==null){
            throw new NgomAdminException(ErrorInfo.USER_PASSWORD_EMPTY_ERR_CODE, ErrorInfo.USER_PASSWORD_EMPTY_ERR_INFO);
        }

        SysUser_T sysUserT = sysUserService.getSysUserByEmail(user.getEmail());
        if (sysUserT == null) {
            throw new NgomAdminException(ErrorInfo.USER_ISNO_ERR_CODE, ErrorInfo.USER_ISNO_ERR_INFO);
        }
        if (!DigestUtils.md5Hex(user.getPassword()).equals(sysUserT.getPassword())) {
            // 密码不对
            throw new NgomAdminException(ErrorInfo.USER_LOGIN_ERR_CODE, ErrorInfo.USER_LOGIN_ERR_INFO);
        }
        httpSession.setAttribute("userId",sysUserT.getId());
        return resultSuccess();
    }

}
