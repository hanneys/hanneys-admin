package ngom.domain.assets;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

//所有币的列表
@Data
public class TokenList {

    private Long id;
    //币种名称
    private String coinName;
    //币种符号
    private String coinSymbol;
    //代币精度
    private int tokenDecimals;
    //币种单价
    private Float unitPrice = 0.00f;//币种单价(保留两位)
    //币种合约地址
    private String contractAddress;
    //币种的图标
    private String coinLogo;//
    //币种类型 ETH 以太坊,  AURORA 极光链
    private String flag;

    private Date createTime;//币种创建时间
    private Date updateTime;//币种更新时间
    //兑换 MT 比例
    private BigDecimal exchangeMT;
    //兑换 NGOT 比例
    private BigDecimal exchangeNGOT;


    public TokenList() {
    }

    public TokenList(Long id, String coinName, String coinSymbol, int tokenDecimals, Float unitPrice, String contractAddress, String coinLogo, String flag, Date createTime, Date updateTime) {
        this.id = id;
        this.coinName = coinName;
        this.coinSymbol = coinSymbol;
        this.tokenDecimals = tokenDecimals;
        this.unitPrice = unitPrice;
        this.contractAddress = contractAddress;
        this.coinLogo = coinLogo;
        this.flag = flag;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public BigDecimal getExchangeMT() {
        return exchangeMT;
    }

    public void setExchangeMT(BigDecimal exchangeMT) {
        this.exchangeMT = exchangeMT;
    }

    public BigDecimal getExchangeNGOT() {
        return exchangeNGOT;
    }

    public void setExchangeNGOT(BigDecimal exchangeNGOT) {
        this.exchangeNGOT = exchangeNGOT;
    }
}
