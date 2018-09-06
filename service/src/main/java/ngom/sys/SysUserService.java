package ngom.sys;

import ngom.domain.sys.SysUser_T;
import ngom.dto.sys.SysUser;
import ngom.base.page.PageInfo;

/**
 * Created by hanaijun on 2018/6/3
 */
public interface SysUserService {
    SysUser_T getSysUserByEmail(String email);

    void insert(SysUser_T sysUser);

    void update(SysUser_T sysUser);


    void addUser(SysUser sysUser);

    PageInfo<SysUser_T> getAll(SysUser sysUser);

    SysUser_T getById(Long id);

    void updatePassword(Long id, String oldPassword, String newPassword);
}
