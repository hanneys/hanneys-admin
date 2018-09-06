package ngom.web.sys;

import ngom.config.NoAuthMethod;
import ngom.sys.SysMenuService;
import ngom.web.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by hanaijun on 2018/6/8
 */
@RestController
@RequestMapping("sys")
public class SysController extends BaseController {

    @Autowired
    private SysMenuService sysMenuService;



    /**
     * @api {POST} /sys/getMenu 获取菜单
     * @apiGroup Sys
     * @apiVersion 1.0.1
     * @apiDescription 获取菜单
     * @apiSuccess {String} success true:正确   false:错误
     * @apiSuccess {String} id 菜单id
     * @apiSuccess {String} name 菜单名称
     * @apiSuccess {String} menuList 子菜单
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "data": [
     *         {
     *             "id": 1,
     *             "name": "审核",
     *             "menuList": [
     *                 {
     *                     "id": 3,
     *                     "name": "全部审核"
     *                 },
     *                 {
     *                     "id": 4,
     *                     "name": "待审核"
     *                 }
     *             ]
     *         },
     *         {
     *             "id": 2,
     *             "name": "反馈",
     *             "menuList": [
     *                 {
     *                     "id": 5,
     *                     "name": "反馈列表"
     *                 }
     *             ]
     *         },
     *         {
     *             "id": 6,
     *             "name": "文章管理",
     *             "menuList": [
     *                 {
     *                     "id": 7,
     *                     "name": "文章列表"
     *                 }
     *             ]
     *         },
     *         {
     *             "id": 8,
     *             "name": "评论管理",
     *             "menuList": [
     *                 {
     *                     "id": 9,
     *                     "name": "评论列表"
     *                 }
     *             ]
     *         }
     *     ],
     *     "success": true
     * }
     */
    @PostMapping("/getMenu")
    @NoAuthMethod
    public Map<String,Object> getAllMenu(HttpSession httpSession){
        String userId= String.valueOf(httpSession.getAttribute("userId"));
        return resultSuccess(sysMenuService.menuListByUserId(Long.valueOf(userId)));
    }


    @RequestMapping("/index")
    public String simple(ModelMap map){
        return "hello";
    }

    @RequestMapping("/testMenu")
    @NoAuthMethod
    public Map<String,Object> testMenu(){
        return resultSuccess(sysMenuService.test());
    }
}
