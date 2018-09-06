package ngom.base;

import ngom.utils.DateUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by hanaijun on 2018/9/3
 */
@Service
public class BaseService {

    public void setQueryDate(String startString, String endString, Query query){
        if (!StringUtils.isEmpty(startString) && !StringUtils.isEmpty(endString)) {
            try {
                Date start=DateUtils.string2Date(startString);
                Date end=DateUtils.string2Date(endString);
                query.addCriteria(Criteria.where("createTime").gte(start).lte(end));
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
    }
}
