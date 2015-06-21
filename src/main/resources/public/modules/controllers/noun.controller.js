/**
 * Controller for handling the general display of nouns (Food, Drug, Medical Device)
 */
function NounController($scope, $stateParams) {
	var that = this;
	that.noun = $stateParams.noun;
	$scope.noun = that.noun;
}

NounController.$inject = ['$scope', '$stateParams'];

angular.module('ctf').controller('NounController', NounController);