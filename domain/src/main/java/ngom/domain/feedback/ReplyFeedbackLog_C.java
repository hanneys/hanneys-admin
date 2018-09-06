package ngom.domain.feedback;

import lombok.Data;

import java.util.Date;

/**
 * 反馈内容
 * Created by hanaijun on 2018/6/6
 */
@Data
public class ReplyFeedbackLog_C {

    private Long id;
    // 反馈id
    private Long fbId;
    // 回复人id
    private Long userId;
    // 回复内容
    private String content;
    // 创建时间
    private Date createTime;
    // 更新时间
    private Date updateTime;
}
