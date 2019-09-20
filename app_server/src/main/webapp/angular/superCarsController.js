/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

app.controller('mainController', function ($scope) {

});

app.controller('homeController', function ($scope, $http, $location, carsUtils) {
    $http.get("../public/fuel")
            .then(function (response) {
                $scope.fuelPrices = response.data;
            });
    $http.get("../public/user")
            .then(function (response) {
                $scope.currentUser = response.data;
            });
    $scope.search = function () {
        carsUtils.setSearchTerm($scope.searchTerm)
        $location.path("/search");
    };
});

app.controller('manufacturerController', function ($scope, $http, $location, carsUtils) {
    $http.get("../public/manufacturer")
            .then(function (response) {
                $scope.manufacturers = response.data;
            });
    $scope.manufacturerLink = function (manufacturerId) {
        carsUtils.setManufacturerId(manufacturerId);
        $location.path("/cars");
    };
});

app.controller('carsController', function ($scope, $http, $location, carsUtils) {
    $scope.manufacturerId = carsUtils.getManufacturerId();
    $http.get("../public/car/manufacturer/" + $scope.manufacturerId)
            .then(function (response) {
                $scope.cars = response.data;
            });
    $http.get("../public/manufacturer/" + $scope.manufacturerId)
            .then(function (response) {
                $scope.manufacturer = response.data;
            });
    $scope.carLink = function (carId) {
        carsUtils.setCarId(carId);
        $location.path("/car");
    };
});

app.controller('carController', function ($scope, $http, $location, carsUtils) {
    $scope.carId = carsUtils.getCarId();
    $http.get("../public/car/" + $scope.carId)
            .then(function (response) {
                $scope.car = response.data;
            });
    $http.post("../public/enquiry/" + $scope.carId)
            .then(function (response) {
                $scope.enquiries = response.data;
            });
    $scope.enquireLink = function (carId) {
        carsUtils.setCarId(carId);
        $location.path("/enquire");
    };
    $scope.doError = function () {
        adddlert("This will error!");
    };
});

app.controller('searchController', function ($scope, $http, $location, carsUtils) {
    if (carsUtils.getSearchTerm() !== null) {
        $scope.searchTerm = carsUtils.getSearchTerm();
    }
    if ($scope.searchTerm !== null) {
        $http.post("../public/car/" + $scope.searchTerm)
                .then(function (response) {
                    $scope.cars = response.data;
                });
    }
    $scope.search = function () {
        $http.post("../public/car/" + $scope.searchTerm)
                .then(function (response) {
                    $scope.cars = response.data;
                });
    };
    $scope.carLink = function (carId) {
        carsUtils.setCarId(carId);
        $location.path("/car");
    };
});

app.controller('sellController', function ($scope, $http, $location) {
    $http.get("../public/manufacturer")
            .then(function (response) {
                $scope.manufacturers = response.data;
            });
    $scope.saveCar = function () {
        var carJSON = {};
        carJSON["name"] = $scope.name;
        carJSON["model"] = $scope.model;
        carJSON["manufacturerId"] = parseInt($scope.manufacturer);
        carJSON["colour"] = $scope.colour;
        carJSON["year"] = $scope.year;
        carJSON["price"] = $scope.price;
        carJSON["summary"] = $scope.summary;
        carJSON["description"] = $scope.description;
        carJSON["wheelSize"] = 0;
        carJSON["tyreSize"] = 0;
        carJSON["photo"] = "0";
        carJSON["manual"] = false;
        $http.put("../public/car", carJSON);
        $location.path("/thankyou");
    };
});

app.controller('enquireController', function ($scope, $http, $location, carsUtils) {
    $scope.carId = carsUtils.getCarId();
    $http.get("../public/car/" + $scope.carId)
            .then(function (response) {
                $scope.car = response.data;
            });
    $scope.saveEnquiry = function () {
        enquiryJSON = {};
        enquiryJSON["name"] = $scope.name;
        enquiryJSON["email"] = $scope.email;
        enquiryJSON["comment"] = $scope.comment;
        enquiryJSON["carId"] = $scope.car.carId;
        $http.put("../public/enquiry", enquiryJSON);
        $location.path("/thankyou");
    };
});

app.controller('leakController', function ($scope, $http) {
    $http.get("../public/leak")
            .then(function (response) {
                $scope.leakSize = response.data;
            });
    $scope.addToLeak = function () {
        $http.get("../public/leak/" + $scope.number + "/" + $scope.size);
        $http.get('../public/leak')
                .then(function (response) {
                    $scope.leakSize = response.data;
                });
    };
    $scope.resetLeak = function () {
        $http.delete("../public/leak");
        $http.get('../public/leak')
                .then(function (response) {
                    $scope.leakSize = response.data;
                });
    };
});

app.controller('loginController', function ($scope, $http, $location) {
    $scope.login = function () {
        var loginJSON = {};
        loginJSON["username"] = $scope.username;
        loginJSON["password"] = $scope.password;
        $http.post("../public/user/login", loginJSON);
        $location.path("/home");
    };
});

app.controller('logoutController', function ($scope, $http) {
    $http.get("../public/user")
            .then(function (response) {
                $scope.currentUser = response.data;
            });
    $http.get("../public/user/logout");
});

app.controller('preferencesController', function ($scope, $http) {
    $http.get("../public/preferences/all")
            .then(function (response) {
                $scope.preferences = response.data;
            });
            $scope.savePreferences = function() {
                $http.post("../public/preferences/all", $scope.preferences);
                $location.path("/preferences");
            }
});