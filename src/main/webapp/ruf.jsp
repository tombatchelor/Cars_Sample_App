<%@ page import="java.util.*" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<jsp:include page="header.jsp" />
<p class="normal">Ruf<br><img src="images/line.gif"></p>
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
<P class="normal" align="center"><img src="images/performance/ruf.gif"></p>
<P class="normal" align="center"><img src="images/performance/rgt.gif"></p>
<P class="car" >The latest year 2003 release from RUF Automobiles. A RUF manufactured sports version of the 996 Carrera constructed on the basis of a natural aspirated engine, that has a dry oil sump with a separate oil tank. Built at the RUF headquarters in Pfaffenhausen.

The RUF RGT with an elevated power output of 395 bhp. This normally aspirated power-plant performance is achieved through equipping motorsports based engine with four new camshafts, a performance exhaust system, air filter and a remapped engine management system.
</P>
<jsp:include page="footer.jsp" />