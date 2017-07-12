(function (ng, undefined){
    'use strict';

var pse = ng.module('pse', []);

pse.controller('SimpleController', ['$scope', '$http', function ($scope, $http) {
  $scope.greeting = 'hola';
  $scope.summary = [];

  //var codes = ['WLCON', 'FLI', 'SEVN', 'MBT'];
  var codes = [];
  $http.get('/summary/' + codes.join(',')).then(function (resp) {
	  $scope.summary = resp.data;
  });
}])


})(angular);