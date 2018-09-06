package ngom.web.user;

import ngom.dto.base.SelectBase;
import ngom.user.InviteStatisticsService;
import ngom.user.UserService;
import ngom.web.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 邀请管理
 * Created by hanaijun on 2018/9/3
 */
@RestController
@RequestMapping("inviteStatistics")
public class InviteStatisticsController extends BaseController {

    @Autowired
    private InviteStatisticsService inviteStatisticsService;

    @Autowired
    private UserService userService;



    @RequestMapping("/getInviteStatistics")
    public Map<String,Object> getInviteStatistics(SelectBase selectBase){
        return resultSuccess(inviteStatisticsService.getInviteStatistics(selectBase));
    }


    @RequestMapping("/getUsersByInviteFirst")
    public Map<String,Object> getInviteStatistics(SelectBase selectBase, @RequestParam("userId") String userId){
        return resultSuccess(userService.getUsersByInviteFirst(Long.valueOf(userId),selectBase));
    }


}
