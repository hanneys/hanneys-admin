package ngom.feedback.impl;

import ngom.base.BaseService;
import ngom.base.feedback.FeedBackStatusEnum;
import ngom.base.page.Pagination;
import ngom.domain.feedback.Feedback_C;
import ngom.domain.feedback.ReplyFeedbackLog_C;
import ngom.dto.base.SelectBase;
import ngom.dto.feedback.FeedbackInfo;
import ngom.feedback.FeedbackService;
import ngom.feedback.ReplyFeedbackLogService;
import ngom.sys.SysUserService;
import ngom.user.UserService;
import ngom.utils.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * Created by hanaijun on 2018/6/5
 */
@Service
public class FeedbackServiceImpl extends BaseService implements FeedbackService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private ReplyFeedbackLogService replyFeedbackLogService;

    @Autowired
    private UserService userService;

    @Autowired
    private SysUserService sysUserService;


    /**
     * 用户反馈列表
     * @param selectBase
     * @return
     */
    @Override
    public Pagination<FeedbackInfo> getFeedbackList(SelectBase selectBase){

        Query query = new Query();
        if(selectBase.getStatus()!=null){
            query.addCriteria(Criteria.where("status").is(selectBase.getStatus()));
        }

        setQueryDate(selectBase.getCreateTimeStart(),selectBase.getCreateTimeEnd(),query);

        query.with(new Sort(Sort.Direction.ASC,"updateTime"));

        //获取总条数
        Long totalCount = mongoTemplate.count(query,Feedback_C.class);
        Integer skip = (selectBase.getPageNo() - 1) * selectBase.getPageSize();
        // skip相当于从那条记录开始
        query.skip(skip);
        // 从skip开始,取多少条记录
        query.limit(selectBase.getPageSize());

        List<Feedback_C> data = mongoTemplate.find(query, Feedback_C.class);

        List<FeedbackInfo> res=data.stream().map(l->{
            FeedbackInfo f = new FeedbackInfo();
            BeanUtils.copyProperties(l,f);
            f.setId(l.getId().toString());
            f.setStatusName(FeedBackStatusEnum.getValueByKey(f.getStatus()));
            f.setMapContent(getReplyMap(l));
            return f;
        }).collect(Collectors.toList());
        Pagination<FeedbackInfo> page = new Pagination<>(selectBase.getPageNo(), selectBase.getPageSize(),totalCount);
        page.build(res);

        return page;
    }


    private   Map<String,Map<String,Object>> getReplyMap(Feedback_C fb){
        Map<String, Map<String, Object>> map = new HashMap<>();
        Map<String, Object> fbMap = new HashMap<>();
        fbMap.put("name",userService.getById(fb.getUserId()).getName());
        fbMap.put("date",fb.getCreateTime());
        fbMap.put("content",fb.getContent());
        map.put("fb",fbMap);

        // 客服回复
        ReplyFeedbackLog_C rfbl = replyFeedbackLogService.getByFbId(fb.getId());
        if(rfbl!=null){
            Map<String, Object> rfblMap = new HashMap<>();
            rfblMap.put("name",sysUserService.getById(rfbl.getUserId()).getName());
            rfblMap.put("date",rfbl.getCreateTime());
            rfblMap.put("content",rfbl.getContent());
            map.put("reply",rfblMap);
        }

        return map;
    }



    /**
     * 修改反馈状态
     * @param id
     * @param status
     */
    @Override
    public void updateFeedbackStatus(Long id,Integer status){
        Query query=new Query(Criteria.where("id").is(id));
        Update update=new Update();
        update.set("status",status);
        update(query,update);
    }



    private void update(Query query, Update update){
        mongoTemplate.updateFirst(query,update,Feedback_C.class);
    }


    @Override
    public Feedback_C getById(Long id){
        return mongoTemplate.findById(id,Feedback_C.class);
    }

}
