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
<p class="normal">About Us<br><img src="images/line.gif"></p>
<P class="normal" align="center"><img src="images/about_car.gif"></p>
<P class="car" >Supercar Trader is published in 13 regional editions. To find details of your local edition, simply click on the links below or use the map to locate your local Supercar Trader magazine.

 Launched in 2004, Supercar Trader is the biggest selling motoring magazine in Britain and is published across thirteen regional editions. More people buy and read Supercar Trader than any other motoring title and the magazine continues to be the market leader with a circulation of 351,654 (ABC Jan-June 2004) and a readership of 1,800,000 (NRS Jan-Dec 2003). 

Supercar Trader has positioned itself as the synonymous choice for buying and selling a motor vehicle with the success of the magazine built on the combination of photo-ads, choice and a unique structure of regional publishing, offering the combined advantages of local targeting and comprehensive national coverage. </P>
<%@ include file="footer.jsp" %>