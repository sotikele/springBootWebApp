app.controller('loginCtrl', function($scope,$http,$window,$location) {
    $scope.checkStatus="";


    $scope.login=function () {

        $http({
            method : "POST",
            url : "http://localhost:8080/login",
            data: {"username":$scope.username,"password":$scope.password}
        }).then(function mySuccess(response) {
            if(response.data.token!=null) {
                $window.sessionStorage['token'] = response.data.token;
                $location.path("/home");

            }


            else{
                $scope.checkResults="your username or password is incorrect"
            }
        }, function myError(response) {
            $scope.checkResults = response.status;
        });

    };
    $scope.back=function () {
        $location.path("/");
    };




    $scope.savePassword=function () {
        var newPassword = JSON.stringify($scope.newPassword);
        var repNewPassword = JSON.stringify($scope.repNewPassword);
        if (newPassword.match(repNewPassword)) {
            $http({
                method: "PUT",
                url: "http://localhost:8080/user/changePassword",
                headers: {
                    'Authorization': "Bearer " + $window.sessionStorage['token']
                },
                data: {"password": $scope.newPassword, "oldPassword": $scope.oldPassword}
            }).then(function mySuccess(response) {

                $location.path("/home");
                alert("Saved");


            }, function myError(response) {
                $scope.message = "old password does not match";
            });
        }

    else{
        alert("passwords does not match");
    }
    }
});