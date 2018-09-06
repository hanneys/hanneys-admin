package ngom.domain.sys;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 系统用户表
 * Created by hanaijun on 2018/6/3
 */
@Entity
@Data
@Table(name = "sys_user")
public class SysUser_T {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    // 职务
    @Column(name = "position")
    private String position;

    @Column(name = "phone")
    private String phone;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;
}
