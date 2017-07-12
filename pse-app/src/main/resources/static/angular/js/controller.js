(function (ng, undefined){
    'use strict';

var pse = ng.module('pse', []);

pse.controller('SimpleController', ['$scope', function ($scope) {
  $scope.greeting = 'hola';
  $scope.summary = [{
	  code: 'Somecode',
	  name: 'Some name',
	  price: '12.26'
  }];
}])


})(angular);