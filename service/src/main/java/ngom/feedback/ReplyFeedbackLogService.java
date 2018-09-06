package ngom.feedback;

import ngom.domain.feedback.ReplyFeedbackLog_C;

/**
 * Created by hanaijun on 2018/6/6
 */
public interface ReplyFeedbackLogService {
    void reply(Long fbId, String content, Long userId);

    ReplyFeedbackLog_C getByFbId(Long fbId);
}
