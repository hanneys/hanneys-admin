package ngom.organization.impl;

import ngom.domain.organization.OrganizationPosition_C;
import ngom.organization.OrganizationPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hanaijun on 2018/6/4
 */
@Service
public class OrganizationPositionServiceImpl implements OrganizationPositionService {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 根据组织id获取未删除职位
     * @param oid
     * @return
     */
    @Override
    public List<OrganizationPosition_C> getByOid(Long oid){
        Query query=new Query(Criteria.where("oid").is(oid).and("deleteFlag").is(0));
        return mongoTemplate.find(query,OrganizationPosition_C.class);
    }
}
