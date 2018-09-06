package ngom.domain.sys;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by hanaijun on 2018/6/6
 */

@Data
@Entity
@Table(name = "sys_user_menu")
public class SysUserMenu_T {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "menu_id")
    private Long menuId;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;
}
