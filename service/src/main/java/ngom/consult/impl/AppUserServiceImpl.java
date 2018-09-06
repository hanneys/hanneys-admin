package ngom.consult.impl;

import lombok.extern.slf4j.Slf4j;
import ngom.consult.AppUserService;
import ngom.domain.user.User_C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * 用户管理
 */
@Slf4j
@Service
public class AppUserServiceImpl implements AppUserService {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 查询用户
     * @param name 用户名
     * @param email 用户账户
     * @return
     */
    @Override
    public User_C findAppUser(String name, String email) {
        log.info("查询用户 name :::>>>{},email:::>>>{}", name, email);
        Query query = new Query();
        if (!StringUtils.isEmpty(name)){
            query.addCriteria(Criteria.where("name").regex(name));
        }
        if (!StringUtils.isEmpty(email)){
            query.addCriteria(Criteria.where("email").regex(email));
        }
        User_C userC = mongoTemplate.findOne(query, User_C.class);
        return userC;
    }

    /**
     * 账号冻结
     * @param id 用户id
     * @param flag 1:禁言, 2:解禁;     3:冻结  4:解冻
     *
     *   freezed 冻结状态 0：未冻结 1：已冻结
     *   banned 禁言状态 0:未禁言 1:已禁言
     */
    @Override
    public void updateAppUser(Long id, Short flag) {
        log.info("更改用户状态 id:::>>>{},flag:::>>>{}", id, flag);
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        Update update = new Update();
        if (flag == 1){
            update.set("banned", (short)1);
        }else if (flag == 2){
            update.set("banned", (short)0);
        }else if (flag == 3){
            update.set("freezed", (short)1);
        }else if (flag== 4){
            update.set("freezed", (short)0);
        }else {
            Date date = new Date();
            update.set("updateTime", date);
        }
        log.info("update::::>>>{}", update);
        mongoTemplate.updateFirst(query, update, User_C.class);
    }
}
