$(function(){
    // 加载导航侧边栏
    $(".navbar").load("og_header.html");
    $(".aside").load("broad.html");  
    // var data={
    //     status:0,
    //     pageNo:1,
    //     pageSize:200
    // };
    // $.ajax({
    //     type:'POST',
    //     url:'organization/getNgoList',
    //     data:data,
    //     success:function(res) {
    //         if (res.success === true) {
    //             matchs(res.data)
    //         }
    //     }
    // });
    // 发送请求渲染数据
        var page = $('.m-style');
        init(1, "", "", "");
        function init(pageNo, status, start, end) {
            var pageSize = 10;
            var data = {
                pageNo: pageNo,
                pageSize: pageSize,
                status: 0,
            };
            $.ajax({
                type: 'POST',
                url: 'organization/getNgoList',
                data: data,
                success: function (res) {
                    if (res.success === true) {
                        var result = res.data;
                        paging(result, pageSize, pageNo, status, start, end);
                        $("table tbody").html("");
                        matchs(result)
                    }
                }
            });
        }
        function paging(result, pageSize, pageNo, status, start, end) {
            page.pagination({
                pageCount: result.pages,
                totalData: result.total,
                showData: pageSize,
                current: pageNo,
                coping: true,
                homePage: '首页',
                endPage: '末页',
                prevContent: '上页',
                nextContent: '下页',
                callback: function (api) {
                    init(api.getCurrent(), status, start, end)
                }
            });
        }
    function matchs(rs) {
        var data={
            select: rs.list
        };
        document.getElementById("tbody").innerHTML = template("my_fb_tmp",data);
    }
    // 点击查看,进入页面,渲染数据
    $(document).on('click', '.check_user',function(){
        var id = this.id;
        window.location.href = "organization_detail?id="+id;
    });
    // 点击编辑,进入页面,渲染数据
    $(document).on('click', '.auditing',function(){
        var id = this.id;
        window.location.href = "organization_audit?id="+id;
    });
});
