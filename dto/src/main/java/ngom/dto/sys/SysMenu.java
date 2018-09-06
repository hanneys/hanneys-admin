package ngom.dto.sys;

import lombok.Data;

/**
 * Created by hanaijun on 2018/6/6
 */
@Data
public class SysMenu {

    private Long id;

    private String name;

    private Long parentId;
}
