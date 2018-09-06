package ngom.domain.sys;

import lombok.Data;

import java.util.Date;

/**
 * 敏感词
 */
@Data
public class SensitiveWords_C {

    private Long id;

    private String blackWords;

    private Date createTime;

    private Date updateTime;



}
