package ngom.dto.organization;

import lombok.Data;
import ngom.dto.base.BaseEntity;

import java.util.Date;

/**
 * Created by hanaijun on 2018/6/4
 */
@Data
public class Ngo extends BaseEntity {

    private Short status;

    private String createTimeStart;

    private String createTimeEnd;

}
