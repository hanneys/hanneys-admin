package ngom.web.consult;

import ngom.base.page.PageInfo;
import ngom.config.NoAuthMethod;
import ngom.consult.ConsultService;
import ngom.domain.article.ArticleVo;
import ngom.domain.article.Article_T;
import ngom.domain.sys.ConsultLabel_C;
import ngom.domain.sys.SysUser_T;
import ngom.sys.SysUserService;
import ngom.utils.IdWorker;
import ngom.web.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 咨询内容管理
 */
@RestController
@RequestMapping("consult")
public class ConsultController extends BaseController {

    @Autowired
    private ConsultService consultService;
    @Autowired
    private SysUserService sysUserService;
    /**
     * @api {POST} /consult/findConsultLabelList 咨询标签
     * @apiGroup Article
     * @apiVersion 1.0.1
     * @apiDescription 咨询
     * @apiSuccess {String} labelName 标签名称
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "data": [
     *         {
     *             "id": 182835280771235846,
     *             "labelName": "银色小天狼"
     *         },
     *         {
     *             "id": 182835355409924096,
     *             "labelName": "正能量"
     *         }
     *     ],
     *     "success": true
     * }
     */
    @RequestMapping("/findConsultLabelList")
    @NoAuthMethod
    public Map<String, Object> findConsultLabelList(HttpSession httpSession) {

        List<ConsultLabel_C> list = consultService.findConsultLabelList();
        return resultSuccess(list);
    }

    /**
     * @api {POST} /consult/addConsult 添加咨询
     * @apiGroup Article
     * @apiVersion 1.0.1
     * @apiDescription 咨询
     * @apiParam {String} title 标题名称
     * @apiParam {JSON} contents 文章内容 格式 JSON[{"text":{"type":"TEXT","content":"这是一片师姐文章"},"image":[{"type":"IMAGE","url":"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2893872400,4221649347","height":"1020px","width":"360px"},{"type":"IMAGE","url":"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2893872400,4221649347","height":"1020px","width":"360px"}]},{"text":{"type":"TEXT","content":"这是一片遮天小说"},"image":[{"type":"IMAGE","url":"http://","height":"1021px","width":"361px"},{"type":"IMAGE","url":"http://www.baidu.com/","height":"1021px","width":"361px"}]}]
     * @apiParam {String} coverUrls 封面图(多个用逗号隔开,) 格式 "url1, url2, url3"
     * @apiParam {String} source 来源
     * @apiParam {String} masters 公益贡献人
     * @apiParam {List} labelNames 标签 格式 ["标签名1","标签名2","标签名3"]
     * @apiSuccessExample {json} 返回样例:
     *                {
     *     "data": {},
     *     "success": true
     * }
     */
    @RequestMapping("/addConsult")
    @NoAuthMethod
    public Map<String, Object> addConsult(@RequestParam("title") String title,
                                          @RequestParam("contents") String contents,
                                          @RequestParam("coverUrls") String coverUrls,
                                          @RequestParam("source") String source,
                                          @RequestParam("masters") String masters,
                                          @RequestParam("labelNames") String labelNames,
                                          HttpSession httpSession) {
        String userId= String.valueOf(httpSession.getAttribute("userId"));
        String name = null;
        if (!"null".equals(userId) && "" != userId && userId != null){
            SysUser_T sysUserT = sysUserService.getById(Long.valueOf(userId));
            if (sysUserT != null){
                name = sysUserT.getName();
            }
        }

        consultService.addConsult(title, contents, coverUrls, source, masters, labelNames, userId, name);
        return resultSuccess();
    }

