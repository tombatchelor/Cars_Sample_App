/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var app = angular.module('superCars', ["ngRoute"]);
app.config(function ($routeProvider) {
    $routeProvider.when("/", {
        templateUrl: "home.html"
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
});
