package ngom.consult;

import ngom.domain.user.User_C;

public interface AppUserService {
    User_C findAppUser(String name, String email);

    void updateAppUser(Long id, Short flag);
}
