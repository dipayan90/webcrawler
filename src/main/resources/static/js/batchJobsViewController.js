angular.module('webCrawler', [])
    .controller('viewController',['$scope','$http',function($scope,$http){
        "use strict";

        $scope.showFeedback = false;

        function constructUrl(url,category) {
            return encodeURI("/crawl?url="+ url+"&category="+category);
        }

        $scope.submit = function(){
              $scope.requestType = "specific dates request";
              $http.get(constructUrl($scope.url,$scope.category)).then(function(data){
                  $scope.showFeedback = true;
                  $scope.feedback = data.data;
              },function(error){
                  $scope.showFeedback = true;
                  $scope.feedback = "Failed to Crawl :(";
              });
        };
    }]);
