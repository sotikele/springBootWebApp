app.controller('homeCtrl', function($scope,$http,$window,$location,$timeout) {

    $scope.userMessages = true;

    $scope.showUserBooks=function () {
        $http({
            method : "GET",
            headers: {
                'Authorization': "Bearer "+$window.sessionStorage['token']
            },
            url : "http://localhost:8080/user/books"

        }).then(function mySuccess(response) {
                if(response.data[0]!=null){
                    $scope.myData=response.data;
                    $scope.noBooksMessage=" ";
                }
                else{
                    $scope.myData=null;
                    $scope.noBooksMessage="you dont have any book in your library";
                    $timeout(function() {
                        $scope.userMessages = false;
                    }, 1000);
                    $scope.userMessages = true;
                }
            },
            function myError(response) {
                $scope.noBooksMessage = response.status;
            });

    };
    $scope.addBook=function () {
        $http({
            method : "POST",
            url : "http://localhost:8080/user/book",
            headers: {
                'Authorization': "Bearer "+$window.sessionStorage['token']
            },
            data: {'title':$scope.title,'author':$scope.author}

        }).then(function mySuccess() {
            $scope.addedMessage = "added"+" "+$scope.title;
        });
    };

    $scope.deleteBook=function () {

        $http({
            method: "POST",
            url: "http://localhost:8080/user/book/delete",
            headers: {
                'Authorization': "Bearer "+$window.sessionStorage['token']
            },
            data: {"title":$scope.dTitle}
        }).then(function mySuccess() {
            $scope.deletedMessage = "deleted" + " " + $scope.dTitle;
        },function myError(response) {
            $scope.deletedMessage = "this book does not exist";
        });
    };

    $scope.logOut=function () {
        $window.sessionStorage.removeItem('token');
        $location.path("/");
    }

});