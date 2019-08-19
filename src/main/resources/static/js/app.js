


var app = angular.module('myApp', ["ngRoute",'pascalprecht.translate','ngStorage',
    'ngSanitize']);

app.config(function ($routeProvider, $locationProvider) {
    $routeProvider
        .when("/", {
            templateUrl: "view/welcome.html"

        })
        .when("/register", {
            templateUrl: "view/register.html",
            controller: "registerCtrl"

        })
        .when("/login", {
            templateUrl: "view/login.html",
            controller: "loginCtrl"
        }).when("/cpass", {
        templateUrl: "view/cpass.html",
        controller: "loginCtrl"
        })
        .when("/home", {
            templateUrl: "view/home.html",
            controller: "homeCtrl",
            resolve: {
                "check": function ($location, $window) {
                    if ($window.sessionStorage.hasOwnProperty('token')) {
                        $location.path('/home');
                    } else {
                        $location.path('/');    //redirect user to home.
                        alert("You don't have access here");
                    }
                }


            }
        }).otherwise({redirectTo: '/'});
});


app.config(function ($translateProvider) {

    $translateProvider
        .preferredLanguage("en")
        .fallbackLanguage("gr")
        .useSanitizeValueStrategy("escaped")
        .useUrlLoader("http://localhost:8080/key/"+"en");
});




