package ngom.dto.organization;

import lombok.Data;

/**
 * Created by hanaijun on 2018/6/4
 */
@Data
public class Position {

    private String id;

    //职位名称
    private String name;

    //职位年费（代币MT）
    private Double fee;

}
