package ngom.web.feedback;

import ngom.dto.base.SelectBase;
import ngom.feedback.FeedbackService;
import ngom.feedback.ReplyFeedbackLogService;
import ngom.web.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by hanaijun on 2018/6/6
 */
@RestController
@RequestMapping("feedback")
public class FeedBackController extends BaseController {

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private ReplyFeedbackLogService replyFeedbackLogService;


    /**
     * @api {POST} /selectBase/getFeedbackList 反馈列表
     * @apiGroup SelectBase-反馈
     * @apiVersion 1.0.1
     * @apiDescription 反馈列表
     * @apiParam {Number} [status] 状态 0 未回复  1 已回复
     * @apiParam {Date} [createTimeStart] 开始时间
     * @apiParam {Date} [createTimeEnd]   结束时间
     * @apiParam {Number} pageNo 页码
     * @apiParam {Number} pageSize 每页数量
     * @apiParamExample {json} 请求示例：
     *       {
     * 	"pageNo":1,
     * 	"pageSize":20
     * }
     * @apiSuccess (200) {String} success true:正确   false:错误
     * @apiSuccess {Number} pageSize 每页数量
     * @apiSuccess {String} statusName 状态
     * @apiSuccess {String} fb 反馈内容
     * @apiSuccess {String} reply 回复内容
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "data": {
     *         "pageSize": 20,
     *         "pageNum": 1,
     *         "pages": 1,
     *         "total": 3,
     *         "list": [
     *             {
     *                 "id": 174530449777934336,
     *                 "createTime": "2018-06-05 19:42:29",
     *                 "mapContent": {
     *                     "fb": {
     *                         "date": "2018-06-05 19:42:29",
     *                         "name": "呵呵呵呵呵呵",
     *                         "content": "提交"
     *                     },
     *                     "reply": {
     *                         "date": "2018-06-05 19:42:29",
     *      *                         "name": "呵呵呵呵呵呵",
     *      *                         "content": "提交"
     *                     }
     *                 },
     *                 "status": 0,
     *                 "statusName": "未回复"
     *             },
     *             {
     *                 "id": 174530474391158784,
     *                 "createTime": "2018-06-05 19:42:52",
     *                 "mapContent": {
     *                     "fb": {
     *                         "date": "2018-06-05 19:42:52",
     *                         "name": "呵呵呵呵呵呵",
     *                         "content": "提交"
     *                     }
     *                 },
     *                 "status": 0,
     *                 "statusName": "未回复"
     *             },
     *             {
     *                 "id": 174530543428354048,
     *                 "createTime": "2018-06-05 19:43:58",
     *                 "mapContent": {
     *                     "fb": {
     *                         "date": "2018-06-05 19:43:58",
     *                         "name": "呵呵呵呵呵呵",
     *                         "content": "123456"
     *                     }
     *                 },
     *                 "status": 0,
     *                 "statusName": "未回复"
     *             }
     *         ],
     *         "lastPage": true,
     *         "firstPage": true
     *     },
     *     "success": true
     * }
     */
    @RequestMapping("getFeedbackList")
    public Map<String,Object> getFeedbackList(SelectBase selectBase){
        return resultSuccess(feedbackService.getFeedbackList(selectBase));
    }

    /**
     * @api {POST} /feedback/reply 回复
     * @apiGroup SelectBase-反馈
     * @apiVersion 1.0.1
     * @apiDescription 反馈列表
     * @apiParam {String} id 反馈id
     * @apiParam {String} content 反馈内容
     * @apiParamExample {json} 请求示例：
     *       {
     * 	"id":1,
     * 	"content":20
     * }
     * @apiSuccess (200) {String} success true:正确   false:错误
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "success": true
     * }
     */
    @RequestMapping("reply")
    public Map<String,Object> reply(@RequestParam String id,
                                    @RequestParam String content,
                                    HttpSession httpSession){
        Long userId=Long.valueOf(String.valueOf(httpSession.getAttribute("userId")));
        replyFeedbackLogService.reply(Long.valueOf(id),content,userId);
        return resultSuccess();
    }
}
