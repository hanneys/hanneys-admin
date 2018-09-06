package ngom.base.feedback;



/**
 *  反馈状态
 * Created by hanaijun on 2018/6/6
 */
public enum FeedBackStatusEnum {

    NOT_RETURN(0,"未回复"),
    RETURNED(1,"已回复");

    private Integer status;

    private String name;

    FeedBackStatusEnum(Integer status,String name){
        this.status=status;
        this.name=name;
    }

    public static String getValueByKey(int index){
        for(FeedBackStatusEnum o:FeedBackStatusEnum.values()){
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
