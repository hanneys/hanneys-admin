$(function(){
  $('.logout').click(function(){
       $.ajax({
          type:'POST',
          url:'sysUser/logout',
          success:function(res) {
              if (res.success === true) {
                 window.location.href="login.html"
              }
           }
       })
    })
})
