package ngom.consult.impl;

import ngom.base.page.Pagination;
import ngom.consult.CommentService;
import ngom.domain.article.ArticleContent_C;
import ngom.domain.article.CommentRecord_C;
import ngom.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private MongoTemplate mongoTemplate;
    /**
     * 查询评论列表
     * @param flag 0 : 时间, 1: 点赞, 2: 举报, 3: 待审核, 4: 已拒绝, 5:已通过
     * @param startTime
     * @param endTime
     * @param content
     * @return
     */
    @Override
    public Pagination<CommentRecord_C> findCommentList(Short flag, String startTime, String endTime, String content, Integer pageNo, Integer pageSize) {
        Query query = new Query();
        //时间
        if (flag == 0){
            query.with(new Sort(Sort.Direction.DESC,"updateTime"));
        }else if (flag == 1){
            //点赞
            query.with(new Sort(Sort.Direction.DESC,"favorCount"));
        }else if (flag == 2){
            //举报
            query.with(new Sort(Sort.Direction.DESC,"reportCount"));
        }else if (flag == 3){
            // 待审核
            query.addCriteria(Criteria.where("auditStatus").is((short)0));
        }else if (flag == 4){
            // 审核失败
            query.addCriteria(Criteria.where("auditStatus").is((short)2));
        }else if (flag == 5){
            // 已通过
            query.addCriteria(Criteria.where("auditStatus").is((short)1));
        }else {
            query.with(new Sort(Sort.Direction.DESC,"updateTime"));
        }
        Date start;
        Date end;
        try {
            if (StringUtils.isEmpty(startTime)) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 10);
                start = calendar.getTime();
            } else {
                start = DateUtils.string2Date(startTime);
            }
            if (StringUtils.isEmpty(endTime)) {
                Calendar calendar = Calendar.getInstance();
                end = calendar.getTime();
            } else {
                end = DateUtils.string2Date(endTime);
            }
            query.addCriteria(Criteria.where("updateTime").gte(start).lte(end));
        }catch (Exception e){
            e.printStackTrace();
        }
        if (!StringUtils.isEmpty(content)){
            query.addCriteria(Criteria.where("content").regex(content));
        }
        //获取总条数
        Long totalCount = mongoTemplate.count(query,CommentRecord_C.class);
        Integer skip = (pageNo - 1) * pageSize;
        // skip相当于从那条记录开始
        query.skip(skip);
        // 从skip开始,取多少条记录
        query.limit(pageSize);
        List<CommentRecord_C> data = mongoTemplate.find(query, CommentRecord_C.class);
        Pagination<CommentRecord_C> page = new Pagination<>(pageNo,pageSize,totalCount);
        page.build(data);
        return page;
    }

    /**
     * 查询评论详情
     * @param id
     * @return
     */
    @Override
    public Map<String, Object> findCommentById(Long id) {
        Map<String, Object> resultMap = new HashMap<>();
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        CommentRecord_C recordC = mongoTemplate.findOne(query, CommentRecord_C.class);
        ArticleContent_C contentC = mongoTemplate.findOne(query, ArticleContent_C.class);
        String title = null;
        if (!StringUtils.isEmpty(contentC)){
            title = contentC.getTitle();
        }
        resultMap.put("title", title);
        resultMap.put("recordC", recordC);
        return resultMap;
    }
}
