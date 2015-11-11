<%@ page import="java.util.*" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<jsp:include page="header.jsp" />
<p class="normal">Alpina<br><img src="images/line.gif"></p>
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
<P class="normal" align="center"><img src="images/performance/alpina.gif"></p>
<P class="normal" align="center"><img src="images/performance/b10.gif"></p>
<P class="car" >Throughout ALPINA's 40-year history, its automobiles have been known for their notion of exclusivity. Over this period, BMW ALPINA's have evolved, taking advantage of, and improving upon, the technology offered at the time - and not just in the pursuit of more power, for improving performance is only one part of ALPINA's philosophy. The ALPINA customer is an enthusiast who appreciates high technology and seeks great driving pleasure from his car, yet prefers a car more refined and more practical than today's sports cars. The BMW ALPINA B10 V8 S is high performance in a most subtle guise. 
</P>
<jsp:include page="footer.jsp" />