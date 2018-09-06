package ngom.dto.feedback;

import lombok.Data;

import java.util.Date;
import java.util.Map;

/**
 * Created by hanaijun on 2018/6/6
 */
@Data
public class FeedbackInfo {

    private String id;

    private Date createTime;

    private Map<String,Map<String,Object>> mapContent;

    // 状态 0 未回复 1 已回复
    private Short status;

    private String statusName;
}
