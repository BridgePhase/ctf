function FoodController($scope, RegionService, FoodService) {	
	var that = this;

	that.selectedState = null;
	that.selectedStateAbbreviations = null;
	that.affectedStates = null;
	that.recalls = [];
	
	that.allStates = RegionService.allUsStates();
	RegionService.location().then(function(result) {
		that.userHomeState = result.stateInformation;
		that.selectedState = that.userHomeState;
		$scope.$broadcast('update-map-currentStateMap', that.selectedState);
		that.loadRecallsForCurrentState();
	});
	
	that.loadRecallsForCurrentState = function() {
		FoodService.recallsByState(that.selectedState.abbreviation).then(function(result) {
			that.recalls = result.results;
		})
	}
	
	that.selectRecall = function(recall) {
		console.debug('Selecting recall:', recall.city, ' - ', recall.distribution_pattern);
		
		var regexp = /[A-Z]{2}/g;
		var match, matches = [];

		while ((match = regexp.exec(recall.distribution_pattern)) != null) {
		  matches.push(match[0]);
		}		
		console.log('matches:', matches);
		var highlightStates = [];
		for (var i = 0; i < matches.length; i++) {
			highlightStates.push({
				abbreviation: matches[i]
			})
		}

		that.affectedStates = highlightStates;
		$scope.$broadcast('update-map-mymap', that.affectedStates);
	}
	
	that.stateClicked = function(stateClicked) {
		that.selectedState = stateClicked;
		that.stateSelected();
	}
	
	that.stateSelected = function() {
		$scope.$broadcast('update-map-currentStateMap', that.selectedState);
	}
}

FoodController.$inject = ['$scope', 'RegionService', 'FoodService'];

angular.module('ctf').controller('FoodController', FoodController)