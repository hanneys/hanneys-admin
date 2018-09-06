package ngom.dto.sys;

import lombok.Data;
import ngom.dto.base.BaseEntity;

/**
 * Created by ZJ on 2018/8/29
 */
@Data
public class Sensitive extends BaseEntity {

    //id 主键
    private Long id;

    //黑词
    private String blackWords;
}
