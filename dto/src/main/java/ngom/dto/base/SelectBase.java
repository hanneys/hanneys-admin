package ngom.dto.base;

import lombok.Data;
import ngom.dto.base.BaseEntity;

import java.util.Date;

/**
 * Created by hanaijun on 2018/6/5
 */
@Data
public class SelectBase extends BaseEntity {

    private String name;

    private Short status;

    private String createTimeStart;

    private String createTimeEnd;
}
