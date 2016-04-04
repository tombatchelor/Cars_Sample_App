<%@ page import="java.util.*" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%
    try {
        Random r = new Random();
        int i = r.nextInt(999);
        Thread.sleep(i);
    } catch (Exception e) {
        e.printStackTrace();
    }
%>
<%@ include file="header.jsp" %>
<p class="normal">Leak<br><img src="images/line.gif"></p>
<p class="normal">
    This page generates a memory leak. It is backed by a static List of byte[] which can be added to using the below Form. Hitting submit will add a number of byte[]s to the List with each array of the specified size
</p>
<p class="normal">
    Enter the number and size of array to add to the collection:
</p>
<html:form action="/leak?query=save"> 
    <table width="80%" border="0">
        <tr> 
            <td>Number:<br>&nbsp;<html:text property="number" /></td> 
        </tr>
        <tr> 
            <td>Size:<br>&nbsp;<html:text property="size" /></td>
        </tr>
        <tr> 
            <td><input type="image" src="images/submit_button.gif"/ align="absmiddle"></td> 
        </tr> 
    </table> 
</html:form> 
<%@ include file="footer.jsp" %>