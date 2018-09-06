package ngom.sys.impl;

import lombok.extern.slf4j.Slf4j;
import ngom.base.page.Pagination;
import ngom.domain.sys.SensitiveWords_C;
import ngom.dto.sys.Sensitive;
import ngom.sys.SensitiveWordsService;
import ngom.utils.FastJsonUtil;
import ngom.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.SecondaryTable;
import java.util.*;

@Slf4j
@Service
public class SensitiveWordsServiceImpl implements SensitiveWordsService {

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 查询敏感词列表
     * @param sensitive
     * @return
     */
    @Override
    public Pagination<SensitiveWords_C> findSensitiveWordsList(Sensitive sensitive) {
        String blackWords = sensitive.getBlackWords();
        Query query = new Query();
        query.with(new Sort(Sort.Direction.DESC,"createTime"));


//        if(!StringUtils.isEmpty(blackWords)){
//
//        }
        if (blackWords != null || blackWords != ""){
            query.addCriteria(Criteria.where("blackWords").regex(blackWords));
        }
        Integer pageNo = sensitive.getPageNo();
        Integer pageSize = sensitive.getPageSize();

        //获取总条数
        Long totalCount = mongoTemplate.count(query,SensitiveWords_C.class);
        Integer skip = (pageNo - 1) * pageSize;
        // skip相当于从那条记录开始
        query.skip(skip);
        // 从skip开始,取多少条记录
        query.limit(pageSize);
        List<SensitiveWords_C> data = mongoTemplate.find(query, SensitiveWords_C.class);

        Pagination<SensitiveWords_C> page = new Pagination<>(pageNo,pageSize,totalCount);
        page.build(data);
        return page;
    }

    @Override
    public void deleteSensitiveWords(Sensitive sensitive) {
        Query query = Query.query(Criteria.where("_id").is(sensitive.getId()));
        mongoTemplate.remove(query, SensitiveWords_C.class);
        redisSensitiveWords();
    }

    @Override
    public void addSensitiveWords(String sensitive) {
        log.info("插入的敏感词为 sensitive:::>>>{}", sensitive);
        List<SensitiveWords_C> list = new ArrayList<>();
        String[] strings = sensitive.split(",");
        for (String obj : strings){
            if (obj!=null || obj != ""){
                SensitiveWords_C words = new SensitiveWords_C();
                words.setId(IdWorker.getIdWorker().nextId());
                words.setBlackWords(obj);
                Date date = new Date();
                words.setCreateTime(date);
                words.setUpdateTime(date);
                list.add(words);
            }
        }
        mongoTemplate.insert(list);
        redisSensitiveWords();
    }

    /**
     * redis 中放入敏感词
     */
    private void redisSensitiveWords(){
        Query query = new Query();
        List<SensitiveWords_C> wordsCS = mongoTemplate.find(query, SensitiveWords_C.class);
        Set<String> set = new TreeSet<>();
        for (SensitiveWords_C wordsC : wordsCS){
            String blackWords = wordsC.getBlackWords();
            set.add(blackWords);
        }
        String json = FastJsonUtil.toJson(set);
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        ops.set("readSensitiveWord", json);
    }
}
