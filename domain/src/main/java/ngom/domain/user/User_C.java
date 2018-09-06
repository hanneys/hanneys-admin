package ngom.domain.user;




import lombok.Data;

import java.io.Serializable;
import java.util.Date;
/**
 * @author ngom
 */
@Data
public class User_C implements Serializable {

    private Long id;
    // 邮箱 索引、唯一标识
    private String email;
    // 密码
    private String password;
    // 邀请码
    private String inviteCode;

    // 自身邀请码
    private String identityCode;
    // 姓名
    private String name;
    // 性别 0 女 1 男
    private Short gender;
    // 生日
    private Date birthday;
    // 职务
    private String position;

    // 电话
    private String phone;
    // 国家代码
    private String areaCode;
    // 头像图片
    private String image;

    // 个性签名
    private String signatures;

    // 冻结状态 0：未冻结 1：已冻结
    private Short freezed;

    // 禁言状态 0:未禁言 1:已禁言
    private Short banned;

    // 注册来源
    private Short registerSource;

    // 一级邀请人  索引
    private Long inviterFirst;

    // 二级邀请人  索引
    private Long inviterSecond;

    // 来源
    private Short source;

    // 第三方账号
    private String thirdAccount;

    //创建时间
    private Date createTime;
    //修改时间
    private Date updateTime;


}
