  <%@include file="/javascript/validate_date.jsp" %>
  <SCRIPT LANGUAGE=javascript>
    <!--

    function dosubmit(){

          if(window.addcourse.courseName.value==""){
            alert("请填写“课程名”！");
            return false;
          }
          if(window.addcourse.courseName.value.length>20){
            alert("“课程名”长度不能超过20个字符");
            return false;
          }

          if(window.addcourse.startDate.value==""){
            alert("请填写“开始日期”！");
            return false;
          }
          if(window.addcourse.startDate.value.length!=8){
            alert("请填写正确的“开始日期”：8位");
            return false;
          }
          if(checkdate(window.addcourse.startDate,"“开始日期”无效")==false)
          {
            return false;
          }

          if(window.addcourse.endDate.value==""){
            alert("请填写“结束日期”！");
            return false;
          }
          if(window.addcourse.endDate.value.length!=8){
            alert("请填写正确的“结束日期”：8位");
            return false;
          }
          if(checkdate(window.addcourse.endDate,"“结束日期”无效")==false)
          {
            return false;
          }

          if(window.addcourse.info.value==""){
            alert("请填写“说明”！");
            return false;
          }
          if(window.addcourse.info.value.length>1000){
            alert("“说明”长度不能超过1000个字符");
            return false;
          }

          if(window.addcourse.state.value==""){
            alert("请选择“状态”！");
            return false;
          }

          if(window.addcourse.teacher.value==""){
            alert("请选择“教师”！");
            return false;
          }
        }
      //-->
  </SCRIPT>