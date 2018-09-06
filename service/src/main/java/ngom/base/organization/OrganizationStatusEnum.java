package ngom.base.organization;

/**
 * Created by hanaijun on 2018/6/5
 */
public enum OrganizationStatusEnum {

    WAIT_CONFIRM(0,"待审核"),
    PASSED(10,"审核通过"),
    NOT_THROUGH(20,"审核不通过");

    private Integer status;

    private String name;

    private OrganizationStatusEnum(Integer status,String name){
        this.status=status;
        this.name=name;
    }

    public static String getValueByKey(int index){
        for(OrganizationStatusEnum o:OrganizationStatusEnum.values()){
            if (o.getStatus() == index) {
                return o.getName();
            }
        }
        return null;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
