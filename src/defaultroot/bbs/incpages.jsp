<%function writeNull(){%>
<div align="center">                                            
  <center>                                               
   <table border="0" width="600" >                                                
  <tr>                                                
    <td width="600" >                                                
      <p align="center">暂时无记录！！！</p>                                                
    </td>                                                
  </tr>                                                 
</table>                                            
  </center>                                  
</div>                                  
<%}%>

<%
function writeTitle(){
String lyb_name;
%>
<!--表头部分 -->
 <div align="center">
  <center>
 <table border="1" width="600" bordercolorlight="#000000" bordercolordark="#FFFFFF" bgcolor="#FAD185" cellspacing="0" cellpadding="3">
  <tr>
    <td width="30" bgcolor="#000080">                                                  
      <p align="center"><a href="bbsadd.jsp?boardid=<%=boardid%>" target="_self"><font color="#FFFFFF">留言</font></a></p>                                              
    </td>                                                                                              
    <td width="30" bgcolor="#000080">                                                  
     <p align="center"><a href="bbslist.jsp?boardid=<%=boardid%>" target="_self"><font color="#FFFFFF">刷新</font></a></p>                                             
    </td> 
    <td width="30" bgcolor="#000080">                                                
     <p align="center"><a href="query.jsp"><font color="#FFFFFF">查询</font></a></p> 
    </td> 
    <td width="100"  bgcolor="#000080">                                              
      <p align="center"><a href="bbslist.jsp?boardid=<%=boardid%>&method=4"><font color="#FFFFFF">精华区</font></a></p>                                          
    </td>
    <td width="200" bgcolor="#000080">  
<!--
-->
    </td>                                                                                               
    <td width="110" valign="middle" bgcolor="#000080">                                                  
      <form method="GET" action="bbslist.jsp" style="margin-top: 0; margin-bottom: 0">                  
        <p align="center" style="margin-top: 0; margin-bottom: 0"><font color="#FFFFFF">转到:<input type="text" name="page" size="3" value="1">页<input type="submit" value="GO" name="GO"></font></p>                                                                           
        <input type="hidden" name="boardid" value="<%=boardid%>">      
        <input type="hidden" name="method" value="<%=method%>">      
        <input type="hidden" name="par" value="<%=par%>">      
        </form>                                                                                     
    </td>                                                                                               
  </tr>                                                                                               
</table>                                                                                            
  </center>                                                                                           
 </div>                                                                                
                                                                                
<!--表头部分结束 -->                                                                    
<%}%>