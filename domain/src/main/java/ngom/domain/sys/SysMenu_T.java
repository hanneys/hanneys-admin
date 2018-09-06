package ngom.domain.sys;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by hanaijun on 2018/6/6
 */
@Entity
@Data
@Table(name = "sys_menu")
public class SysMenu_T {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "level")
    private Integer level;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;
}
