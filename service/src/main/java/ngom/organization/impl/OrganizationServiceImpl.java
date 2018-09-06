package ngom.organization.impl;

import ngom.assets.CurrencyService;
import ngom.base.BaseService;
import ngom.base.organization.OrganizationStatusEnum;
import ngom.base.page.Pagination;
import ngom.domain.organization.AuditOrganizationLog_C;
import ngom.domain.organization.OrganizationPosition_C;
import ngom.domain.organization.Organization_C;
import ngom.domain.sys.SysUser_T;
import ngom.dto.organization.Ngo;
import ngom.dto.organization.NgoInfo;
import ngom.dto.organization.Position;
import ngom.exception.ErrorInfo;
import ngom.exception.NgomAdminException;
import ngom.organization.AuditOrganizationLogService;
import ngom.organization.OrganizationPositionService;
import ngom.organization.OrganizationService;
import ngom.sys.SysUserService;
import ngom.utils.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * NGO 组织
 * Created by hanaijun on 2018/6/4
 */
@Service
public class OrganizationServiceImpl extends BaseService implements OrganizationService  {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private OrganizationPositionService organizationPositionService;

    @Autowired
    private AuditOrganizationLogService auditOrganizationLogService;

    @Value("${ngo.image.url}")
    private String imageUrl;

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private SysUserService sysUserService;


    /**
     * ngo 列表
     * @param ngo
     * @return
     */
    @Override
    public Pagination<NgoInfo> getNgoList(Ngo ngo){
        Query query = new Query();
        if(ngo.getStatus()!=null){
            query.addCriteria(Criteria.where("status").is(ngo.getStatus()));
        }

        setQueryDate(ngo.getCreateTimeStart(),ngo.getCreateTimeEnd(),query);
        query.with(new Sort(Sort.Direction.DESC,"createTime"));

        //获取总条数
        Long totalCount = mongoTemplate.count(query,Organization_C.class);
        Integer skip = (ngo.getPageNo() - 1) * ngo.getPageSize();
        // skip相当于从那条记录开始
        query.skip(skip);
        // 从skip开始,取多少条记录
        query.limit(ngo.getPageSize());

        List<Organization_C> data = mongoTemplate.find(query, Organization_C.class);
        List<NgoInfo> result = data.stream().map(d->{
            NgoInfo ngoInfo = new NgoInfo();
            BeanUtils.copyProperties(d,ngoInfo);
            ngoInfo.setId(d.getId().toString());
            if(!StringUtils.isEmpty(d.getImage())){
                ngoInfo.setImage(imageUrl+d.getImage());
            }

            ngoInfo.setStatusName(OrganizationStatusEnum.getValueByKey(d.getStatus()));
            return ngoInfo;
        }).collect(Collectors.toList());
        Pagination<NgoInfo> page = new Pagination<>(ngo.getPageNo(),ngo.getPageSize(),totalCount);
        page.build(result);

        return page;

    }


    /**
     * ngo 详情
     * @param id
     * @return
     */
    @Override
    public NgoInfo ngoDetail(Long id){
        Query query=new Query(Criteria.where("id").is(id));
        Organization_C organization=mongoTemplate.findOne(query,Organization_C.class);
        Query auditLog=new Query(Criteria.where("oid").is(id));
        AuditOrganizationLog_C auditOrganizationLogC=mongoTemplate.findOne(auditLog,AuditOrganizationLog_C.class);
        // 职位列表
        List<OrganizationPosition_C> organizationPositionCs = organizationPositionService.getByOid(id);
        List<Position> positionList = organizationPositionCs.stream().map(o->{
            Position p=new Position();
            BeanUtils.copyProperties(o,p);
            p.setId(o.getId().toString());
            return p;
        }).collect(Collectors.toList());

        NgoInfo ngoInfo = new NgoInfo();
        BeanUtils.copyProperties(organization,ngoInfo);
        ngoInfo.setId(organization.getId().toString());
        if(!StringUtils.isEmpty(organization.getImage())){
            ngoInfo.setImage(imageUrl+organization.getImage());
        }
        ngoInfo.setPositions(positionList);
        ngoInfo.setStatusName(OrganizationStatusEnum.getValueByKey(organization.getStatus()));
        if(auditOrganizationLogC!=null){
            ngoInfo.setRemark(auditOrganizationLogC.getRemark());
            SysUser_T sysUser=sysUserService.getById(auditOrganizationLogC.getUserId());
            ngoInfo.setAuditer(sysUser.getName());
        }
        return ngoInfo;
    }


    /**
     * 通过审核
     * @param userId 当前用户id
     * @param id ngoid
     * @param tokenAddress 合约地址
     */
    @Override
    public void passed(Long userId, Long id, String tokenAddress){
        if(StringUtils.isEmpty(id)){
            throw new NgomAdminException(ErrorInfo.ORGANIZATION_ID_EMPTY_ERR_CODE,ErrorInfo.ORGANIZATION_ID_EMPTY_ERR_INFO);
        }
        if(StringUtils.isEmpty(tokenAddress)){
            throw new NgomAdminException(ErrorInfo.ORGANIZATION_TOKENADDRESS_EMPTY_ERR_CODE,ErrorInfo.ORGANIZATION_TOKENADDRESS_EMPTY_ERR_INFO);
        }
        audit(userId,id,null,OrganizationStatusEnum.PASSED.getStatus(),tokenAddress);
        Organization_C organizationC =mongoTemplate.findById(id,Organization_C.class);
        currencyService.auditOrganization(organizationC );
    }

    /**
     * 拒绝通过
     * @param userId 当前用户id
     * @param id ngoid
     * @param remark 备注信息
     */
    @Override
    public void notThrough(Long userId,Long id,String remark){
        if(StringUtils.isEmpty(id)){
            throw new NgomAdminException(ErrorInfo.ORGANIZATION_ID_EMPTY_ERR_CODE,ErrorInfo.ORGANIZATION_ID_EMPTY_ERR_INFO);
        }
        if(StringUtils.isEmpty(remark)){
            throw new NgomAdminException(ErrorInfo.ORGANIZATION_REMARK_EMPTY_ERR_CODE,ErrorInfo.ORGANIZATION_REMARK_EMPTY_ERR_INOF);
        }

        audit(userId,id,remark,OrganizationStatusEnum.NOT_THROUGH.getStatus(),null);
    }


    private void audit(Long userId,Long id,String remark,Integer status,String tokenAddress){
        Query query=new Query(Criteria.where("id").is(id));
        Update update = new Update();
        update.set("status",status);
        if(tokenAddress!=null){
            update.set("contractAddress",tokenAddress);
        }
        update(query,update);
        auditOrganizationLogService.insert(id,userId,status,remark);


    }

    private void update(Query query, Update update){
        update.set("updateTime",new Date());
        mongoTemplate.updateFirst(query,update,Organization_C.class);
    }


}
