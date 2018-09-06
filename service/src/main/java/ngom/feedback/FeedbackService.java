package ngom.feedback;

import ngom.base.page.Pagination;
import ngom.domain.feedback.Feedback_C;
import ngom.dto.base.SelectBase;
import ngom.dto.feedback.FeedbackInfo;


/**
 * Created by hanaijun on 2018/6/5
 */
public interface FeedbackService {
    Pagination<FeedbackInfo> getFeedbackList(SelectBase selectBase);


    void updateFeedbackStatus(Long id, Integer status);

    Feedback_C getById(Long id);
}
