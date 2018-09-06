package ngom.domain.article;

import lombok.Data;


import java.util.Date;

/**
 * 消息表
 * Created by hanaijun on 2018/8/30
 */
@Data
public class Message_C {

    private Long id;

    // 用户id
    private Long userId;

    // 文章id
    private Long articleId;

    // 文章图片
    private String articleImage;

    // 来源id(就是那条回复或者评论的id，如果是官网回复则是反馈的id)
    private Long sourceId;

    // 消息类型(官方回复 0,回复 1,点赞 2,评论 3)
    private Short type;

    // 回复人id(如官网回复则为后台管理员的id)
    private Long replyId;

    // 回复人名称
    private String replyName;

    // 回复人头像
    private Long replyImage;

    // 回复内容
    private String content;

    // 已读状态 0: 未读 1:已读
    private Short readStatus;

    // 用户删除状态 0:未删除 1:已删除
    private Short delFlag;

    private Date createTime;

    private Date updateTime;


}
