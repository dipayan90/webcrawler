/**
 * Created by chattod on 6/11/15.
 *
 * Angularjs Controller for our small view.
 */
angular.module('webCrawler', [])
    .controller('viewController',['$scope','$http',function($scope,$http){
        "use strict";

        $scope.showFeedback = false;

        function constructUrl(url) {
            return "/crawl?url="+ encodeURI(url);
        }

        $scope.submit = function(){
              $scope.requestType = "specific dates request";
              $http.get(constructUrl($scope.url)).then(function(data){
                  alert(data)
                  $scope.showFeedback = true;
                  $scope.feedback = data;
              },function(error){
                  alert(error)
                  $scope.showFeedback = true;
                  $scope.feedback = "Failed to Crawl :(";
              });
        };
    }]);
