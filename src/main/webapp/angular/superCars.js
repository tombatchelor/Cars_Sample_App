/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var app = angular.module('superCars', ["ngRoute"]);
app.config(function ($routeProvider) {
    $routeProvider.when("/", {
        templateUrl: "home.html",
        controller: "homeController"
    })
            .when("/home", {
                templateUrl: "home.html",
                controller: "homeController"
            })
            .when("/manufacturers", {
                templateUrl: "manufacturers.html",
                controller: "manufacturerController"
            })
            .when("/cars", {
                templateUrl: "cars.html",
                controller: "carsController"
            })
            .when("/car", {
                templateUrl: "car.html",
                controller: "carController"
            })
            .when("/search", {
                templateUrl: "search.html",
                controller: "searchController"
            })
            .when("/sell", {
                templateUrl: "sell.html",
                controller: "sellController"
            })
            .when("/enquire", {
                templateUrl: "enquire.html",
                controller: "enquireController"
            })
            .when("/insurance", {
                templateUrl: "insurance.html"
            })
            .when("/about", {
                templateUrl: "about.html",
                controller: "leakController"
            })
            .when("/thankyou", {
                templateUrl: "thankyou.html"
            })
            .when('/alpina', {
                templateUrl: "alpina.html"
            })
            .when("/amg", {
                templateUrl: "amg.html"
            })
            .when("/gembella", {
                templateUrl: "gembella.html"
            })
            .when("/mazdaspeed", {
                templateUrl: "mazdaspeed.html"
            })
            .when("/ruf", {
                templateUrl: "ruf.html"
            });
});

app.factory('carsUtils', function() {
    var manufacturerId;
    var carId;
    var carsUtilsService = {};
    
    carsUtilsService.setCarId = function(id) {
        carId = id;
    };
    
    carsUtilsService.getCarId = function() {
        return carId;
    };
    
    carsUtilsService.setManufacturerId = function(id) {
        manufacturerId = id;
    };
    
    carsUtilsService.getManufacturerId = function() {
        return manufacturerId;
    };
    return carsUtilsService;
});
