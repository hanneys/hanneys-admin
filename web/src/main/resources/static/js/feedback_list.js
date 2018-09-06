$(function() {
  // 加载导航侧边栏
  $(".navbar").load("og_header.html");
      $(".aside").load("broad.html");
      //日期时间范围
      laydate.render({
          elem: '#dataTable',
          type: 'datetime',
          range: true,
          format: 'yyyy-MM-dd HH:mm:ss',
          isInitValue: true,
          done: function (value, date, endDate) {
              var start = value.split(" - ")[0];
              var end = value.split(" - ")[1];
              var status = $("#status").val();
              init(1, status, start, end);
          }
      });
      // form-control获取光标删除选择时间按钮
      $('.form-control').focus(function(){
        $('.layui-laydate-footer>.laydate-btns-time').css('display','none');
      })
      var page = $('.m-style');
      init(1, "", "", "");
      function init(pageNo, status, start, end) {
          var pageSize = 10;
          var data = {
              pageNo: pageNo,
              pageSize: pageSize,
              status: status,
              createTimeStart: start,
              createTimeEnd: end
          };
          console.log(data)
          $.ajax({
              type: 'POST',
              url: 'feedback/getFeedbackList',
              data: data,
              success: function (res) {
                console.log(res)
                  if (res.success === true) {
                      var result = res.data;
                      paging(result, pageSize, pageNo, status, start, end);
                      // $("table tbody").html("");
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
          var data = {
              select: rs.list
          };
          document.getElementById("tbody").innerHTML = template("my_fb_tmp", data);
      }
      // // 点击查看,进入页面,渲染数据
      // $(document).on('click', '.check_user', function () {
      //     var id = this.id;
      //     window.location.href = "organization_detail?id=" + id;
      // });
      // // 点击编辑,进入页面,渲染数据
      // $(document).on('click', '.auditing', function () {
      //     var id = this.id;
      //     window.location.href = "organization_audit?id=" + id;
      // });
      $("#status").change(function () {
          var status = $("#status").val();
          var startEnd = $("#dataTable").val();
          var start = startEnd.split(" - ")[0];
          var end = startEnd.split(" - ")[1];
          init(1, status, start, end);
      });
  });
