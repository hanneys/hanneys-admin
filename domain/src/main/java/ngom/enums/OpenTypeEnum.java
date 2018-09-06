package ngom.enums;

/**
 * Created by hanaijun on 2018/7/18
 */
public enum OpenTypeEnum {

    QQ((short) 0,"QQ"),
    WEIXIN((short)10,"微信"),
    WEIBO((short)20,"微博"),
    TWITTER((short)30,"twitter"),
    FACEBOOK((short)40,"facebook"),
    GOOGLE((short)50,"google");

    private Short status;

    private String name;

    private OpenTypeEnum(Short status, String name){
        this.status=status;
        this.name=name;
    }

    public static String getValueByKey(int index){
        for(OpenTypeEnum o:OpenTypeEnum.values()){
            if (o.getStatus() == index) {
                return o.getName();
            }
        }
        return null;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}