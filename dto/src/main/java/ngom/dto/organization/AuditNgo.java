package ngom.dto.organization;

import lombok.Data;

/**
 * Created by hanaijun on 2018/6/8
 */
@Data
public class AuditNgo {

    // id
    private String id;

    // 备注
    private String remark;

    // 合约地址
    private String contractAddress;
}
