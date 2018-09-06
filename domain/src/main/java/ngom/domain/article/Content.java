package ngom.domain.article;

import lombok.Data;

import java.util.List;

/**
 * 页面内容集合
 */
@Data
public class Content {

    private Long id;
    /**
     * 文章内容
     */
    private Text text;

    /**
     * 文章图片
     */
    private List<Image> image;

}
