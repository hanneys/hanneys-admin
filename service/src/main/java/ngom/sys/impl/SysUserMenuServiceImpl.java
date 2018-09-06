package ngom.sys.impl;

import ngom.dao.sys.SysUserMenuMapper;
import ngom.dao.sys.SysUserMenuRepository;
import ngom.domain.sys.SysUserMenu_T;
import ngom.sys.SysUserMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by hanaijun on 2018/6/8
 */
@Service
public class SysUserMenuServiceImpl implements SysUserMenuService {

    @Autowired
    private SysUserMenuMapper sysUserMenuMapper;

    @Autowired
    private SysUserMenuRepository sysUserMenuRepository;

    @Override
    public List<SysUserMenu_T> getByUserId(Long userId){
        return sysUserMenuMapper.getByUserId(userId);
    }

    @Override
    public List<Long> getMenuIdListByUserId(Long userId){
        return getByUserId(userId).stream().map(SysUserMenu_T::getMenuId).collect(Collectors.toList());
    }


    @Override
    public void insertList(List<SysUserMenu_T> list){
        sysUserMenuRepository.save(list);
    }
}
