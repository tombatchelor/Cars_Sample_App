<%@ page import="java.util.*" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<jsp:include page="header.jsp" />
<p class="normal">MazdaSpeed<br><img src="images/line.gif"></p>
<P class="normal" align="center"><img src="images/performance/mazda.gif"></p>
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
<P class="normal" align="center"><img src="images/performance/rx8.gif"></p>
<P class="car" >Mazda Motor Corporation announces the release of a limited edition RX-8 Mazdaspeed Version, which is based on the RX-8 and fitted with Mazdaspeed* brand tune-up parts for enhanced sports driving performance. The RX-8 Mazdaspeed Version goes on sale from the middle of February at Mazda Anfini and Mazda dealerships throughout Japan. 

The new RX-8 Mazdaspeed Version is based on the RX-8 Type S. Mazda has tuned up the engine using it`s exclusively designed PCM (Powertrain Control Module) to match the modified intake and exhaust systems of the Mazdaspeed Version, as well as going through balance adjustments around the eccentric shaft in response to the lightweight flywheel. Mazda has also tuned up the suspension and fitted aero parts to further enhance sports driving performance. Those modifications, carried out under Mazda`s strict quality control system, provide the RX-8 Mazdaspeed Version with well-balanced performance. 

</P>
<jsp:include page="footer.jsp" />