<%@page import="java.util.Random"%>
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
<p class="normal">Confirmation<br><img src="images/line.gif"></p>
<P class="car" >Thanks for <%= request.getAttribute("actionText")%> through Supercars trader. Your enquiry will be passed on to seller.</P>
<%@ include file="footer.jsp" %>