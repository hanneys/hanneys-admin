package ngom.domain.article;


import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * mongo 文章内容表
 */
public class ArticleContent_C implements Serializable {

    private static final long serialVersionUID = -8394911634800879713L;

    /**
     * 与文章表ngom.domain.article.Article_T id 对应 一对一
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 标签
     */
    private Set<String> tags;
    /**
     * 文章内容集合
     */
    private List<Content> contentList;

    /**
     * 创建时间
     */
    private Date createTme;

    /**
     * 更新时间
     */
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Content> getContentList() {
        return contentList;
    }

    public void setContentList(List<Content> contentList) {
        this.contentList = contentList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public Date getCreateTme() {
        return createTme;
    }

    public void setCreateTme(Date createTme) {
        this.createTme = createTme;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
