package ngom.dao.sys;


import ngom.domain.sys.SysUserMenu_T;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface SysUserMenuMapper {

    int deleteByPrimaryKey(Long id);

    int insertSelective(SysUserMenu_T record);

    SysUserMenu_T selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUserMenu_T record);


    List<SysUserMenu_T> getByUserId(Long userId);
}
