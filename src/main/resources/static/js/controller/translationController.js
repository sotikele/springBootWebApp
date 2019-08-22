app.controller('translationCtrl', function ($scope, $http, $window, $translate, ngTableParams, languageID, $location) {
    var totalKeys;

    $scope.languages = {
        English: "en",
        Greek: "gr"
    };


    $scope.changeLanguage = function (locale) {
        $translate.use(locale);

    };


    $scope.updateTranslation = function (index) {

        $http({
            method: "POST",
            url: "http://localhost:8080/key/update",
            data: {
                'id': $scope.keys[index].id, 'translations': {
                    "00a6b293-8279-4bfb-85e1-a6fa375a726e": $scope.keys[index].translations[languageID.GREEK],
                    "72c8e89b-431f-421d-840c-9d5d2a887f06": $scope.keys[index].translations[languageID.ENGLISH]
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
                //static value needs information from backend
                total: response.data,

                getData: function ($defer, params) {
                    $http({
                        method: "GET",
                        url: "http://localhost:8080/keys/" + (params.page() - 1) + "/" + params.count()
                    }).then(function mySuccess(response) {

                        $scope.keys = response.data;
                        //params.total();
                        $defer.resolve($scope.keys);

                    });
                }

            });

        });
    }


});