$(function () {
  $.ajax({
    type: 'POST',
    url: 'sysUser/getUser',
    success: function (res) {
      if (res.success === true) {
        var html = template('my_tmp',res.data)
        $('#profile').html(html)
      }
    }
  });
});