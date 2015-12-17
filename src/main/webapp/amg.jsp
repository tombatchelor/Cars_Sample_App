<%@ page import="java.util.*" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<jsp:include page="header.jsp" />
<p class="normal">AMG<br><img src="images/line.gif"></p>
<%
try {
			Random r = new Random();
			int i = r.nextInt(500);
			Thread.sleep(i);
		}
		catch(Exception e){
			e.printStackTrace();
		}
%>
<P class="normal" align="center"><img src="images/performance/amg.gif"></p>
<P class="normal" align="center"><img src="images/performance/slk.gif"></p>
<P class="car" >Inspired by the core values of Mercedes-Benz - like quality, safety, comfort and environmental protection - Mercedes-AMG GmbH develops and builds high-performance sports cars, options and accessories.

Development work at Mercedes-AMG is driven by the pursuit of performance, sporting prowess and individuality. The dynamism and sporty character of these fascinating and unique vehicles catapult them into "pole position" in their respective Mercedes-Benz model ranges.</P>
<jsp:include page="footer.jsp" />