  <SCRIPT LANGUAGE=javascript>
    <!--

    function dosubmit(){

          if(window.answersheet.itemNumber.value==""){
            alert("请填写“题号”！");
            return false;
          }
          if(window.answersheet.itemNumber.value.length>20){
            alert("“题号”长度不能超过20个字符");
            return false;
          }

          if(window.answersheet.answerNumber.value==""){
            alert("请填写“备选项数”！");
            return false;
          }
          if(window.answersheet.answerNumber.value.length>20){
            alert("“备选项数”长度不能超过20个字符");
            return false;
          }

          if(window.answersheet.answertype.value==""){
            alert("请选择“类型”！");
            return false;
          }

          if(window.answersheet.answer.value==""){
            alert("请填写“答案”！");
            return false;
          }
          if(window.answersheet.answer.value.length>3000){
            alert("“答案”长度不能超过3000个字符");
            return false;
          }

          if(window.answersheet.memo.value==""){
            alert("请填写“备注”！");
            return false;
          }
          if(window.answersheet.memo.value.length>1000){
            alert("“备注”长度不能超过1000个字符");
            return false;
          }
        }
      //-->
  </SCRIPT>