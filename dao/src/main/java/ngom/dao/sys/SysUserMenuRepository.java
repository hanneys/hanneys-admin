package ngom.dao.sys;

import ngom.domain.sys.SysUserMenu_T;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by hanaijun on 2018/8/29
 */
public interface SysUserMenuRepository extends JpaRepository<SysUserMenu_T,Long> {
}
