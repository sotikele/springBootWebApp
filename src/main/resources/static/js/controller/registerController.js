app.controller('registerCtrl', function($scope,$http,$window,$location) {

    $scope.register=function () {
        var password=JSON.stringify($scope.password);
        var cpassword=JSON.stringify($scope.cpassword);
        if ($scope.name == null || $scope.email == null || $scope.password == null || $scope.cpassword == null ||$scope.username==null) {
            alert("Please fill all fields...!!!!!!");
        } else if (($scope.password.length) < 8) {
            alert("Password should at least 8 character in length...!!!!!!");
        } else if (!(password).match(cpassword)) {
            alert("Your passwords don't match. Try again?");
        } else {

            $http({
                method : "POST",
                url : "http://localhost:8080/register",
                data: {"name":$scope.name,"email":$scope.email,"password":$scope.password,"username":$scope.username}
            }).then(function mySuccess(response) {
                $window.sessionStorage['token'] = response.data.token;
               $location.path("/home");
            }, function myError(response) {
                $scope.message = response.data.message;
            });

        }};
    $scope.back=function () {
        $location.path("/");
    }
});