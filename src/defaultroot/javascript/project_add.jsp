  <%@include file="/javascript/validate_date.jsp" %>
  <SCRIPT LANGUAGE=javascript>
    <!--
    function dosubmit(){

          if(window.projectadd.name.value==""){
            alert("����д�����ơ���");
            return false;
          }
          if(window.projectadd.name.value.length>50){
            alert("�����ơ����Ȳ��ܳ���50���ַ�");
            return false;
          }

          if(window.projectadd.startDate.value==""){
            alert("����д����ʼ���ڡ���");
            return false;
          }
          if(window.projectadd.startDate.value.length!=8){
            alert("����д��ȷ�ġ���ʼ���ڡ���8λ");
            return false;
          }
          if(checkdate(window.projectadd.startDate,"����ʼ���ڡ���Ч")==false)
          {
            return false;
          }


          if(window.projectadd.endDate.value==""){
            alert("����д���������ڡ���");
            return false;
          }
          if(window.projectadd.endDate.value.length!=8){
            alert("����д��ȷ�ġ��������ڡ���8λ");
            return false;
          }
          if(checkdate(window.projectadd.endDate,"���������ڡ���Ч")==false)
          {
            return false;
          }

          if(window.projectadd.info.value==""){
            alert("����д��˵������");
            return false;
          }
          if(window.projectadd.info.value.length>1000){
            alert("��˵�������Ȳ��ܳ���1000���ַ�");
            return false;
          }

          if(window.projectadd.courseID.value==""){
            alert("��ѡ�񡰿γ̡���");
            return false;
          }

          if(window.projectadd.state.value==""){
            alert("��ѡ��״̬");
            return false;
          }

          if(window.projectadd.publishResult.value==""){
            alert("��ѡ�񡰳ɼ�״̬��");
            return false;
          }
        }
      //-->
  </SCRIPT>