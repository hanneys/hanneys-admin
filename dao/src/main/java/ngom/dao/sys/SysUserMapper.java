package ngom.dao.sys;

import ngom.domain.sys.SysUser_T;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by hanaijun on 2018/6/3
 */
@Component
@Mapper
public interface SysUserMapper  {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(SysUser_T record);

    SysUser_T selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUser_T record);

    SysUser_T getByEmail(String email);

    List<SysUser_T> selectAll();
}
