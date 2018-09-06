$(function(){
  $(".log_btn").on('click', function login() {
     var email = $.trim($("#email").val());
     var password = $.trim($("#password").val());
     var r_eamil=/^\w+@\w+(.)\w+$/;
     if (!email) {
           layer.alert('请输入邮箱');
          return false;
      };
     if(r_eamil.test(email) === false){
           layer.alert('邮箱格式不正确,请重新输入');
          return false;
      };
       if (!password) {
          layer.alert('请输入密码');
          return false;
      };
      var data={
            email:$("#email").val(),
            password:$("#password").val()
        };
        // 发送请求
            $.ajax({
            type:'POST',
            url:'user/login',
            data:data,
            success:function(res) {
                if (res.success === true) {
                    window.location.href = "organization_list";
                }else{
                   layer.alert(res.message);
                }
             }
         });
       });
  // 回车登录
    $(document).keyup(function(e){
      if(e.keyCode===13){
         $(".log_btn").click();
      }
    })
});
