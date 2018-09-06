$(function (){
    // 引入侧边栏
     $(".navbar").load("og_header.html");
     $(".aside").load("broad.html");
     // 1.发送 ajax 获取密码
      getPsd ();
      var psd,oldPwd;
      function getPsd () {
          $.ajax({
          type:'POST',
          url:'sysUser/getUser',
          success:function(res) {
              if (res.success === true) {
                 psd = res.data.password;
              }
           }
       }); 
     }
   
        // 点击修改密码判断
        $('.btn').on('click',function(e){
           e.preventDefault();
           oldPwd = $.trim($('.old_pwd').val())
           // 判断用户输入的原来的密码
           if(md5(oldPwd) !== psd) {
               layer.alert('您输入的原密码不正确,请重新输入!');
               return false;
           }
           // 判断用户两次输入的密码是否一致
           // 密码至少8位，要求必须字母、数字加英文符号（不包含空格 cuijian0502）
             var regPwd= /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$/;
             var newPwd = $.trim($('.new_pwd').val());
             var reNewpwd = $.trim($('.re_newpwd').val());
             // 判断密码格式
             if (regPwd.test(newPwd)===false) {
                layer.alert('密码格式不正确!');
                 return false;
             }
             if(newPwd !== reNewpwd){
              layer.alert('两次输入的密码不一致,请重新输入!');
              return false;
           }
           // 发送请求
          var data = {
            password:oldPwd,
            newPassword:newPwd
          }
          $.ajax({
            type:"POST",
            data:data,
            url:"sysUser/updatePassword",
            beforeSend:function(){
              // 判断输入的不能为空
              if(!newPwd){
              layer.alert('请输入密码');
              return false;
              }
            },
            success:function(res){
              if (res.success === true) {
              // 修改密码成功调用退出接口返回登录页面
              $.ajax({
                  type:'POST',
                  url:'sysUser/logout',
                  success:function(res) {
                      if (res.success === true) {
                         window.location.href="login.html"
                      }
                   }
               }); 
              }else{
                layer.alert(res.message);
              }
            }
          });
      })
})
