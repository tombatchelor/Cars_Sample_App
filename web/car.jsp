<%@ page import="java.util.*, supercars.*" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%
try {
			Random r = new Random();
			int i = r.nextInt(2000);
			Thread.sleep(i);
		}
		catch(Exception e){
			e.printStackTrace();
		}
%>
<jsp:include page="header.jsp" />
  <tr> 
    <td>
    <p><img width="150" height="100" src="images/cars/<c:out value="${car.photo}"/>.jpg" align="absmiddle"><img src="images/manufacturers/<c:out value="${manufacturer.logo}"/>">
   	<a href="enquire.do?car=<c:out value="${car.carId}"/>&carName=<c:out value="${car.name}"/>&nbsp;<c:out value="${car.model}"/>"><img src="images/enquire_button.gif"/></a>
    </p> 
    <p class="car"><b>Performance Summary:</b><br>
    <%
    Car car = (Car)request.getAttribute("car");
    Engine engine = car.getEngine();
    %>
    Engine: <%= engine.getCapacity()%>cc<br>
    Bhp: <%= engine.getBhp() %><br>
    Torque: <%= engine.getTorque()%><br>
    Top Speed: <%= engine.getTopSpeed()%>mph<br>
	0-60: <%= engine.getZeroSixty() %> sec
    </p>
    <p class="car">Name:&nbsp;<c:out value="${car.name}"/><br>
    Model:&nbsp;<c:out value="${car.model}"/><br>
    Summary:&nbsp;<c:out value="${car.summary}"/><br>
    Description:&nbsp;<c:out value="${car.description}"/><br>
    Price:&nbsp;£<c:out value="${car.price}"/><br>
    Colour:&nbsp;<c:out value="${car.colour}"/><br>
    Year:&nbsp;<c:out value="${car.year}"/></p>
  	</td>
  </tr>
  <tr>
  	<td><a href="car.do?query=carEnquiries&cid=<c:out value="${car.carId}"/>"><img src="images/view_enquiries_button.gif"/></a></td>
  </tr>
    <c:forEach var="enquiry" items="${enquiries}">
	  <tr class="grey"> 
	    <td> 
	    	<p>From:<c:out value="${enquiry.name}"/>,&nbsp;<c:out value="${enquiry.email}"/><br><c:out value="${enquiry.comment}"/><br><img src="images/line.gif"></p>
	  	</td>
	  </tr>
	  <tr>
	  	<td></td>
	  </tr>
	</c:forEach>
	<%	ArrayList al = (ArrayList)request.getAttribute("enquiries");
	if (al == null)
		{}
	else if (request.getParameter("query").equals("carEnquiries") && al.size() < 1) 
		out.print("<p>No Enquiries Found.</p>");%>
<jsp:include page="footer.jsp" />