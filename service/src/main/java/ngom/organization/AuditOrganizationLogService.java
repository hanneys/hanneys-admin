package ngom.organization;

/**
 * Created by hanaijun on 2018/6/5
 */
public interface AuditOrganizationLogService {

    void insert(Long oid, Long userId, Integer status, String remark);
}