    /**
     * @api {POST} /consult/findConsultList 全部咨询列表
     * @apiGroup Article
     * @apiVersion 1.0.1
     * @apiDescription 咨询
     * @apiParam {String} title 标题名称
     * @apiParam {Short} releaseStatus 发布状态 0:未发布, 1:已发布
     * @apiParam {Short} delFlag 删除状态 0:未删除, 1:已删除
     * @apiParam {String} startTime 开始时间 格式 yyyy-MM-dd HH:mm:ss
     * @apiParam {String} endTime 结束时间 格式 yyyy-MM-dd HH:mm:ss
     * @apiParam {Integer} pageNo 当前页
     * @apiParam {Integer} pageSize 每页显示多少条
     * @apiSuccess {String} title 标题名称
     * @apiSuccess {String} userName 发布者
     * @apiSuccess {String} releaseTime 发布日期
     * @apiSuccess {Stort} releaseStatus 0:未发布, 1:已发布
     * @apiSuccess {Integer} voteCount 参与人数
     * @apiSuccess {Integer} settledTotal 产出PLT
     * @apiSuccess {Short} delFlag 0:未删除, 1:已删除
     * @apiSuccessExample {json} 返回样例:
        {
            "data":{
            "pageNum":1,
            "pageSize":1,
            "total":1,
            "pages":1,
            "list":[
                {
                    "id":182766648410193920,
                    "userId":null,
                    "userName":null,
                    "title":"这是一片师姐文章",
                    "cover1":"url1",
                    "cover2":"url2",
                    "cover3":"url3",
                    "source":"api",
                    "masterName":"1,2,3,4,",
                    "voteCount":0,
                    "releaseTime":"2018-09-04 17:33:20",
                    "releaseStatus":1,
                    "settledTotal":null,
                    "unsettleAmount":null,
                }
            ],
            "firstPage":true,
            "lastPage":true
            },
            "success":true
        }
     */
    @RequestMapping("/findConsultList")
    @NoAuthMethod
    public Map<String, Object> findConsultList(@RequestParam("title") String title,
                                               @RequestParam("releaseStatus") Short releaseStatus,
                                               @RequestParam("delFlag") Short delFlag,
                                               @RequestParam("startTime") String startTime,
                                               @RequestParam("endTime") String endTime,
                                               @RequestParam("pageNo") Integer pageNo,
                                               @RequestParam("pageSize") Integer pageSize,
                                               HttpSession httpSession) {

        PageInfo<Article_T> articleCS = consultService.findConsultList(title, releaseStatus, delFlag, startTime, endTime, pageNo, pageSize);
        return resultSuccess(articleCS);
    }
    /**
     * @api {POST} /consult/findConsultById 编辑查询单条咨询
     * @apiGroup Article
     * @apiVersion 1.0.1
     * @apiDescription 咨询
     * @apiParam {Long} id 文章id(不能为空)
     * @apiSuccess {String} title 标题名称
     * @apiSuccess {String} cover1 封面图1
     * @apiSuccess {String} cover2 封面图2
     * @apiSuccess {String} cover3 封面图3
     * @apiSuccess {String} source 来源
     * @apiSuccess {String} masterName 公益贡献人
     * @apiSuccess {String} tags 标签
     * @apiSuccessExample {json} 返回样例:
        {
            "data":{
            "pageNum":1,
            "pageSize":1,
            "total":1,
            "pages":1,
            "list":[
            {
                "id":182766648410193920,
                "userId":null,
                "userName":null,
                "title":"这是一片师姐文章",
                "cover1":"url1",
                "cover2":"url2",
                "cover3":"url3",
                "source":"api",
                "masterName":"1,2,3,4,",
                "voteCount":0,
                "releaseTime":"2018-09-04 17:33:20",
                "releaseStatus":1,
                "settledTotal":null,
                "unsettleAmount":null,
                "articleContentC":{
                "id":182766648410193920,
                "title":"这是一片师姐文章",
                "tags":[
                    "1",
                    "2",
                    "3",
                    "4"
                ],
                "contentList":[
                        {
                            "text":{
                                "type":"TEXT",
                                "content":"这是一片师姐文章"
                            },
                            "image":[
                            {
                                "type":"IMAGE",
                                "url":"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2893872400,4221649347",
                                "height":"1020px",
                                "width":"360px"
                            },
                            {
                                "type":"IMAGE",
                                "url":"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2893872400,4221649347",
                                "height":"1020px",
                                "width":"360px"
                            }
                            ]
                        },
                        {
                        "text":{
                            "type":"TEXT",
                            "content":"这是一片遮天小说"
                        },
                        "image":[
                        {
                            "type":"IMAGE",
                            "url":"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2893872400,4221649347",
                            "height":"1021px",
                            "width":"361px"
                        },
                        {
                            "type":"IMAGE",
                            "url":"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2893872400,4221649347",
                            "height":"1021px",
                            "width":"361px"
                        }
                    ]
                    }
                ],
                "createTme":"2018-09-04 17:33:20",
                "updateTime":"2018-09-04 17:33:20"
                }
                }
                ],
                "firstPage":true,
                "lastPage":true
                },
                "success":true
        }
     */
    @RequestMapping("/findConsultById")
    @NoAuthMethod
    public Map<String, Object> findConsultById(@RequestParam("id") Long id,
                                               HttpSession httpSession) {

        ArticleVo articleVo = consultService.selectConsultById(id);
        return resultSuccess(articleVo);
    }

