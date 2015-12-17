<%@ page import="java.util.*" %>
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
<p class="normal"><img src="images/manufacturers/<c:out value="${manufacturer.logo}"/>" align="absmiddle"><%=((ArrayList)request.getAttribute("cars")).size()%> cars found:<img src="images/line.gif"></p>
<table>
<c:forEach var="car" items="${cars}">
  <tr class="grey"> 
    <td> 
    	<p><img src="images/cars/<c:out value="${car.photo}"/>.jpg"></p>
  	</td>
  	<td>
  		<p class="summary"><a href="car.do?query=car&cid=<c:out value="${car.carId}"/>"><c:out value="${car.model}"/>&nbsp;<c:out value="${car.name}"/></a>&nbsp;<c:out value="${car.summary}"/></p>
  	</td>
  </tr>
</c:forEach>
</table>
<%@ include file="footer.jsp" %>