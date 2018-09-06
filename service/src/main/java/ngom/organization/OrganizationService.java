package ngom.organization;

import ngom.dto.organization.Ngo;
import ngom.dto.organization.NgoInfo;
import ngom.base.page.Pagination;

/**
 * Created by hanaijun on 2018/6/4
 */
public interface OrganizationService {
    Pagination<NgoInfo> getNgoList(Ngo ngo);

    NgoInfo ngoDetail(Long id);

    void passed(Long userId, Long id, String remark);

    void notThrough(Long userId, Long id, String remark);
}
