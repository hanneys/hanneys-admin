package ngom.domain.user;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 邀请统计表
 * Created by hanaijun on 2018/9/3
 */
@Data
public class InviteStatistics_C implements Serializable {

    private static final long serialVersionUID = 5683524957222604566L;

    private Long id;

    // 用户id
    private Long userId;

    // 用户手机号
    private String phone;

    // 用户邮箱
    private String emial;

    // 第三方账号
    private String thirdAccount;

    // 已邀人数
    private Long count;

    // 创建时间
    private Date createTime;

    // 更新时间
    private Date updateTime;


}
