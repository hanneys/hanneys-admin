$(function(){
    // 加载导航侧边栏
    $(".navbar").load("og_header.html");
    $(".aside").load("broad.html");
    function getQueryString(name) {
        var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);//search,查询？后面的参数，并匹配正则
        if(r!=null)return  unescape(r[2]); return null;
    }
    var id=getQueryString("id");
    var data= {
        id:id
    };
    // 发送请求
    $.ajax({
        type:'POST',
        url:'organization/ngoDetail',
        data:data,
        success:function(res) {
            if (res.success === true) {
                list(res.data)
            }
        }
    });
    function list(rs) {
        document.getElementById("review_organization").innerHTML = template("my_list",rs);
        document.getElementById("my_button").innerHTML = template("my_button_tmp",rs);
    };
    // 点击通过审核
    $(document).on('click', '.through',function() {
        layer.prompt({
            formType: 2,
            value: '',
            title: '请输入合约地址'
        }, function (value, index, elem) {
            var data = {
                id: getQueryString("id"),
                contractAddress: value
            };
            $.ajax({
                type: 'POST',
                url: 'organization/passed',
                data: data,
                success: function (res) {
                    if (res.success === true) {
                        layer.close(index);
                        parent.location.reload();
                    }
                }
            });

        })
    });
    //点击拒绝通过
     $(document).on('click', '.not_through',function(){

         layer.prompt({
             formType: 2,
             value: '',
             title: '请输入备注'
         }, function (value, index, elem) {
             var data = {
                 id: getQueryString("id"),
                 remark: value
             };
             $.ajax({
                 type: 'POST',
                 url: 'organization/notThrough',
                 data: data,
                 success: function (res) {
                     if (res.success === true) {
                         layer.close(index);
                         parent.location.reload();
                     }
                 }

             });

         });
    })
});
