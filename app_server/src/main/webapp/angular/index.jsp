<%-- 
    Document   : index.jsp
    Created on : Aug 30, 2016, 2:40:33 PM
    Author     : tom.batchelor
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script src="angular.js"></script>
        <script src="angular-route.js"></script>
        <title>Supercar Trader</title>
    </head>
    <body>
    <center>
        <div ng-app="superCars" ng-controller="mainController">
            <link rel="stylesheet" type="text/css" href="trader.css" />
            <table>
                <tr>
                    <td colspan="6" align="center">
                <center>
                    <a href="#home">
                        <img src="images/logo.gif">
                    </a>
                </center>
                </td>
                </tr>
                <tr>
                    <td>
                        <a href="#manufacturers">
                            <img src="images/cars.png">
                        </a>
                    </td>
                    <td>
                        <a href="#search">
                            <img src="images/search.png">
                        </a>
                    </td>
                    <td>
                        <a href="#sell">
                            <img src="images/sell.png">
                        </a>
                    </td>
                    <td>
                        <a href="#enquire">
                            <img src="images/enquire.png">
                        </a>
                    </td>
                    <td>
                        <a href="#insurance">
                            <img src="images/insurance.png">
                        </a>
                    </td>
                    <td>
                        <a href="#about">
                            <img src="images/about.png">
                        </a>
                    </td>
                </tr>
            </table>
            <div ng-view></div>
        </div>
    </center>
    <script src="superCars.js"></script>
    <script src="superCarsController.js"></script>
</body>
</html>
