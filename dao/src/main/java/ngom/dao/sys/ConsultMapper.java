package ngom.dao.sys;


import ngom.domain.article.ArticleVo;
import ngom.domain.article.Article_T;
import ngom.domain.sys.SysMenu_T;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@Mapper
public interface ConsultMapper {

    int insert(Article_T articleT);
    int insertSelective(Article_T articleT);

    List<Article_T> selectArticleList(@Param("title") String title, @Param("releaseStatus")Short releaseStatus, @Param("start")Date start, @Param("end")Date end);

    Article_T selectConsultById(@Param("id")Long id);

    void updateByPrimaryKeySelective(ArticleVo articleVo);

    void updateConsultById(ArticleVo articleVo);

    void delReleaseConsult(@Param("id")Long id, @Param("releaseStatus")Short releaseStatus, @Param("delFlag")Short delFlag, @Param("time")Date time, @Param("date")Date date);
}