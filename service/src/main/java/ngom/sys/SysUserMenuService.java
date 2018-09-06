package ngom.sys;

import ngom.domain.sys.SysUserMenu_T;

import java.util.List;

/**
 * Created by hanaijun on 2018/6/8
 */
public interface SysUserMenuService {
    List<SysUserMenu_T> getByUserId(Long userId);

    List<Long> getMenuIdListByUserId(Long userId);

    void insertList(List<SysUserMenu_T> list);
}
