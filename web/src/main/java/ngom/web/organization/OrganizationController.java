package ngom.web.organization;

import ngom.dto.organization.AuditNgo;
import ngom.dto.organization.Ngo;
import ngom.organization.OrganizationService;
import ngom.web.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by hanaijun on 2018/6/4
 */
@RestController
@RequestMapping("organization")
public class OrganizationController extends BaseController {

    @Autowired
    private OrganizationService organizationService;


    /**
     * @api {POST} /organization/getNgoList 审核列表
     * @apiGroup NGO组织
     * @apiVersion 1.0.1
     * @apiDescription 审核列表
     * @apiParam {Number} [status] 状态 0 待审核  10审核通过 20 审核不通过
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
     * @apiSuccess {Number} pageNum 页码
     * @apiSuccess {Number} pages 总页数
     * @apiSuccess {Number} total 总数量
     * @apiSuccess {Boolean} firstPage 是否第一页
     * @apiSuccess {Boolean} lastPage 是否最后一页
     * @apiSuccess {String} id id
     * @apiSuccess {String} image 图片
     * @apiSuccess {String} name 组织名称
     * @apiSuccess {String} desc 组织名称
     * @apiSuccess {String} coinName 代币名称
     * @apiSuccess {String} coinNumber 代币数量
     * @apiSuccess {String} exchangeNGOT 兑换比例 ngot 数量
     * @apiSuccess {String} createTime 时间
     * @apiSuccess {String} statusName 状态名称
     * @apiSuccess {Number} status 状态
     * @apiSuccessExample {json} 返回样例:
     *                {
     *     "data": {
     *         "pageSize": 20,
     *         "pageNum": 1,
     *         "pages": 1,
     *         "total": 11,
     *         "list": [
     *             {
     *                 "id": 174419989854471168,
     *                 "image": "1528093537760_173711315785793536.jpg",
     *                 "name": "明早见",
     *                 "desc": "up一一样嘻嘻嘻嘻",
     *                 "coinName": null,
     *                 "coinNumber": null,
     *                 "exchangeNGOT": 2,
     *                 "createTime": "2018-06-04 14:26:46",
     *                 "status": 0,
     *                 "statusName": "待审核"
     *             },
     *            ......
     *         ],
     *         "firstPage": true,
     *         "lastPage": true
     *     },
     *     "success": true
     * }
     */
    @RequestMapping("getNgoList")
    public Map<String,Object> getNgoList( Ngo ngo){
        return resultSuccess(organizationService.getNgoList(ngo));
    }

    /**
     * @api {POST} /organization/ngoDetail ngo详情
     * @apiGroup NGO组织
     * @apiVersion 1.0.1
     * @apiDescription ngo详情
     * @apiParam {Number} id id
     * @apiParamExample {json} 请求示例：
     *       ?id=xxxx
     * @apiSuccess (200) {String} success true:正确   false:错误
     * @apiSuccess {String} id id
     * @apiSuccess {String} image 图片
     * @apiSuccess {String} name 组织名称
     * @apiSuccess {String} desc 组织名称
     * @apiSuccess {String} coinName 代币名称
     * @apiSuccess {String} coinNumber 代币数量
     * @apiSuccess {String} exchangeNGOT 兑换比例 ngot 数量
     * @apiSuccess {String} createTime 时间
     * @apiSuccess {String} statusName 状态名称
     * @apiSuccess {Number} status 状态
     * @apiSuccess {Object} positions {
     *     id: 名称
     *     name: 职位
     *     fee: 会费
     * }
     * @apiSuccessExample {json} 返回样例:
     *      {
     *     "data": {
     *         "id": 174419989854471168,
     *         "image": "1528093537760_173711315785793536.jpg",
     *         "name": "明早见",
     *         "desc": "up一一样嘻嘻嘻嘻",
     *         "coinName": null,
     *         "coinNumber": null,
     *         "exchangeNGOT": 2,
     *         "createTime": "2018-06-04 14:26:46",
     *         "status": 0,
     *         "statusName": "待审核",
     *         "positions": [
     *             {
     *                 "id": 174419989910045696,
     *                 "name": "会长",
     *                 "fee": 200
     *             },
     *             {
     *                 "id": 174419989910045697,
     *                 "name": "海哥",
     *                 "fee": 100
     *             },
     *             {
     *                 "id": 174419989910045698,
     *                 "name": "炯少",
     *                 "fee": 100
     *             }
     *         ]
     *     },
     *     "success": true
     * }
     */
    @RequestMapping("ngoDetail")
    public Map<String,Object> ngoDetail(@RequestParam(name = "id")String id){
        return resultSuccess(organizationService.ngoDetail(Long.valueOf(id)));
    }



    /**
     * @api {POST} /organization/passed 通过审核
     * @apiGroup NGO组织
     * @apiVersion 1.0.1
     * @apiDescription 通过审核
     * @apiParam {Number} id id
     * @apiParam {String} contractAddress 合约地址
     * @apiParamExample {json} 请求示例：
     *       {
     *           "id":1,
     *           "tokenAddress":"zzz"
     *       }
     * @apiSuccessExample {json} 返回样例:
     *  {
     *      "success":true
     *  }
     */
    @RequestMapping("passed")
    public Map<String,Object> passed(AuditNgo auditNgo, HttpSession httpSession){
        String userId= String.valueOf(httpSession.getAttribute("userId"));
        organizationService.passed(Long.valueOf(userId),Long.valueOf(auditNgo.getId()),auditNgo.getContractAddress());
        return resultSuccess();
    }

    /**
     * @api {POST} /organization/notThrough 拒绝审核
     * @apiGroup NGO组织
     * @apiVersion 1.0.1
     * @apiDescription 拒绝审核
     * @apiParam {Number} id id
     * @apiParam {String} remark 备注
     * @apiParamExample {json} 请求示例：
     *       {
     *           "id":1,
     *           "remark":"zzz"
     *       }
     * @apiSuccessExample {json} 返回样例:
     *  {
     *      "success":true
     *  }
     */
    @RequestMapping("notThrough")
    public Map<String,Object> notThrough( AuditNgo auditNgo,HttpSession httpSession){
        String userId= String.valueOf(httpSession.getAttribute("userId"));
        organizationService.notThrough(Long.valueOf(userId),Long.valueOf(auditNgo.getId()),auditNgo.getRemark());
        return resultSuccess();
    }
}
