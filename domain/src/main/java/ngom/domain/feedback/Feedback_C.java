package ngom.domain.feedback;

import lombok.Data;

import java.util.Date;

/**
 * Created by hanaijun on 2018/9/2
 */
@Data
public class Feedback_C {

    private Long id;

    private Long userId;

    private String content;

    /**
     * 状态 0 未回复 1 已回复
     */
    private Short status;

    private Date createTime;

}
