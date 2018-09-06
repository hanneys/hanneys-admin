package ngom.sys;

import ngom.domain.sys.SysMenu_T;
import ngom.dto.sys.MenuList;
import ngom.dto.sys.SysMenu;

import java.util.List;

/**
 * Created by hanaijun on 2018/6/6
 */
public interface SysMenuService {
    void addMenu(SysMenu sysMenu);

    List<MenuList> menuListByUserId(Long userId);

    List<SysMenu_T> test();
}
