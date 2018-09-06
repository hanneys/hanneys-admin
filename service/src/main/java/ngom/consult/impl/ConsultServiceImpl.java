package ngom.consult.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import ngom.base.page.PageInfo;
import ngom.base.page.Pagination;
import ngom.consult.ConsultService;
import ngom.dao.sys.ConsultMapper;
import ngom.domain.article.*;
import ngom.domain.sys.ConsultLabel_C;
import ngom.domain.sys.SysUser_T;
import ngom.utils.DateUtils;
import ngom.utils.FastJsonUtil;
import ngom.utils.IdWorker;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.awt.geom.Ellipse2D;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.*;

/**
 * @author  zj
 */
@Slf4j
@Service
public class ConsultServiceImpl implements ConsultService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private ConsultMapper consultMapper;

    /**
     * 查询咨询标签
     * @return
     */
    @Override
    public List<ConsultLabel_C> findConsultLabelList() {
        Query query = new Query();
        List<ConsultLabel_C> labelCS = mongoTemplate.find(query, ConsultLabel_C.class);
        return labelCS;
    }

    @Override
    public void addConsult(String title, String contents, String coverUrls, String source, String masters, String labelNames, String userId, String userName) {
        log.info("添加咨询 title:::>>>{},contents:::>>>{},coverUrls:::>>>{},source:::>>>{},masters:::>>>{},labelNames:::>>>{},userId:::>>>{},userName:::>>>{}",
                title, contents, coverUrls, source, masters, labelNames, userId, userName);
        Article_T articleT = new Article_T();
        long id = IdWorker.getIdWorker().nextId();
        articleT.setId(id);
        articleT.setTitle(title);
        ArticleContent_C articleContentC = new ArticleContent_C();
        articleContentC.setTitle(title);
        if (!StringUtils.isEmpty(contents)){
            List<Content> contentList = FastJsonUtil.parseList(contents, Content.class);
            log.info("contentList:::>>>{}", JSON.toJSON(contentList));
            articleContentC.setId(id);
            articleContentC.setContentList(contentList);
        }
        if (!StringUtils.isEmpty(labelNames)){
            Set<String> setLabels = new TreeSet<>();
            List<String> list = FastJsonUtil.parseList(labelNames, String.class);
            setLabels.addAll(list);
            articleContentC.setTags(setLabels);
        }
        Date date = new Date();
        articleContentC.setCreateTme(date);
        articleContentC.setUpdateTime(date);
        if (!StringUtils.isEmpty(coverUrls)){
            String[] split = coverUrls.split(",");
            int length = split.length;

            for (int i = 0; i < length; i++){
                String url = split[i];
                if (i == 0){
                    articleT.setCover1(url);
                }else if (i == 1){
                    articleT.setCover2(url);
                }else if (i == 2){
                    articleT.setCover3(url);
                }
            }
        }
        BigDecimal zero = BigDecimal.ZERO;
        articleT.setUserName(userName);
        articleT.setArticleType((short)1);
        articleT.setCategory("");
        articleT.setDelFlag((short)0);
        articleT.setMasterName(masters);
        articleT.setMasterRewardAmount(zero);
        articleT.setMineEndTime(null);
        articleT.setMiningTime(null);
        //先保存为 未发布 0:未发布, 1: 已发布
        articleT.setReleaseStatus((short)0);
        articleT.setSettledTotal(zero);
        articleT.setSource(source);
        articleT.setUnsettleAmount(zero);
        articleT.setUnsettleDate(null);
        // StringUtils.isEmpty(userId)? null:Long.valueOf(userId)
        if (StringUtils.isEmpty(userId) && "null".equals(userId)){
            articleT.setUserId(0L);
        }else {
            articleT.setUserId(Long.valueOf(userId));
        }

        articleT.setVoteCount(0);
        //投片截止时间 14 天
        articleT.setVoteEndTime(null);
        Timestamp timestamp = new Timestamp(date.getTime());
        articleT.setCreateTime(timestamp);
        articleT.setUpdateTime(timestamp);
        articleT.setReleaseTime(timestamp);
        articleT.setVoteResult(zero);
        consultMapper.insertSelective(articleT);
        mongoTemplate.insert(articleContentC);
    }

    /**
     * 查询咨询列表
     * @param title 文章标题
     * @param releaseStatus 是否发布 0: 未; 1: 已发
     * @param delFlag 是否删除 0: 未; 1: 已发
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param pageNo 当前页
     * @param pageSize 每页多少条
     * @return
     */
    @Override
    public PageInfo<Article_T> findConsultList(String title, Short releaseStatus, Short delFlag, String startTime, String endTime, Integer pageNo, Integer pageSize) {
        log.info("查询咨询列表 title::::>>>{},releaseStatus::::>>>{},startTime::::>>>{},endTime::::>>>{},pageNo::::>>>{},pageSize::::>>>{}", title, releaseStatus, startTime, endTime, pageNo, pageSize);
        PageHelper.startPage(pageNo, pageSize);
        List<Article_T> list= null;
        Date start;
        Date end;
        try {
            if (StringUtils.isEmpty(startTime)){
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 7);
                start = calendar.getTime();
            }else {
                start = DateUtils.string2Date(startTime);
            }
            if (StringUtils.isEmpty(endTime)){
                Calendar calendar = Calendar.getInstance();
                end = calendar.getTime();
            }else {
                end = DateUtils.string2Date(endTime);
            }
            list = consultMapper.selectArticleList(title, releaseStatus, start, end);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new PageInfo<>(list);
    }

    @Override
    public ArticleVo selectConsultById(Long id) {
        log.info("查询文章详情 id:::>>>{}", id);
        Article_T articleT = consultMapper.selectConsultById(id);
        ArticleVo articleVo = new ArticleVo();
        BeanUtils.copyProperties(articleT, articleVo);
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        ArticleContent_C articleContentC = mongoTemplate.findOne(query, ArticleContent_C.class);
        articleVo.setArticleContentC(articleContentC);
        return articleVo;
    }

    @Override
    public void updateConsultById(ArticleVo articleVo, String contents, String labelNames) {
        log.info("更新咨询内容 articleVo::::>>>{}, contents:::>>>{}, labelNames:::>>>{}",JSON.toJSONString(articleVo), contents, labelNames);
        String title = articleVo.getTitle();
        Long id = articleVo.getId();
        if (StringUtils.isEmpty(id)){
            return;
        }
        consultMapper.updateConsultById(articleVo);

        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        Update update = new Update();
        if (!StringUtils.isEmpty(title)){
            update.set("title", title);
        }
        if (!StringUtils.isEmpty(contents)){
            List<Content> contentList = FastJsonUtil.parseList(contents, Content.class);
            log.info("contentList:::>>>{}", JSON.toJSON(contentList));
            update.set("contentList", contentList);
        }
        if (!StringUtils.isEmpty(labelNames)){
            Set<String> setLabels = new TreeSet<>();
            List<String> list = FastJsonUtil.parseList(labelNames, String.class);
            setLabels.addAll(list);
            update.set("tags", setLabels);
        }
        log.info("根据id更新文章 query:::>>>{}, update:::>>>{}", JSON.toJSONString(query), JSON.toJSONString(update));
        update.set("updateTime", new Date());
        mongoTemplate.updateFirst(query, update, ArticleContent_C.class);
    }

    /**
     * 1: 删除, 2: 发布
     * @param flag
     */
    @Override
    public void delReleaseConsult(Long id, String flag) {
        log.info("删除或者更新文章 id:::>>>{}, flag:::>>>{}", id, flag);
        //删除
        Date date = new Date();
        if ("1".equals(flag)){
            /**
             * 删除标识(0 正常1 删除)
             */
            Short delFlag = 1;
            consultMapper.delReleaseConsult(id, null, delFlag, null, date);
        }
        //发布
        if ("2".equals(flag)){
            /**
             * "发布状态(0 待发布 1 已发布)"
             */

            Short releaseStatus = 1;
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 14);
            Date time = calendar.getTime();
            consultMapper.delReleaseConsult(id, releaseStatus, null, time, date);
        }
    }

    @Override
    public void InsertConsultLabel(List<ConsultLabel_C> list) {
        mongoTemplate.insert(list, ConsultLabel_C.class);
    }

}
