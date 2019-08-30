var app = angular.module('myApp', ['ngRoute', 'pascalprecht.translate', 'ngStorage', 'ngCookies',
    'ngSanitize', 'ngTable', 'flow']);

app.config(function ($routeProvider, $translateProvider) {
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


app.config(["flowFactoryProvider",
    function (flowFactoryProvider) {


        // # #####################################################################
        // # Configure file upload
        // # #####################################################################

        flowFactoryProvider.defaults = {
            testChunks: false,
            target: "/upload",
            permanentErrors: [404, 500, 501],
            maxChunkRetries: 1,
            chunkRetryInterval: 5000,
            simultaneousUploads: 4,
            generateUniqueIdentifier: function () {
                return "xxxxxxxx-xxxx-xxxx-yxxx-xxxxxxxxxxxx".replace(/[xy]/g, function (c) {
                    var r, v;
                    r = Math.random() * 16 | 0;
                    v = (c === "x" ? r : r & 0x3 | 0x8);
                    return v.toString(16);
                });
            }
        }
    }]);


app.constant('languageID', {
    ENGLISH: '8d65597e-0041-49b3-9c3f-fc061fefeeaa',
    GREEK: '244dfc69-9ef7-429f-b152-8017933b54be'
});









