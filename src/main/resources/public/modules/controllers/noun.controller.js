/**
 * Controller for handling the general display of nouns (Food, Drug, Medical Device)
 */
function NounController($scope, $stateParams) {
	var that = this;
	that.noun = $stateParams.noun;
	$scope.noun = that.noun;
	
	// we need to provide a more friendly noun for medical devices
	var friendlyNames = {
		'food': 'food recalls',
		'device': 'medical devices',
		'medicine': 'medicines'
	};
	
	$scope.friendlyNoun = friendlyNames[that.noun];
	that.friendlyNoun = friendlyNames[that.noun];
	document.title = 'Consider the following - ' + that.friendlyNoun;
	
	$scope.backToTop = function() {
		window.scroll(0, 0);
	}
}

NounController.$inject = ['$scope', '$stateParams'];

angular.module('ctf').controller('NounController', NounController);