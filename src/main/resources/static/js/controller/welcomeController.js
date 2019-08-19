

app.controller('welCtrl', function ($scope, $translateProvider,$http) {

    $scope.languages = {
        English: "en",
        Greeklish: "gr"
    };


    $scope.changeLanguage = function () {
        $http({
            method : "GET",
            url : "http://localhost:8080/key/"+$scope.selectedLanguage
        }).then(function mySuccess(response) {
            $translateProvider.useUrlLoader("http://localhost:8080/key/"+$scope.selectedLanguage);



            // $translate.use($scope.selectedLanguage);

         }, function myError(response) {
            $scope.message = response.data.message;
        });
    };
});

