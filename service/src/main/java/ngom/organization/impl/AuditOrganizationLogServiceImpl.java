package ngom.organization.impl;

import ngom.domain.organization.AuditOrganizationLog_C;
import ngom.organization.AuditOrganizationLogService;
import ngom.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 审核备注
 * Created by hanaijun on 2018/6/5
 */
@Service
public class AuditOrganizationLogServiceImpl implements AuditOrganizationLogService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public AuditOrganizationLog_C getByOid(){
        return new AuditOrganizationLog_C();
    }

    @Override
    public void insert(Long oid, Long userId, Integer status, String remark){
        AuditOrganizationLog_C auditOrganizationLog = new AuditOrganizationLog_C();
        auditOrganizationLog.setId(IdWorker.getIdWorker().nextId());
        auditOrganizationLog.setOid(oid);
        auditOrganizationLog.setUserId(userId);
        auditOrganizationLog.setRemark(remark);
        auditOrganizationLog.setStatus(status.shortValue());
        auditOrganizationLog.setCreateTime(new Date());
        mongoTemplate.insert(auditOrganizationLog);
    }
}
