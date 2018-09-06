package ngom.dao.sys;


import ngom.domain.sys.SysMenu_T;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface SysMenuMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(SysMenu_T record);

    SysMenu_T selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysMenu_T record);


    List<SysMenu_T> getByLevel(Integer level);

    List<SysMenu_T> selectAll();
}