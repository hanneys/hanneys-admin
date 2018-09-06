package ngom.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by hanaijun on 2018/6/3
 */
@ConfigurationProperties(prefix = "druid")
@Data
public class DruidProperties {

    private String url;
    private String username;
    private String password;
    private String driverClass;

    private int     maxActive;
    private int     minIdle;
    private int     initialSize;
    private boolean testOnBorrow;


}