    /**
     * @api {POST} /consult/updateConsultById 编辑咨询
     * @apiGroup Article
     * @apiVersion 1.0.1
     * @apiDescription 咨询
     * @apiParam {Long} id 文章id(不能为空)
     * @apiParam {String} title 标题名称
     * @apiParam {String} cover1 封面图1
     * @apiParam {String} cover2 封面图2
     * @apiParam {String} cover3 封面图3
     * @apiParam {String} source 来源
     * @apiParam {String} masterName 公益贡献人
     * @apiParam {JSON} contents 文章内容[{"text":{"type":"TEXT","content":"这是一片师姐文章"},"image":[{"type":"IMAGE","url":"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2893872400,4221649347","height":"1020px","width":"360px"},{"type":"IMAGE","url":"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2893872400,4221649347","height":"1020px","width":"360px"}]},{"text":{"type":"TEXT","content":"这是一片遮天小说"},"image":[{"type":"IMAGE","url":"http://","height":"1021px","width":"361px"},{"type":"IMAGE","url":"http://www.baidu.com/","height":"1021px","width":"361px"}]}]
     * @apiParam {List} labelNames 标签 格式 ["标签名1","标签名2","标签名3"]
     * @apiSuccessExample {json} 返回样例:
     *                {
     *     "data": {},
     *     "success": true
     * }
     */
    @RequestMapping("/updateConsultById")
    @NoAuthMethod
    public Map<String, Object> updateConsultById(ArticleVo articleVo,
                                                 @RequestParam("contents") String contents,
                                                 @RequestParam("labelNames") String labelNames,
                                                 HttpSession httpSession) {
        consultService.updateConsultById(articleVo, contents, labelNames);
        return resultSuccess();
    }

    /**
     * @api {POST} /consult/del_release_Consult 删除or发布咨询
     * @apiGroup Article
     * @apiVersion 1.0.1
     * @apiDescription 咨询
     * @apiParam {Long} id 文章id
     * @apiParam {Short} flag 1:删除, 2: 发布
     * @apiSuccessExample {json} 返回样例:
     *                {
     *     "data": {},
     *     "success": true
     * }
     */
    @RequestMapping("/del_release_Consult")
    @NoAuthMethod
    public Map<String, Object> del_release_Consult(@RequestParam("id") Long id,
                                                   @RequestParam("flag") String flag,
                                                   HttpSession httpSession) {
        if (StringUtils.isEmpty(flag)){
            return resultFailure("001", "flag 不能为空");
        }
        consultService.delReleaseConsult(id, flag);
        return resultSuccess();
    }
}
