package ngom.sys.impl;

import com.github.pagehelper.PageHelper;
import ngom.dao.sys.SysUserMapper;
import ngom.dao.sys.SysUserRepository;
import ngom.domain.sys.SysUserMenu_T;
import ngom.domain.sys.SysUser_T;
import ngom.dto.sys.SysUser;
import ngom.base.page.PageInfo;
import ngom.exception.ErrorInfo;
import ngom.exception.NgomAdminException;
import ngom.sys.SysUserMenuService;
import ngom.sys.SysUserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by hanaijun on 2018/6/3
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysUserMenuService sysUserMenuService;

    @Autowired
    private SysUserRepository sysUserRepository;

    @Override
    public SysUser_T getSysUserByEmail(String email){
        return sysUserMapper.getByEmail(email);
    }


    @Override
    public void insert(SysUser_T sysUser){
        sysUserMapper.insertSelective(sysUser);
    }

    @Override
    public void update(SysUser_T sysUser){
        sysUserMapper.updateByPrimaryKeySelective(sysUser);
    }


    @Override
    public void addUser(SysUser sysUser){
        SysUser_T sysUserT = new SysUser_T();
        BeanUtils.copyProperties(sysUser, sysUserT);
        sysUserT=sysUserRepository.save(sysUserT);

        insertUserMenuByIds(sysUser.getPermissionIds(),sysUserT.getId());
    }


    private void insertUserMenuByIds(Integer[] permissionIds,Long userId){
        List<SysUserMenu_T> list = new ArrayList<>();
        for(Integer id:permissionIds){
            SysUserMenu_T sysUserMenu = new SysUserMenu_T();
            sysUserMenu.setUserId(userId);
            sysUserMenu.setMenuId(Long.valueOf(id));
            sysUserMenu.setCreateTime(new Date());
            sysUserMenu.setUpdateTime(new Date());
            list.add(sysUserMenu);
        }
        sysUserMenuService.insertList(list);
    }





    @Override
    public PageInfo<SysUser_T> getAll(SysUser sysUser){
        PageHelper.startPage(sysUser.getPageNo(),sysUser.getPageSize());

        List<SysUser_T> list = sysUserMapper.selectAll();

        return new PageInfo<>(list);
    }

    @Override
    public SysUser_T getById(Long id){
        return sysUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updatePassword(Long id, String oldPassword, String newPassword){
        SysUser_T sysUser=getById(id);
        if(!sysUser.getPassword().equals(DigestUtils.md5Hex(oldPassword))){
            throw new NgomAdminException(ErrorInfo.USER_PASSWORD_ERROR_ERR_CODE,ErrorInfo.USER_PASSWORD_ERROR_ERR_INFO);
        }
        sysUser.setPassword(DigestUtils.md5Hex(newPassword));
        update(sysUser);

    }



}
