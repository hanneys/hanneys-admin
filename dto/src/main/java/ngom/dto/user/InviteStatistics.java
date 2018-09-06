package ngom.dto.user;

import lombok.Data;

/**
 * Created by hanaijun on 2018/9/3
 */
@Data
public class InviteStatistics {

    private String userId;

    private String account;

    private Long count;
}
