var app = angular.module('myApp', ['ngRoute', 'pascalprecht.translate', 'ngStorage','ngCookies',
    'ngSanitize','ngTable']);

app.config(function ($routeProvider,$translateProvider) {
    angular.lowercase = angular.$$lowercase;
    $routeProvider
        .when("/", {
            templateUrl: "view/welcome.html",
            controller: "translationCtrl"
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

    }).when("/translations", {
        templateUrl: "view/translations.html",
        controller: "translationCtrl"

    }).when("/home", {
            templateUrl: "view/home.html",
            controller: "homeCtrl",
            resolve: {
                "check": function ($location, $window) {
                    if ($window.sessionStorage.hasOwnProperty('token')) {
                        $location.path('/home');
                    } else {
                        $location.path('/');
                        alert("You don't have access here");
                    }
                }


            }
        }).otherwise({redirectTo: '/'});


    $translateProvider
        .preferredLanguage("en")
        .fallbackLanguage("gr")
        .useLocalStorage()
        .useSanitizeValueStrategy("escaped")
        .useUrlLoader("http://localhost:8080/translations");



});




app.constant('languageID', {
    ENGLISH: '8d65597e-0041-49b3-9c3f-fc061fefeeaa',
    GREEK:'244dfc69-9ef7-429f-b152-8017933b54be'
});






