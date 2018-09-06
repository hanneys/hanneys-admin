package ngom.domain.article;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class ArticleVo {

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
     * 发布时间
     */
    private Timestamp releaseTime;

    /**
     * "发布状态(0 待发布 1 已发布)"
     */
    private Short releaseStatus;

    /**
     * 文章实际贡献总值 (结算后)累加
     */
    private BigDecimal settledTotal;

    /**
     * 文章当天贡献总值 (当天未结算)
     */
    private BigDecimal unsettleAmount;
    /**
     * 文章内容集合
     */
    private ArticleContent_C articleContentC;
}
