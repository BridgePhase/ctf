function FoodController($scope, RegionService) {	
	var that = this;

	that.selectedState = null;
	that.selectedStateAbbreviations = null;
	
	that.allStates = RegionService.allUsStates();
	RegionService.location().then(function(result) {
		that.userHomeState = result.stateInformation;
		that.selectedState = that.userHomeState;
		$scope.$broadcast('update-map-currentStateMap', that.selectedState);
	});
	
	that.stateClicked = function(stateClicked) {
		that.selectedState = stateClicked;
		that.stateSelected();
	}
	
	that.stateSelected = function() {
		$scope.$broadcast('update-map-currentStateMap', that.selectedState);
	}
}

FoodController.$inject = ['$scope', 'RegionService'];

angular.module('ctf').controller('FoodController', FoodController)