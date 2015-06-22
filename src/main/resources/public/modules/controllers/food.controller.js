function FoodController($scope, RegionService, FoodService) {	
	var that = this;

	that.selectedState = null;
	that.selectedStateAbbreviations = null;
	that.affectedStates = null;
	that.recalls = [];
	that.selectedRecall = null;
	
	that.recallMetadata = null;
	
	that.allStates = RegionService.allUsStates();
	RegionService.location().then(function(result) {
		that.userHomeState = result.stateInformation;
		that.selectedState = that.userHomeState;
		$scope.$broadcast('update-map-currentStateMap', that.selectedState);
		that.loadRecallsForCurrentState();
	});
	
	that.loadRecallsForCurrentState = function() {
		that.recalls = [];
		that.recallMetadata = null;
		that.selectedRecall = null;
		that.affectedStates = [];
		FoodService.recallsByState(that.selectedState.abbreviation).then(function(result) {
			that.recallMetadata = result.meta;
			that.recalls = result.results;
		})
	}
	
	that.selectRecall = function(recall) {
		that.selectedRecall = recall;
		var regexp = /[A-Z]{2}/g;
		var match, matches = [];

		while ((match = regexp.exec(recall.distribution_pattern)) != null) {
		  matches.push(match[0]);
		}		
		var highlightStates = [];
		that.allAffectedStates = "";
		for (var i = 0; i < matches.length; i++) {
			var state = RegionService.stateFromAbbreviation(matches[i]);
			if (state != null) {
				highlightStates.push(state);
				if (that.allAffectedStates.length > 0) {
					that.allAffectedStates += ', ';
				}
				that.allAffectedStates += state.name;
			}
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
		that.loadRecallsForCurrentState();
	}
}

FoodController.$inject = ['$scope', 'RegionService', 'FoodService'];

angular.module('ctf').controller('FoodController', FoodController)