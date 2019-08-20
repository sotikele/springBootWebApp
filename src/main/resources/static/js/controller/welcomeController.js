

app.controller('welCtrl', function ($scope,$http,$translate) {

    $scope.languages = {
        English: "en",
        Greek: "gr"
    };


    $scope.changeLanguage = function (locale) {
            $translate.use(locale);

   };
});

