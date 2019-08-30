app.controller('translationCtrl', function ($scope, $http, $window, $translate, ngTableParams, languageID) {


    $scope.changeLanguage = function (locale) {
        $translate.use(locale);

    };


    $scope.updateTranslation = function (index) {

        $http({
            method: "POST",
            url: "http://localhost:8080/key/update",
            data: {
                'id': $scope.keys[index].id, 'translations': {
                    "gr": $scope.keys[index].translations.gr,
                    "en": $scope.keys[index].translations.en
                }
            }

        }).then(function mySuccess() {
                alert("translations saved");
            },
            function myError() {
                alert("update failed");
            });


    };

    $scope.initTable = function () {
        $http({
            method: "GET",
            url: "http://localhost:8080/totalKeys"
        }).then(function mySuccess(response) {

            $scope.keyTable = new ngTableParams({

                page: 1,
                count: 10

            }, {

                total: response.data,

                getData: function ($defer, params) {
                    $http({
                        method: "GET",
                        url: "http://localhost:8080/keys/" + (params.page() - 1) + "/" + params.count()
                    }).then(function mySuccess(response) {

                        $scope.keys = response.data;

                        $defer.resolve($scope.keys);

                    });
                }

            });

        });
    }


});