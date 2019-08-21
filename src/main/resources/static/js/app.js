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
    }).when("/update", {
        templateUrl: "view/updateTranslations.html",
        controller: "translationCtrl"

    })
        .when("/home", {
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
    English: '35ea9f2c-e174-4618-8bfe-6a233c1d7452',
    Greek:'26dd1795-82c7-4a78-aa6e-1a1f4f27cce8'
});




