package ngom.consult;

import ngom.base.page.Pagination;
import ngom.domain.article.CommentRecord_C;

import java.util.Map;

public interface CommentService {
    Pagination<CommentRecord_C> findCommentList(Short flag, String startTime, String endTime, String content, Integer pageNo, Integer pageSize);

    Map<String,Object> findCommentById(Long id);
}
