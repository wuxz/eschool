<%@include file="/javascript/validate_date.jsp" %>
  <SCRIPT LANGUAGE=javascript>
    <!--
    function dosubmit(){

          if(window.adduser.loginName.value==""){
            alert("����д���û���¼������");
            return false;
          }
          if(window.adduser.loginName.value.length>20){
            alert("���û���¼�������Ȳ��ܳ���20���ַ�");
            return false;
          }

          if(window.adduser.name.value==""){
            alert("����д����������");
            return false;
          }
          if(window.adduser.name.value.length>20){
            alert("�����������Ȳ��ܳ���20���ַ�");
            return false;
          }

          if(window.adduser.gender.value==""){
            alert("����д���Ա𡱣�");
            return false;
          }
          if(window.adduser.gender.value.length>10){
            alert("���Ա𡱳��Ȳ��ܳ���10���ַ�");
            return false;
          }

          if(window.adduser.birthday.value==""){
            alert("����д�����ա���");
            return false;
          }
          if(window.adduser.birthday.value.length!=8){
            alert("����д��ȷ�ġ����ա���8λ");
            return false;
          }
          if(checkdate(window.adduser.birthday,"�����ա���Ч")==false)
          {
            return false;
          }

          if(window.adduser.tel.value==""){
            alert("����д���绰����");
            return false;
          }
          if(window.adduser.tel.value.length>20){
            alert("���绰�����Ȳ��ܳ���20���ַ�");
            return false;
          }

          if(window.adduser.email.value==""){
            alert("����д���ʼ�����");
            return false;
          }
          if(window.adduser.email.value.length>150){
            alert("���ʼ������Ȳ��ܳ���20���ַ�");
            return false;
          }

          if(window.adduser.address.value==""){
            alert("����д����ַ����");
            return false;
          }
          if(window.adduser.address.value.length>150){
            alert("����ַ�����Ȳ��ܳ���20���ַ�");
            return false;
          }

          if(window.adduser.classID.value==""){
            alert("��ѡ�����");
            return false;
          }

          if(window.adduser.userType.value==""){
            alert("��ѡ�����");
            return false;
          }

          if(window.adduser.state.value==""){
            alert("��ѡ��״̬");
            return false;
          }
        }
      //-->
  </SCRIPT>