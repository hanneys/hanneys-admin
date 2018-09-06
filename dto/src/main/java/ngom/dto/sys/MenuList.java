package ngom.dto.sys;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * Created by hanaijun on 2018/6/8
 */
@Data
public class MenuList {

    private Long id;

    private String name;

    // 过滤返回空的注解
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<MenuList> menuList;
}
