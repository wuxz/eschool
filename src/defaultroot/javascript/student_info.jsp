  <SCRIPT LANGUAGE=javascript>
    <!--
    function dosubmit(){

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
        }
      //-->
  </SCRIPT>