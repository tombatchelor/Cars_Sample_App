<%@ page import="java.util.*" %>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ include file="header.jsp" %>
<%
try {
			Random r = new Random();
			int i = r.nextInt(999);
			Thread.sleep(i);
		}
		catch(Exception e){
			e.printStackTrace();
		}
%>
<html>
<head>
<title>Enquire</title>
</head>
<body>
<p class="normal">Enquire<br><img src="images/line.gif"></p>
  <html:form action="enquire?query=save"> 
    <table width="80%" border="0"> 
      <tr> 
        <td>Name:<br>&nbsp;<html:text property="name" /></td>
      </tr>
      <tr> 
       <td>Email:<br>&nbsp;<html:text property="email" /></td> 
      </tr>
      <tr>
      	<td>Car:<br>&nbsp;<input type="text" name="carName" value="<%= request.getParameter("carName")%>"READONLY/></td>
      </tr>
      <tr>
      	<td><input type="hidden" name="carId" value="<%= request.getParameter("car")%>"/></td>
      </tr>
      <tr>
       <td>Description:<br><img src="images/line.gif"><br><br>&nbsp;<html:textarea rows="12" cols="45" property="comment" /></td> 
      </tr> 
      <tr> 
        <td><input type="image" src="images/submit_button.gif"/ align="absmiddle"></td> 
      </tr> 
    </table>     
  </html:form> 
</body>
</html>
<%@ include file="footer.jsp" %>