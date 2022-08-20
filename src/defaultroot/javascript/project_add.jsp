  <%@include file="/javascript/validate_date.jsp" %>
  <SCRIPT LANGUAGE=javascript>
    <!--
    function dosubmit(){

          if(window.projectadd.name.value==""){
            alert("请填写“名称”！");
            return false;
          }
          if(window.projectadd.name.value.length>50){
            alert("“名称”长度不能超过50个字符");
            return false;
          }

          if(window.projectadd.startDate.value==""){
            alert("请填写“开始日期”！");
            return false;
          }
          if(window.projectadd.startDate.value.length!=8){
            alert("请填写正确的“开始日期”：8位");
            return false;
          }
          if(checkdate(window.projectadd.startDate,"“开始日期”无效")==false)
          {
            return false;
          }


          if(window.projectadd.endDate.value==""){
            alert("请填写“结束日期”！");
            return false;
          }
          if(window.projectadd.endDate.value.length!=8){
            alert("请填写正确的“结束日期”：8位");
            return false;
          }
          if(checkdate(window.projectadd.endDate,"“结束日期”无效")==false)
          {
            return false;
          }

          if(window.projectadd.info.value==""){
            alert("请填写“说明”！");
            return false;
          }
          if(window.projectadd.info.value.length>1000){
            alert("“说明”长度不能超过1000个字符");
            return false;
          }

          if(window.projectadd.courseID.value==""){
            alert("请选择“课程”！");
            return false;
          }

          if(window.projectadd.state.value==""){
            alert("请选择状态");
            return false;
          }

          if(window.projectadd.publishResult.value==""){
            alert("请选择“成绩状态”");
            return false;
          }
        }
      //-->
  </SCRIPT>