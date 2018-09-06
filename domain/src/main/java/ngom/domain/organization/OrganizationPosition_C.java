package ngom.domain.organization;

import lombok.Data;

import java.util.Date;

/**
 * Created by hanaijun on 2018/6/4
 */
@Data
public class OrganizationPosition_C {

    private Long id;//职位id
    private Long oid;//组织id
    private String name;//职位名称
    private Double fee;//职位年费（代币MT）
    private Short deleteFlag;//删除标示 0 未删除 1已删除
    private Date createTime;
    private Date updateTime;
}
