<%@include file="/javascript/validate_date.jsp" %>
  <SCRIPT LANGUAGE=javascript>
    <!--
    function dosubmit(){

          if(window.adduser.loginName.value==""){
            alert("请填写“用户登录名”！");
            return false;
          }
          if(window.adduser.loginName.value.length>20){
            alert("“用户登录名”长度不能超过20个字符");
            return false;
          }

          if(window.adduser.name.value==""){
            alert("请填写“姓名”！");
            return false;
          }
          if(window.adduser.name.value.length>20){
            alert("“姓名”长度不能超过20个字符");
            return false;
          }

          if(window.adduser.gender.value==""){
            alert("请填写“性别”！");
            return false;
          }
          if(window.adduser.gender.value.length>10){
            alert("“性别”长度不能超过10个字符");
            return false;
          }

          if(window.adduser.birthday.value==""){
            alert("请填写“生日”！");
            return false;
          }
          if(window.adduser.birthday.value.length!=8){
            alert("请填写正确的“生日”：8位");
            return false;
          }
          if(checkdate(window.adduser.birthday,"“生日”无效")==false)
          {
            return false;
          }

          if(window.adduser.tel.value==""){
            alert("请填写“电话”！");
            return false;
          }
          if(window.adduser.tel.value.length>20){
            alert("“电话”长度不能超过20个字符");
            return false;
          }

          if(window.adduser.email.value==""){
            alert("请填写“邮件”！");
            return false;
          }
          if(window.adduser.email.value.length>150){
            alert("“邮件”长度不能超过20个字符");
            return false;
          }

          if(window.adduser.address.value==""){
            alert("请填写“地址”！");
            return false;
          }
          if(window.adduser.address.value.length>150){
            alert("“地址”长度不能超过20个字符");
            return false;
          }

          if(window.adduser.classID.value==""){
            alert("请选择组别");
            return false;
          }

          if(window.adduser.userType.value==""){
            alert("请选择身份");
            return false;
          }

          if(window.adduser.state.value==""){
            alert("请选择状态");
            return false;
          }
        }
      //-->
  </SCRIPT>