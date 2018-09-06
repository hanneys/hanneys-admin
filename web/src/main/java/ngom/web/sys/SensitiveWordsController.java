package ngom.web.sys;


import ngom.base.page.Pagination;
import ngom.config.NoAuthMethod;
import ngom.domain.sys.SensitiveWords_C;
import ngom.dto.sys.Sensitive;
import ngom.exception.ErrorInfo;
import ngom.exception.NgomAdminException;
import ngom.sys.SensitiveWordsService;
import ngom.web.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * created ZJ 2018年08月29日17:26:08
 */
@RestController
@RequestMapping("sensitiveWords")
public class SensitiveWordsController extends BaseController {

    @Autowired
    private SensitiveWordsService sensitiveWordsService;

    /**
     * @api {POST} /sensitiveWords/findSensitiveWordsList 查询敏感词列表
     * @apiGroup SensitiveWords
     * @apiVersion 1.0.1
     * @apiDescription 敏感词
     * @apiParam {String} blackWords 黑词
     * @apiParam {Integer} pageNo 当前页
     * @apiParam {Integer} pageSize 每页显示多少条
     * @apiSuccess {Long} id : id
     * @apiSuccess {String} blackWords 敏感词
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
                        "blackWords":"敏感词1"
                    },
                    {
                        "id":182766648410193920,
                        "blackWords":"敏感词2"
                    }
                ],
            "firstPage":true,
            "lastPage":true
            },
        "success":true
        }
     */
    @RequestMapping("/findSensitiveWordsList")
    @NoAuthMethod
    public Map<String, Object> findSensitiveWordsList(Sensitive sensitive,
                                                      HttpSession httpSession) {

        Pagination<SensitiveWords_C> list = sensitiveWordsService.findSensitiveWordsList(sensitive);
        return resultSuccess(list);
    }

    /**
     * @api {POST} /sensitiveWords/deleteSensitiveWords 删除敏感词
     * @apiGroup SensitiveWords
     * @apiVersion 1.0.1
     * @apiDescription 敏感词
     * @apiParam {Long} id 主键id(不能为空)
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "data": {},
     *     "success": true
     * }
     */
    @RequestMapping("/deleteSensitiveWords")
    @NoAuthMethod
    public Map<String, Object> deleteSensitiveWords(Sensitive sensitive,
                                                      HttpSession httpSession) {

        if (sensitive.getId() == null){
            throw new NgomAdminException(ErrorInfo.SENSITIVE_NOT_EMPTY_ERR_CODE, ErrorInfo.SENSITIVE_NOT_EMPTY_ERR_INFO);
        }
        sensitiveWordsService.deleteSensitiveWords(sensitive);
        return resultSuccess();
    }


    /**
     * @api {POST} /sensitiveWords/addSensitiveWords 添加敏感词
     * @apiGroup SensitiveWords
     * @apiVersion 1.0.1
     * @apiDescription 敏感词
     * @apiParam {String} sensitive 敏感词 格式 "敏感词1,敏感词2,敏感词3"
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "data": {},
     *     "success": true
     * }
     */
    @RequestMapping("/addSensitiveWords")
    @NoAuthMethod
    public Map<String, Object> addSensitiveWords(@RequestParam("sensitive") String sensitive,
                                                 HttpSession httpSession) {

        if (sensitive == null || sensitive == ""){
            throw new NgomAdminException(ErrorInfo.SENSITIVE_NOT_EMPTY_ERR_CODE, ErrorInfo.SENSITIVE_NOT_EMPTY_ERR_INFO);
        }
        sensitiveWordsService.addSensitiveWords(sensitive);
        return resultSuccess();
    }

}
