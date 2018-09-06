package ngom.web.consult;

import ngom.config.NoAuthMethod;
import ngom.consult.AppUserService;
import ngom.domain.user.User_C;
import ngom.web.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("appUser")
public class AppUserController extends BaseController {

    @Autowired
    private AppUserService appUserService;
    /**
     * @api {POST} /appUser/findAppUser 查询用户
     * @apiGroup AppUser
     * @apiVersion 1.0.1
     * @apiDescription 用户管理
     * @apiParam {String} name 用户名
     * @apiParam {String} email 用户账号
     * @apiSuccess {Long} id 用户id
     * @apiSuccess {String} email 用户账号
     * @apiSuccess {Date} createTime 注册时间
     * @apiSuccess {Short} banned 禁言 0:未禁言 1:已禁言
     * @apiSuccess {Short} freezed 冻结 0：未冻结 1：已冻结
     * @apiSuccess {String} name 用户名
     * @apiSuccessExample {json} 返回样例:
        {
            "data": {
                    "id": 173711315785793536,
                    "email": "244930322@qq.com",
                    "password": "f57149016014226c8c3dfceb32d14f23",
                    "inviteCode": "",
                    "identityCode": null,
                    "name": "呵呵呵呵呵呵",
                    "gender": 1,
                    "birthday": "2007-06-08 00:00:00",
                    "position": "主席",
                    "phone": "15311806620",
                    "areaCode": null,
                    "image": "1529932197245_173711315785793536.jpg",
                    "signatures": null,
                    "freezed": 0,
                    "banned": null,
                    "registerSource": null,
                    "inviterFirst": null,
                    "inviterSecond": null,
                    "source": null,
                    "thirdAccount": null,
                    "createTime": "2018-05-27 18:42:42",
                    "updateTime": "2018-06-25 21:09:59",
                    "amount": null
                },
            "success": true
        }
     */
    @RequestMapping("/findAppUser")
    @NoAuthMethod
    public Map<String, Object> findAppUser(@RequestParam("name") String name,
                                               @RequestParam("email") String email,
                                               HttpSession httpSession) {

        if (StringUtils.isEmpty(name) && StringUtils.isEmpty(email)){
            return resultFailure("001", "用户名用户账户不能同时为空");
        }
        User_C consultLabelC = appUserService.findAppUser(name, email);
        return resultSuccess(consultLabelC);
    }

    /**
     * @api {POST} /appUser/updateAppUser 操作用户
     * @apiGroup AppUser
     * @apiVersion 1.0.1
     * @apiDescription 用户管理
     * @apiParam {Long} id 用户id
     * @apiParam {Short} flag 1:禁言, 2:解禁;     3:冻结  4:解冻
     * @apiSuccessExample {json} 返回样例:
        {
            "data": {},
            "success": true
        }
     */
    @RequestMapping("/updateAppUser")
    @NoAuthMethod
    public Map<String, Object> updateAppUser(@RequestParam("id") Long id,
                                           @RequestParam("flag") Short flag,
                                           HttpSession httpSession) {

        if (StringUtils.isEmpty(id)){
            return resultFailure("001", "id 不能为空");
        }
        if (StringUtils.isEmpty(flag)){
            return resultFailure("002", "flag 不能为空");
        }
        appUserService.updateAppUser(id, flag);
        return resultSuccess();
    }

}
