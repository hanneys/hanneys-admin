package ngom.assets.impl;

import ngom.assets.CurrencyService;
import ngom.assets.TokenListService;
import ngom.domain.assets.Currency_C;
import ngom.domain.assets.TokenList;
import ngom.domain.organization.Organization_C;
import ngom.domain.user.User_C;
import ngom.user.UserService;
import ngom.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by hanaijun on 2018/6/12
 */
@Service
public class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenListService tokenListService;

    @Override
    public void auditOrganization(Organization_C organization){
        User_C user_c=userService.getById(organization.getCreator());
        Currency_C currencyC = new Currency_C();
        currencyC.setAddress(organization.getPersonalAddress());
        currencyC.setCoinLogo("");
        currencyC.setCoinName(organization.getCoinName());
        currencyC.setCoinNumber(0.0f);
        currencyC.setCoinTotalPrice(0.0f);
        currencyC.setContractAddress(null);
        Date date = new Date();
        currencyC.setCreateTime(date);
        currencyC.setFlag("AURORA");
        currencyC.setId(IdWorker.getIdWorker().nextId());
        currencyC.setPublicAddress("");
        currencyC.setUnitPrice(0.0f);
        currencyC.setUpdateTime(date);
        currencyC.setUserAccount(user_c.getEmail());
        currencyC.setUserId(user_c.getId());
        currencyC.setUserName(user_c.getEmail());
        currencyC.setMeetId(organization.getId());
        long tokenId = IdWorker.getIdWorker().nextId();
        currencyC.setTokenId(tokenId);
        mongoTemplate.insert(currencyC);
        insertTokenList(tokenId,organization.getCoinName(),date,organization);
    }


    private void insertTokenList(long tokenId, String coinName, Date date,Organization_C organization){
        TokenList tokenList = new TokenList(tokenId, coinName, coinName, 5, 0.00f, organization.getContractAddress(), "", "AURORA",date,date);
        tokenList.setExchangeMT(BigDecimal.valueOf(organization.getExchangeMT()));
        tokenList.setExchangeNGOT(BigDecimal.valueOf(organization.getExchangeNGOT()));
        tokenListService.insert(tokenList);
    }
}
