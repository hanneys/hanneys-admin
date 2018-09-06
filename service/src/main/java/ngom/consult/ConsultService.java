package ngom.consult;

import ngom.base.page.PageInfo;
import ngom.domain.article.ArticleVo;
import ngom.domain.article.Article_T;
import ngom.domain.sys.ConsultLabel_C;

import java.util.List;

public interface ConsultService {
    List<ConsultLabel_C> findConsultLabelList();

    void addConsult(String title, String contents, String coverUrls, String source, String masters, String labelNames, String userId, String userName);


    PageInfo<Article_T> findConsultList(String title, Short releaseStatus, Short delFlag, String startTime, String endTime, Integer pageNo, Integer pageSize);

    ArticleVo selectConsultById(Long id);

    void updateConsultById(ArticleVo articleVo, String contents, String labelNames);

    void delReleaseConsult(Long id, String flag);

    void InsertConsultLabel(List<ConsultLabel_C> list);
}
