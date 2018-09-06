package ngom.domain.article;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName CommentRecord_C
 * @Author LS
 * @Description 类描述: 评论记录表
 * @Date 2018/9/2 10:26
 * @Version 1.0
 **/
@Data
public class CommentRecord_C implements Serializable {

    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 文章ID
     */
    private Long articleId;

    /**
     * 内容
     */
    private String content;

    /**
     * 备注
     */
    private String remark;

    /**
     * 用户头像
     */
    private String userImage;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * "评论类型(0 评论 1 回复)"
     */
    private Short commentType;

    /**
     * "用户删除标识(0 正常 1 删除)"
     */
    private Short userDelFlag;

    /**
     * "审核状态(0 待审核 1 审核通过 2 审核未通过)"
     */
    private Short auditStatus;

    /**
     * 被回复用户ID
     */
    private Long toUserId;

    /**
     * 被回复评论ID
     */
    private Long commentId;

    /**
     * 被回复用户昵称
     */
    private String toNickName;

    /**
     * 被回复用户头像
     */
    private String toUserImage;

    /**
     * 点赞总数
     */
    private BigDecimal favorCount;

    /**
     * 举报总数
     */
    private BigDecimal reportCount;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;


}
