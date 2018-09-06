package ngom.user.impl;

import lombok.extern.slf4j.Slf4j;
import ngom.base.BaseService;
import ngom.base.page.Pagination;
import ngom.domain.user.InviteStatistics_C;
import ngom.dto.base.SelectBase;
import ngom.dto.user.InviteStatistics;
import ngom.user.InviteStatisticsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by hanaijun on 2018/9/3
 */
@Service
@Slf4j
public class InviteStatisticsServiceImpl extends BaseService implements InviteStatisticsService {

    @Autowired
    private MongoTemplate mongoTemplate;


    /**
     * 邀请管理
     * @param selectBase
     * @return
     */
    @Override
    public Pagination<InviteStatistics> getInviteStatistics(SelectBase selectBase){

        Query query = new Query();

        setQueryDate(selectBase.getCreateTimeStart(),selectBase.getCreateTimeEnd(),query);
        query.with(new Sort(Sort.Direction.DESC,"updateTime"));

        if(!StringUtils.isEmpty(selectBase.getName())){
            Pattern pattern=Pattern.compile("^.*"+selectBase.getName()+".*$", Pattern.CASE_INSENSITIVE);
            query.addCriteria(Criteria.where("account").regex(pattern));
        }

        //获取总条数
        Long totalCount = mongoTemplate.count(query,InviteStatistics_C.class);
        Integer skip = (selectBase.getPageNo() - 1) * selectBase.getPageSize();
        // skip相当于从那条记录开始
        query.skip(skip);
        // 从skip开始,取多少条记录
        query.limit(selectBase.getPageSize());

        List<InviteStatistics_C> data = mongoTemplate.find(query, InviteStatistics_C.class);

        List<InviteStatistics> rs=data.stream().map(d->{
            InviteStatistics is = new InviteStatistics();
            BeanUtils.copyProperties(d,is);
            is.setUserId(d.getUserId().toString());
            return is;
        }).collect(Collectors.toList());

        Pagination<InviteStatistics> page = new Pagination<>(selectBase.getPageNo(),selectBase.getPageSize(),totalCount);
        page.build(rs);
        return page;
    }

    @Override
    public void save(InviteStatistics_C is){
        mongoTemplate.insert(is);
    }
}
