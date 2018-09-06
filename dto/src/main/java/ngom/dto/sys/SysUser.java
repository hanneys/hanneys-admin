package ngom.dto.sys;

import lombok.Data;
import ngom.dto.base.BaseEntity;

import javax.persistence.Column;

/**
 * Created by hanaijun on 2018/6/3
 */
@Data
public class SysUser extends BaseEntity {


    private String email;

    private String password;

    private String newPassword;

    private String name;

    private Integer[] permissionIds;

    private String position;

    private String phone;

}
