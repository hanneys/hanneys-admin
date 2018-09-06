package ngom.user.impl;

import ngom.base.page.Pagination;
import ngom.domain.user.User_C;
import ngom.dto.base.SelectBase;
import ngom.dto.user.InvitedUsers;
import ngom.enums.OpenTypeEnum;
import ngom.user.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by hanaijun on 2018/6/6
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public User_C getById(Long id){
        return mongoTemplate.findById(id,User_C.class);
    }


    /**
     * 邀请详情列表
     * @param inviteFirst
     * @param selectBase
     * @return
     */
    @Override
    public Pagination<InvitedUsers> getUsersByInviteFirst(Long inviteFirst, SelectBase selectBase){
        Query query = new Query(Criteria.where("inviterFirst").is(inviteFirst));
        query.with(new Sort(Sort.Direction.DESC,"createTime"));
        //获取总条数
        Long totalCount = mongoTemplate.count(query,User_C.class);
        Integer skip = (selectBase.getPageNo() - 1) * selectBase.getPageSize();
        // skip相当于从那条记录开始
        query.skip(skip);
        // 从skip开始,取多少条记录
        query.limit(selectBase.getPageSize());
        List<User_C> data = mongoTemplate.find(query, User_C.class);

        List<InvitedUsers> rs=data.stream().map(d->{
            InvitedUsers iu = new InvitedUsers();
            BeanUtils.copyProperties(d,iu);
            iu.setThirdType(OpenTypeEnum.getValueByKey(d.getSource()));
            return iu;
        }).collect(Collectors.toList());

        Pagination<InvitedUsers> page = new Pagination<>(selectBase.getPageNo(),selectBase.getPageSize(),totalCount);
        page.build(rs);
        return page;
    }



    @Override
    public void save(List<User_C> list){
        mongoTemplate.insert(list,User_C.class);
    }


}
