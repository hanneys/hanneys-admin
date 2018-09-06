package ngom.base.page;

import lombok.Data;

import java.util.List;

/**
 * mongodb封装类
 * Created by hanaijun on 2018/6/4
 */
@Data
public class Pagination<T> {

    /** 每页显示条数 */
    private Integer pageSize;

    /** 当前页 */
    private Integer pageNum;

    /** 总页数 */
    private Integer pages;

    /** 查询到的总数据量 */
    private Long total;

    /** 数据集 */
    private List list;

    //是否为第一页
    private boolean isFirstPage = false;
    //是否为最后一页
    private boolean isLastPage = false;


    public Pagination(Integer pageNum,Integer pageSize,Long total){
        this.pageNum = pageNum;
        this.pageSize=pageSize;
        this.total=total;
    }


    /**
     * 判定页面边界
     */
    private void judgePageBoudary() {
        isFirstPage = pageNum == 1;
        isLastPage = pageNum.equals(pages);
    }

    /**
     * 处理查询后的结果数据
     *
     * @param items
     *            查询结果集
     * @param
     *
     */
    public void build(List items) {
        setList(items);
        Long count =  getTotal();
        Long divisor = count / getPageSize();
        Long remainder = count % getPageSize();
        setPages((int) (remainder == 0 ? divisor == 0 ? 1 : divisor : divisor + 1));
        judgePageBoudary();
    }
}
