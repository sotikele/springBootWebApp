app.controller('translationCtrl', function($scope,$http,$window,$translate,ngTableParams,languageID) {

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
               //static value needs data.length
                total: 100,

                getData: function ($defer, params) {
                    $http({
                        method : "GET",
                        url : "http://localhost:8080/keys/"+params.page()+"/"+params.count()
                    }).then(function mySuccess(response) {

                        // $scope.keys = [];
                        // $.each(response.data, function (k, v) {
                        //     var singleObj = {};
                        //     singleObj['KeyName'] = k;
                        //     singleObj['Translation'] = v;
                        //     $scope.keys.push(singleObj);
                        //
                        // });

                        $scope.keys=response.data;
                    // params.page(): Returns the index of the current "slice" of data rows
                    //params.count():  Returns the number of data rows per page
                    $scope.data = $scope.keys.slice((params.page() - 1) * params.count(), params.page() * params.count());

                    $defer.resolve($scope.data);
                    });
                }

            });
















});