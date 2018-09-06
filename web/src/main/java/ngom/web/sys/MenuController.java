package ngom.web.sys;

import ngom.config.NoAuthMethod;
import ngom.domain.sys.SysMenu_T;
import ngom.sys.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by hanaijun on 2018/8/27
 */
@Controller
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @RequestMapping("/list")
    @NoAuthMethod
    public String  listUser(Model model) {
        List<SysMenu_T> list=sysMenuService.test();
        model.addAttribute("list",list);
        return "";
    }
}
