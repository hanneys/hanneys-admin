package ngom.domain.assets;


import lombok.Data;


import java.util.Date;

/**
 * @Currency_C 用户币种资产
 * @author zj_imac
 * @date 2018年05月14日11:18:28
 */
@Data
public class Currency_C {


    private Long id;
    private String userName;//用户名
    private String userAccount;//用户账户
    private Long userId;//用户id
    private String coinName;//币种名称
    private String flag;//币种类型 ETH 以太坊,  AURORA 极光链
    private String publicAddress;//公钥
    private String address;//对外用户看到的 钱包地址(收币转币)
    private String contractAddress;//合约地址
    private Float coinNumber = 0.00f;//币种数量
    private Float unitPrice = 0.00f;//币种单价(保留两位)
    private Float coinTotalPrice = 0.00f;//币种总额(保留两位)
    private String coinLogo;// 币种logourl
    private Date createTime;//币种创建时间
    private Date updateTime;//币种更新时间
    private Long meetId; //商会id
    private Long tokenId;//跟币种关联的id

    public Currency_C() {
    }

    public Currency_C(Long id, String userName, String userAccount, Long userId, String coinName, String flag, String publicAddress, String address, String contractAddress, Float coinNumber, Float unitPrice, Float coinTotalPrice, String coinLogo, Date createTime, Date updateTime) {
        this.id = id;
        this.userName = userName;
        this.userAccount = userAccount;
        this.userId = userId;
        this.coinName = coinName;
        this.flag = flag;
        this.publicAddress = publicAddress;
        this.address = address;
        this.contractAddress = contractAddress;
        this.coinNumber = coinNumber;
        this.unitPrice = unitPrice;
        this.coinTotalPrice = coinTotalPrice;
        this.coinLogo = coinLogo;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }


}
