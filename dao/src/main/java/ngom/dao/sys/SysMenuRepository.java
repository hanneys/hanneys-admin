package ngom.dao.sys;

import ngom.domain.sys.SysMenu_T;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by hanaijun on 2018/8/27
 * @author ngom
 */
public interface SysMenuRepository extends JpaRepository<SysMenu_T,Long> {

}
