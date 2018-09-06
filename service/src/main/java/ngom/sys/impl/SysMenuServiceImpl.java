package ngom.sys.impl;

import ngom.dao.sys.SysMenuMapper;
import ngom.dao.sys.SysMenuRepository;
import ngom.domain.sys.SysMenu_T;
import ngom.domain.sys.SysUserMenu_T;
import ngom.dto.sys.MenuList;
import ngom.dto.sys.SysMenu;
import ngom.sys.SysMenuService;
import ngom.sys.SysUserMenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by hanaijun on 2018/6/6
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysUserMenuService sysUserMenuService;

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private SysMenuRepository sysMenuRepository;

    @Override
    public void addMenu(SysMenu sysMenu){
        SysMenu_T sysMenuT = new SysMenu_T();
        BeanUtils.copyProperties(sysMenu,sysMenuT);
        sysMenuT.setCreateTime(new Date());
        sysMenuT.setUpdateTime(new Date());
        insert(sysMenuT);
    }

    private void insert(SysMenu_T sysMenuT){
        sysMenuMapper.insertSelective(sysMenuT);
    }


    public List<SysMenu_T> getAll(){
        return sysMenuMapper.selectAll();
    }

    @Override
    public List<MenuList> menuListByUserId(Long userId){
        // 用户拥有的菜单ids
        List<Long> umList = sysUserMenuService.getMenuIdListByUserId(userId);
        // 顶级菜单ids
        List<SysMenu_T> sysMenuTList =sysMenuMapper.getByLevel(0);
        // 所有的菜单
        Map<Long, List<SysMenu_T>> map = getParentIdMap();

        List<MenuList> rs = new ArrayList<>();


        sysMenuTList.forEach(l->{
            if(umList.contains(l.getId())){
                MenuList menuList=new MenuList();
                BeanUtils.copyProperties(l,menuList);
                List<SysMenu_T> child=map.get(l.getId());
                if(!CollectionUtils.isEmpty(child)){
                    List<MenuList> list = new ArrayList<>();
                    child.forEach(cl->{
                        MenuList ml=new MenuList();
                        BeanUtils.copyProperties(cl,ml);
                        list.add(ml);
                    });
                    menuList.setMenuList(list);
                }
                rs.add(menuList);
            }

        });
        return rs;

    }

    /**
     * 构建 key:parentId  value:List<SysMenu_T>
     * @return
     */
    public Map<Long,List<SysMenu_T>> getParentIdMap(){
        List<SysMenu_T> list=getAll();
        Map<Long, List<SysMenu_T>> map = new HashMap<>();
        list.forEach(d->{
            List<SysMenu_T> rs = map.get(d.getParentId());
            if(!CollectionUtils.isEmpty(rs)){
                rs.add(d);
            }else{
                rs = new ArrayList<>();
                rs.add(d);
            }
            map.put(d.getParentId(),rs);
        });
        return map;
    }


    public Map<Long,List<SysMenu_T>> getIdMap(){
        List<SysMenu_T> list=getAll();
        Map<Long, List<SysMenu_T>> map = new HashMap<>();
        list.forEach(d->{
            List<SysMenu_T> rs = map.get(d.getId());
            if(!CollectionUtils.isEmpty(rs)){
                rs.add(d);
            }else{
                rs = new ArrayList<>();
                rs.add(d);
            }
            map.put(d.getId(),rs);
        });
        return map;
    }


    @Override
    public List<SysMenu_T> test(){
        return sysMenuRepository.findAll();
    }

}
