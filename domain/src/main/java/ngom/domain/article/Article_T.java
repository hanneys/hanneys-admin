package ngom.domain.article;


import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

/**
 * @ClassName Article_T
 * @Author LS
 * @Description 类描述: 文章表
 * @Date 2018/9/2 9:33
 * @Version 1.0
 **/
@Data
public class Article_T implements Serializable {

    private static final long serialVersionUID = 5598878473738454939L;

    private Long id;

    /**
     * 作者ID
     */
    private Long userId;

    /**
     * 发布者名称
     */
    private String userName;

    /**
     * 标题
     */
    private String title;

    /**
     * 封面图1
     */
    private String cover1;

    /**
     * 封面图2
     */
    private String cover2;

    /**
     * 封面图3
     */
    private String cover3;

    /**
     * 文章分类(待定)
     */
    private String category;

    /**
     * 来源
     */
    private String source;

    /**
     * 主人翁姓名(,(英文)隔开)
     */
    private String masterName;

    /**
     * 投票总人数(参与人数)
     */
    private int voteCount;

    /**
     * 主人翁应得奖励
     */
    private BigDecimal masterRewardAmount;

    /**
     * 发布时间
     */
    private Timestamp releaseTime;

    /**
     * "发布状态(0 待发布 1 已发布)"
     */
    private Short releaseStatus;

    /**
     * "文章类型(0 国内 1 国际)"
     */
    private Short articleType;

    /**
     * 文章实际贡献总值 (结算后)累加
     */
    private BigDecimal settledTotal;

    /**
     * 文章当天贡献总值 (当天未结算)
     */
    private BigDecimal unsettleAmount;

    /**
     * 最新贡献日期
     */
    private String unsettleDate;

    /**
     * 投票结果(分数比率)
     */
    private BigDecimal voteResult;

    /**
     * 投票截至时间
     */
    private Timestamp voteEndTime;

    /**
     * 挖矿截止时间
     */
    private Timestamp mineEndTime;
    /**
     * 挖矿结束时间
     */
    private Timestamp miningTime;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 更新时间
     */
    private Timestamp updateTime;

    /**
     * 删除标识(0 正常1 删除)
     */
    private Short delFlag;


}
