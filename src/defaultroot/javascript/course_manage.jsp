  <%@include file="/javascript/validate_date.jsp" %>
  <SCRIPT LANGUAGE=javascript>
    <!--

    function dosubmit(){

          if(window.addcourse.courseName.value==""){
            alert("����д���γ�������");
            return false;
          }
          if(window.addcourse.courseName.value.length>20){
            alert("���γ��������Ȳ��ܳ���20���ַ�");
            return false;
          }

          if(window.addcourse.startDate.value==""){
            alert("����д����ʼ���ڡ���");
            return false;
          }
          if(window.addcourse.startDate.value.length!=8){
            alert("����д��ȷ�ġ���ʼ���ڡ���8λ");
            return false;
          }
          if(checkdate(window.addcourse.startDate,"����ʼ���ڡ���Ч")==false)
          {
            return false;
          }

          if(window.addcourse.endDate.value==""){
            alert("����д���������ڡ���");
            return false;
          }
          if(window.addcourse.endDate.value.length!=8){
            alert("����д��ȷ�ġ��������ڡ���8λ");
            return false;
          }
          if(checkdate(window.addcourse.endDate,"���������ڡ���Ч")==false)
          {
            return false;
          }

          if(window.addcourse.info.value==""){
            alert("����д��˵������");
            return false;
          }
          if(window.addcourse.info.value.length>1000){
            alert("��˵�������Ȳ��ܳ���1000���ַ�");
            return false;
          }

          if(window.addcourse.state.value==""){
            alert("��ѡ��״̬����");
            return false;
          }

          if(window.addcourse.teacher.value==""){
            alert("��ѡ�񡰽�ʦ����");
            return false;
          }
        }
      //-->
  </SCRIPT>