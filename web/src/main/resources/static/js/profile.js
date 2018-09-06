$(function (){
    // 引入侧边栏
    $(".navbar").load("og_header.html");
    $(".aside").load("broad.html");
    // 发送AJAX
     $.ajax({
        type:'POST',
        url:'sysUser/getUser',
        success:function(res) {
            if (res.success === true) {
                matchs(res.data)
            }
        }
    });

    function matchs(rs) {
        $("#profile_email").html(template("tmp_email",rs));
        $("#profile_name").html(template("tmp_name",rs));
    }
    // 点击跳转修改密码页
    $('.btn').click(function(e){
       e.preventDefault(); 
      window.location.href ="password_reset.html" 
    })
})
