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
<p class="normal">Insurance<br><img src="images/line.gif"></p>
<P class="normal" align="center"><img src="images/insurance_car.gif"></p>
<P class="car" >Welcome to Supercar Insurance If you're looking for a cheap car insurance quote, you're in the right place.

Because we're direct on the net our overheads are lower, so we can offer car insurance for less. So if you're looking for cheap car insurance, why not get a quote from us now?

Our secure quote pages will provide you with a car insurance quote in minutes. This means that paying for your motor insurance online is completely safe.

And if you want to talk to someone for help or advice about your car insurance quote, there's a national rate number to call. You can even pay for your car insurance over the phone!
</P>
<%@ include file="footer.jsp" %>