package ngom.domain.news;


import lombok.Data;

import java.util.Date;

/**
 * 新闻
 * Created by hanaijun on 2018/8/26
 */
@Data
public class News_C {

    private Long id;

    // 新闻标题
    private String title;

    // 新闻类别
    private Short category;

    // 封面图片
    private String coverImage;

    // 新闻内容
    private String content;

    // 来源
    private String source;

    // 主人公
    private String master;

    // 主人公贡献值数量
    private Long masterContributions;

    // 产出贡献值
    private Long contributions;

    // 审核状态 0:未审核 1:审核未通过 2:审核已通过
    private Short status;

    // 审核人
    private Long auditor;

    // 冻结状态 0：未冻结 1：已冻结
    private Short freezed;

    private Date createTime;

    private Date updateTime;


}
