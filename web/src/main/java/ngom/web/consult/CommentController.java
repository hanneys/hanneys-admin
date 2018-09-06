package ngom.web.consult;

import ngom.base.page.Pagination;
import ngom.config.NoAuthMethod;
import ngom.consult.CommentService;
import ngom.domain.article.CommentRecord_C;
import ngom.web.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;


/**
 * 评论管理
 */
@RestController
@RequestMapping("comment")
public class CommentController extends BaseController {

    @Autowired
    private CommentService commentService;
    /**
     * @api {POST} /comment/findCommentList 查询评论列表
     * @apiGroup Comment
     * @apiVersion 1.0.1
     * @apiDescription 评论
     * @apiParam {Short} flag 0 : 时间, 1: 点赞, 2: 举报, 3: 待审核, 4: 已拒绝, 5:已通过
     * @apiParam {String} startTime 开始时间
     * @apiParam {String} endTime 结束时间
     * @apiParam {String} content 内容关键字
     * @apiParam {Integer} pageNo 当前页
     * @apiParam {Integer} pageSize 每页显示多少条
     * @apiSuccess {Long} id 评论id
     * @apiSuccess {String} nickName 用户名
     * @apiSuccess {String} content 评论内容
     * @apiSuccess {Date} updateTime 评论时间
     * @apiSuccess {Short} auditStatus 审核状态(0 待审核 1 审核通过 2 审核未通过)"
     * @apiSuccess {String} favorCount 点赞数
     * @apiSuccess {String} reportCount 举报数
     * @apiSuccessExample {json} 返回样例:
        {
            "data": {
                "pageSize": 20,
                "pageNum": 1,
                "pages": 1,
                "total": 0,
                "list": [],
                "firstPage": true,
                "lastPage": true
            },
            "success": true
        }
     */
    @RequestMapping("/findCommentList")
    @NoAuthMethod
    public Map<String, Object> findCommentList(@RequestParam("flag") Short flag,
                                               @RequestParam("startTime") String startTime,
                                               @RequestParam("endTime") String endTime,
                                               @RequestParam("content") String content,
                                               @RequestParam("pageNo") Integer pageNo,
                                               @RequestParam("pageSize") Integer pageSize,
                                               HttpSession httpSession) {

        if(StringUtils.isEmpty(pageNo)){
            pageNo = 1;
        }
        if(StringUtils.isEmpty(pageSize)){
            pageSize = 20;
        }
        Pagination<CommentRecord_C> list = commentService.findCommentList(flag, startTime, endTime, content, pageNo, pageSize);
        return resultSuccess(list);
    }

    /**
     * @api {POST} /comment/findCommentById 评论详情
     * @apiGroup Comment
     * @apiVersion 1.0.1
     * @apiDescription 评论
     * @apiParam {Long} id 评论id (不能为空)
     * @apiSuccess {Long} id 评论id
     * @apiSuccess {String} nickName 用户名
     * @apiSuccess {String} content 评论内容
     * @apiSuccess {Date} updateTime 评论时间
     * @apiSuccess {Short} auditStatus 审核状态(0 待审核 1 审核通过 2 审核未通过)"
     * @apiSuccess {String} favorCount 点赞数
     * @apiSuccess {String} reportCount 举报数
     * @apiSuccess {String} title 文章标题
     * @apiSuccessExample {json} 返回样例:
        {
            "data": {
                "recordC": null,
                "title": null
            },
            "success": true
        }
     */
    @RequestMapping("/findCommentById")
    @NoAuthMethod
    public Map<String, Object> findCommentById(@RequestParam("id") Long id,
                                               HttpSession httpSession) {

        if (StringUtils.isEmpty(id)){
            return resultFailure("001", "id 不能为空");
        }
        Map<String, Object> map = commentService.findCommentById(id);
        return resultSuccess(map);
    }


}
