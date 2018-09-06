package ngom.domain.organization;

import lombok.Data;

import java.util.Date;

@Data
public class AuditOrganizationLog_C {

    private Long id;
    // 组织id
    private Long oid;
    // 审核人ID
    private Long userId;
    // 审核操作状态 10:未通过 20:已通过
    private Short status;
    // 审核备注
    private String remark;
    // //创建时间
    private Date createTime;


}
