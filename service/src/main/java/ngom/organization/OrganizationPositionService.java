package ngom.organization;

import ngom.domain.organization.OrganizationPosition_C;

import java.util.List;

/**
 * Created by hanaijun on 2018/6/4
 */
public interface OrganizationPositionService {
    List<OrganizationPosition_C> getByOid(Long oid);
}
