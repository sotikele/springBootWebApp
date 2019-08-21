app.controller('translationCtrl', function ($scope, $http, $window, $translate, ngTableParams) {

    $scope.languages = {
        English: "en",
        Greek: "gr"
    };


    $scope.changeLanguage = function (locale) {
        $translate.use(locale);

    };


    $scope.keyTable = new ngTableParams({

        page: 1,
        count: 5

    }, {
        //static value needs information from backend
        total: 28,

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