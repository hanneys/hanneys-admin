package ngom.web.sys;


import ngom.config.NoAuthMethod;
import ngom.dto.sys.SysUser;
import ngom.sys.SysUserService;
import ngom.web.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author ngom
 */
@RestController
@RequestMapping("sysUser")
public class SysUserController extends BaseController {


    @Autowired
    private SysUserService sysUserService;


    /**
     * @api {POST} /sysUser/addUser 添加管理员
     * @apiGroup User
     * @apiVersion 1.0.1
     * @apiDescription 添加管理员
     * @apiParam {String} email 邮箱
     * @apiParam {String} name 名称
     * @apiParam {Integer[]} permissionIds 权限ids
     * @apiParam {String} position 职务
     * @apiParam {String} phone 电话
     * @apiParamExample {json} 请求示例：
     *       {
     *           "email":"hanaijun@ngom.info",
     *           "name":"haj",
     *           "permissionIds":[1,2,3],
     *           "position":"zz",
     *           "phone":"10086",
     *       }
     * @apiSuccessExample {json} 返回样例:
     *  {
     *      "success":true
     *  }
     */
    @RequestMapping("addUser")
    @NoAuthMethod
    public Map<String,Object> addUser(SysUser sysUser){
       sysUserService.addUser(sysUser);
       return resultSuccess();
}




    @RequestMapping("getAllUser")
    public Map<String,Object> getAll(@RequestBody SysUser sysUser){
        return resultSuccess(sysUserService.getAll(sysUser));
    }

    @RequestMapping("getUser")
    public Map<String,Object> getUser(HttpSession httpSession){
        String userId= String.valueOf(httpSession.getAttribute("userId"));
        return resultSuccess(sysUserService.getById(Long.valueOf(userId)));
    }

    /**
     * @api {POST} /sysUser/updatePassword 修改密码
     * @apiGroup User
     * @apiVersion 1.0.1
     * @apiDescription 修改密码
     * @apiParam {String} password 密码
     * @apiParam {String} newPassword 新密码
     * @apiParamExample {json} 请求示例：
     *       {
     *           "password":123456,
     *           "newPassword":"zzz1233"
     *       }
     * @apiSuccessExample {json} 返回样例:
     *  {
     *      "success":true
     *  }
     */
    @RequestMapping("updatePassword")
    public Map<String,Object> getUser(SysUser sysUser,HttpSession httpSession){
        String userId= String.valueOf(httpSession.getAttribute("userId"));
        sysUserService.updatePassword(Long.valueOf(userId),sysUser.getPassword(),sysUser.getNewPassword());
        return resultSuccess();
    }

    /**
     * @api {POST} /sysUser/logout 退出登录
     * @apiGroup User
     * @apiVersion 1.0.1
     * @apiDescription 退出登录
     * @apiParamExample {json} 请求示例：
     * {
     *
     * }
     * @apiSuccessExample {json} 返回样例:
     *  {
     *      "success":true
     *  }
     */
    @RequestMapping("logout")
    public Map<String,Object> logout(HttpSession httpSession){
        httpSession.invalidate();
        return resultSuccess();
    }





}