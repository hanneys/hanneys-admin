package ngom.user;

import ngom.base.page.Pagination;
import ngom.domain.user.User_C;
import ngom.dto.base.SelectBase;
import ngom.dto.user.InvitedUsers;

import java.util.List;

/**
 * Created by hanaijun on 2018/6/6
 */
public interface UserService {
    User_C getById(Long id);

    Pagination<InvitedUsers> getUsersByInviteFirst(Long inviteFirst, SelectBase selectBase);

    void save(List<User_C> list);
}
