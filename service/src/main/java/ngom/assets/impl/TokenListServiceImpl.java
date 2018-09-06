package ngom.assets.impl;

import ngom.assets.TokenListService;
import ngom.domain.assets.TokenList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by hanaijun on 2018/6/12
 */
@Service
public class TokenListServiceImpl implements TokenListService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void insert(TokenList tokenList){
        mongoTemplate.insert(tokenList);
    }
}
