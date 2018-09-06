package ngom.user.impl;

import ngom.domain.article.Message_C;
import ngom.user.UserMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by hanaijun on 2018/9/2
 */
@Service
public class UserMessageServiceImpl implements UserMessageService {

    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public void insert(Message_C messageC){
        mongoTemplate.insert(messageC);
    }
}
