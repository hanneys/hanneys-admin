package ngom.user;

import ngom.base.page.Pagination;
import ngom.domain.user.InviteStatistics_C;
import ngom.dto.base.SelectBase;
import ngom.dto.user.InviteStatistics;

import java.util.List;

/**
 * Created by hanaijun on 2018/9/3
 */
public interface InviteStatisticsService {
    Pagination<InviteStatistics> getInviteStatistics(SelectBase selectBase);


    void save(InviteStatistics_C is);
}
