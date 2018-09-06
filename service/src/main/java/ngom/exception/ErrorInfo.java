package ngom.exception;

/**
 * sys 100-199
 * organiztion 200-299
 * Created by hanaijun on 2018/6/3
 */
public class ErrorInfo {

    public static final String USER_LOGIN_ERR_CODE="err.sys.100";//错误码
    public static final String USER_LOGIN_ERR_INFO = "用户名或密码错误"; //中文描述

    public static final String USER_ISNO_ERR_CODE="err.sys.104";
    public static final String USER_ISNO_ERR_INFO="用户不存在";

    public static final String USER_LOGIN_ERROR_CODE="err.sys.105";
    public static final String USER_LOGIN_ERROR_INFO="登录信息错误";

    public static final String USER_NOT_LOGIN_ERR_CODE="err.sys.107";//错误码
    public static final String USER_NOT_LOGIN_ERR_INFO = "未登录"; //中文描述

    public static final String USER_EMAIL_EMPTY_ERR_CODE="err.sys.108";
    public static final String USER_EMAIL_EMPTY_ERR_INFO="用户邮箱不能为空";

    public static final String USER_PASSWORD_EMPTY_ERR_CODE="err.sys.109";
    public static final String USER_PASSWORD_EMPTY_ERR_INFO="用户密码不能为空";

    public static final String USER_PASSWORD_ERROR_ERR_CODE="err.sys.110";
    public static final String USER_PASSWORD_ERROR_ERR_INFO="旧密码错误";


    public static final String SYS_PARAMS_ERROR_CODE = "err.sys.300";
    public static final String SYS_PARAMS_ERROR_INFO = "缺少必要的参数";

    public static final String ORGANIZATION_ID_EMPTY_ERR_CODE="err.organization.200";
    public static final String ORGANIZATION_ID_EMPTY_ERR_INFO="ngo id 不能为空";

    public static final String ORGANIZATION_TOKENADDRESS_EMPTY_ERR_CODE="err.organization.201";
    public static final String ORGANIZATION_TOKENADDRESS_EMPTY_ERR_INFO="合约地址不能为空";

    public static final String ORGANIZATION_REMARK_EMPTY_ERR_CODE="err.organization.202";
    public static final String ORGANIZATION_REMARK_EMPTY_ERR_INOF="备注内容不能为空";


    public static final String SENSITIVE_ID_NOT_EMPTY_ERR_CODE = "err.sensitive.203";
    public static final String SENSITIVE_ID_NOT_EMPTY_ERR_INFO = "删除敏感词id不能为空";

    public static final String SENSITIVE_NOT_EMPTY_ERR_CODE = "err.sensitive.204";
    public static final String SENSITIVE_NOT_EMPTY_ERR_INFO = "敏感词不能为空";

    public static final String UPLOADE_IMG_LOCATION_ERR_CODE="err.upload.img.997";
    public static final String UPLOADE_IMG_LOCATION_ERR_INFO="路径错误";

    public static final String UPLOADE_IMG_EMPTY_ERR_CODE="err.upload.img.998";
    public static final String UPLOADE_IMG_EMPTY_ERR_INFO="没有图片";

    public static final String UPLOADE_IMG_ERR_CODE="err.upload.img.999";
    public static final String UPLOADE_IMG_ERR_INFO="图片格式错误";
}
