  <SCRIPT LANGUAGE=javascript>
    <!--

    function dosubmit(){

          if(window.answersheet.itemNumber.value==""){
            alert("����д����š���");
            return false;
          }
          if(window.answersheet.itemNumber.value.length>20){
            alert("����š����Ȳ��ܳ���20���ַ�");
            return false;
          }

          if(window.answersheet.answerNumber.value==""){
            alert("����д����ѡ��������");
            return false;
          }
          if(window.answersheet.answerNumber.value.length>20){
            alert("����ѡ���������Ȳ��ܳ���20���ַ�");
            return false;
          }

          if(window.answersheet.answertype.value==""){
            alert("��ѡ�����͡���");
            return false;
          }

          if(window.answersheet.answer.value==""){
            alert("����д���𰸡���");
            return false;
          }
          if(window.answersheet.answer.value.length>3000){
            alert("���𰸡����Ȳ��ܳ���3000���ַ�");
            return false;
          }

          if(window.answersheet.memo.value==""){
            alert("����д����ע����");
            return false;
          }
          if(window.answersheet.memo.value.length>1000){
            alert("����ע�����Ȳ��ܳ���1000���ַ�");
            return false;
          }
        }
      //-->
  </SCRIPT>