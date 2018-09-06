package ngom.domain.organization;

import lombok.Data;

import java.util.Date;

/**
 * Created by hanaijun on 2018/6/4
 */
@Data
public class Organization_C {

    private Long id;//组织ID
    private Long creator;//创建人
    private String image;//组织头像名
    private String name;//组织名称
    private String desc;//组织简介
    private String inviteCode;//邀请码
    private String personalAddress;//创建者极光链地址
    private String contractAddress;//代币地址
    private String mcmtAddress;//交流币地址
    private String mcbtAddress;//贡献币地址
    private String coinName;//代币名称
    private Double coinNumber;//代币数量
    private Date createTime;//创建时间
    private Short status;//状态 默认0 待审核  10审核通过 20 审核不通过
    private Short deleteFlag;//删除标示 0未删除 1已删除
    private Double exchangeMT;//兑换比例  mt 数量
    private Double exchangeNGOT;//兑换比例 ngot 数量
    private Date updateTime;//修改时间
    private Long memberCount=0L;//成员数量
}
