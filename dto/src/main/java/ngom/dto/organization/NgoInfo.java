package ngom.dto.organization;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created by hanaijun on 2018/6/4
 */
@Data
public class NgoInfo {

    private String id;
    // 图片
    private String image;
    // 组织名称
    private String name;
    // 组织简介
    private String desc;
    // 代币名称
    private String coinName;
    // 代币数量
    private Double coinNumber;
    // 兑换比例 ngot 数量
    private Double exchangeNGOT;
    // 创建时间
    private Date createTime;
    // 状态
    private Short status;
    // 状态名称
    private String statusName;
    //
    private String remark;

    private String auditer;

    private String personalAddress;//创建者极光链地址

    // 合约地址
    private String contractAddress;

    // 职位
    private List<Position> positions;


}
