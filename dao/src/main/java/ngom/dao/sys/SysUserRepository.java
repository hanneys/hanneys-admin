package ngom.dao.sys;

import ngom.domain.sys.SysUser_T;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by hanaijun on 2018/8/29
 */
public interface SysUserRepository extends JpaRepository<SysUser_T,Long> {
}
