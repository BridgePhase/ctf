/**
 * Controller for handling the general display of nouns (Food, Drug, Medical Device)
 */
function NounController($stateParams) {
	var that = this;
	
	that.noun = $stateParams.noun;
}

NounController.$inject = ['$stateParams'];

angular.module('ctf').controller('NounController', NounController);