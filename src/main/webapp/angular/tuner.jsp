<%-- 
    Document   : tuner.jsp
    Created on : Oct 13, 2016, 10:21:06 AM
    Author     : tom.batchelor
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
 <%
            String tunerName = request.getParameter("tunerName");
        %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=tunerName%></title>
    </head>
    <body>

        <script>
            tunerName = "<%=tunerName%>";
            // document.querySelector('.tuner').innerHTML = tunerName;
        </script>
        <link rel="stylesheet" type="text/css" href="trader.css" />
        <table>
            <tr>
                <td colspan="5" align="left">
                    <a href="index.jsp">
                        <IMG SRC="images/logo.gif">
                    </a>
                </td>
            </tr>
        </table>
        <br />

        <% if (tunerName.equals("AMG")) { %>
        <p class="normal">
        <div class="tuner"></div><br />
            <img src="images/line.gif" />
        </p>
        <p class="normal" align="left">
            <img src="images/performance/amg.gif"/>
        </p>
        <p class="normal" align="left">
            <img src="images/performance/slk.gif"/>
        </p>
        <p class="car" >
            Inspired by the core values of Mercedes-Benz - like quality, safety, comfort and environmental
            protection - Mercedes-AMG GmbH develops and builds high-performance sports cars, options and accessories.

            Development work at Mercedes-AMG is driven by the pursuit of performance, sporting prowess and
            individuality. The dynamism and sporty character of these fascinating and unique vehicles catapult
            them into "pole position" in their respective Mercedes-Benz model ranges.
        </p>
        <% } %>

        <% if (tunerName.equals("Alpina")) { %>
        <p class="normal">
            <div class="tuner"></div><br />
            <img src="images/line.gif" />
        </p>
        <p class="normal" align="left">
            <img src="images/performance/alpina.gif" />
        </p>
        <p class="normal" align="left">
            <img src="images/performance/b10.gif" />
        </p>
        <p class="car" >
            Throughout ALPINA's 40-year history, its automobiles have been known for their notion of exclusivity.
            Over this period, BMW ALPINA's have evolved, taking advantage of, and improving upon, the technology
            offered at the time - and not just in the pursuit of more power, for improving performance is only one
            part of ALPINA's philosophy. The ALPINA customer is an enthusiast who appreciates high technology and
            seeks great driving pleasure from his car, yet prefers a car more refined and more practical than today's
            sports cars. The BMW ALPINA B10 V8 S is high performance in a most subtle guise. 
        </p>
        <% } %>

        <% if (tunerName.equals("Gembella")) { %>
        <p class="normal">
            <div class="tuner"></div><br />
            <img src="images/line.gif" />
        </p>
        <p class="normal" align="left">
            <img src="images/performance/gembella.gif" />
        </p>
        <p class="normal" align="left">
            <img src="images/performance/gcar.gif" />
        </p>
        <p class="car" >
            Porsche customizer Gemballa from Leonberg thrills the hearts of the drivers of the
            noble Porsche-SUV by its new high-performance cure, the Gemballa V6 Turbo conversion
            kit for the Porsche Cayenne V6, for the Cayenne V6 reaches new dimensions of
            performance with the help of the new Gemballa conversion kit.
        </p>
        <% } %>

        <% if (tunerName.equals("Mazdaspeed")) { %>
        <p class="normal">
            <div class="tuner"></div><br />
            <img src="images/line.gif">
        </p>
        <p class="normal" align="left">
            <img src="images/performance/mazda.gif" />
        </p>
        <p class="normal" align="left">
            <img src="images/performance/rx8.gif" />
        </p>
        <p class="car">
            Mazda Motor Corporation announces the release of a limited edition RX-8 Mazdaspeed
            Version, which is based on the RX-8 and fitted with Mazdaspeed* brand tune-up parts
            for enhanced sports driving performance. The RX-8 Mazdaspeed Version goes on sale
            from the middle of February at Mazda Anfini and Mazda dealerships throughout Japan. 
        </p>
        <p class="car">
            The new RX-8 Mazdaspeed Version is based on the RX-8 Type S. Mazda has tuned
            up the engine using it`s exclusively designed PCM (Powertrain Control Module)
            to match the modified intake and exhaust systems of the Mazdaspeed Version,
            as well as going through balance adjustments around the eccentric shaft in
            response to the lightweight flywheel. Mazda has also tuned up the suspension
            and fitted aero parts to further enhance sports driving performance. Those
            modifications, carried out under Mazda`s strict quality control system,
            provide the RX-8 Mazdaspeed Version with well-balanced performance. 
        </p>
        <% } %>

        <% if (tunerName.equals("RUF")) { %>
        <p class="normal">
            <div class="tuner"></div><br />
            <img src="images/line.gif">
        </p>
        <p class="normal" align="left">
            <img src="images/performance/ruf.gif" />
        </p>
        <p class="normal" align="left">
            <img src="images/performance/rgt.gif" />
        </p>
        <p class="car" >
            The latest year 2003 release from RUF Automobiles. A RUF manufactured sports
            version of the 996 Carrera constructed on the basis of a natural aspirated
            engine, that has a dry oil sump with a separate oil tank. Built at the RUF
            headquarters in Pfaffenhausen.
        <p>
        <p class="car">
            The RUF RGT with an elevated power output of 395 bhp. This normally
            aspirated power-plant performance is achieved through equipping motorsports
            based engine with four new camshafts, a performance exhaust system, air
            filter and a re-mapped engine management system.
            </[>
            <% }%>
            <IMG SRC="images/pipe.gif">
                    <script>document.querySelector('.tuner').innerHTML = tunerName;</script>
    </body>
</html>
