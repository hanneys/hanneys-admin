package ngom.domain.article;

import java.util.Date;

/**
 * 审核 评论、回复 日志表
 * Created by hanaijun on 2018/8/30
 */
public class AuditReplyLog_C {

    private Long id;

    // 文章id
    private Long articleId;

    // 来源id(属于哪条评论,如本身是评论,则与replyId相同)
    private Long sourceId;

    // 来源类型 (评论,回复)
    private Short sourceType;

    // 当前内容id
    private Long replyId;

    // 审核操作  1:已通过 2:拒绝
    private Short status;

    // 审核人
    private Long userId;

    // 审核备注
    private String remark;

    // 创建时间
    private Date createTime;

    private Date updateTime;
}
