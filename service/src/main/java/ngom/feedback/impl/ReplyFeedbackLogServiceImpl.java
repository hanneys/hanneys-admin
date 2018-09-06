package ngom.feedback.impl;

import ngom.base.feedback.FeedBackStatusEnum;
import ngom.domain.article.Message_C;
import ngom.domain.feedback.Feedback_C;
import ngom.domain.feedback.ReplyFeedbackLog_C;
import ngom.domain.sys.SysUser_T;
import ngom.feedback.FeedbackService;
import ngom.feedback.ReplyFeedbackLogService;
import ngom.sys.SysUserService;
import ngom.user.UserMessageService;
import ngom.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by hanaijun on 2018/6/6
 */
@Service
public class ReplyFeedbackLogServiceImpl implements ReplyFeedbackLogService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private UserMessageService userMessageService;


    /**
     * 回复反馈内容
     * @param fbId
     * @param content
     * @param userId 回复人id
     */
    @Override
    public void reply(Long fbId,String content,Long userId){
        ReplyFeedbackLog_C rfbl = new ReplyFeedbackLog_C();
        rfbl.setId(IdWorker.getIdWorker().nextId());
        rfbl.setFbId(fbId);
        rfbl.setUserId(userId);
        rfbl.setContent(content);
        rfbl.setCreateTime(new Date());
        rfbl.setUpdateTime(new Date());
        insert(rfbl);
        // 修改反馈状态
        feedbackService.updateFeedbackStatus(fbId, FeedBackStatusEnum.RETURNED.getStatus());

        Feedback_C feedbackC = feedbackService.getById(fbId);
        // 添加消息表
        Message_C messageC=new Message_C();
        messageC.setId(IdWorker.getIdWorker().nextId());
        messageC.setUserId(feedbackC.getUserId());
        messageC.setSourceId(fbId);
        messageC.setType(Short.valueOf("1"));
        messageC.setReplyId(userId);
        SysUser_T sysUserT = sysUserService.getById(userId);
        messageC.setReplyName(sysUserT.getName());
        messageC.setContent(content);
        messageC.setReadStatus(Short.valueOf("0"));
        messageC.setDelFlag(Short.valueOf("0"));
        messageC.setCreateTime(new Date());
        messageC.setUpdateTime(new Date());
        userMessageService.insert(messageC);
    }

    @Override
    public ReplyFeedbackLog_C getByFbId(Long fbId){
        Query query = new Query(Criteria.where("fbId").is(fbId));
        return mongoTemplate.findOne(query,ReplyFeedbackLog_C.class);
    }


    private void insert(ReplyFeedbackLog_C rfbl){
        mongoTemplate.insert(rfbl);
    }
}
