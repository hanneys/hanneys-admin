package ngom.base.feedback;


/**
 * 反馈类型
 * Created by hanaijun on 2018/6/6
 */

public enum  FeedBackTypeEnum {
    USER(1,"用户"),
    CUSTOMER(2,"客服");

    private Integer type;

    private String name;

    private FeedBackTypeEnum(Integer type,String name){
        this.type=type;
        this.name=name;
    }
    public static String getValueByKey(int index){
        for(FeedBackTypeEnum o:FeedBackTypeEnum.values()){
            if (o.getType() == index) {
                return o.getName();
            }
        }
        return null;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
