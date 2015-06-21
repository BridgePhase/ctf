angular.module('ctf').directive('usMap', ['RegionService', function(RegionService) {
	var forEach = Array.prototype.forEach;
	
	function unhighlightAllStates(element) {
		var states = document.querySelectorAll('#' + element.id + ' .state')
		var statesArray = Array.prototype.slice.call(states);
		statesArray.forEach(function(elem) {
			elem.setAttribute('class', 'state');
		});
	}
	
	function normalizeSelectedStateParam(states) {
		if (states == null) {
			return [];
		}
		if (!(states instanceof Array)) {
			if (states) {
				return [states]
			} else {
				return [];
			}
		} else {
			return states;
		}
	}
	
	function highlightStates(element, states) {
		states = normalizeSelectedStateParam(states);
		for (var i = 0; i < states.length; i++) {
			document.querySelector('#' + element.id + ' #'+ states[i].abbreviation).setAttribute('class', 'state highlighted');
		}
	}
	
	return {
		restrict: 'E',
		templateUrl: 'modules/directives/usmap/usmap.svg',
		scope: {
			enableHover: '=?hover',
			highlightStates: '=?highlightStates',
			onselect: '&onselect'
		},
		link: function(scope, element, attributes) {
			var states = normalizeSelectedStateParam(scope.highlightStates);
			highlightStates(element[0], states);
			
			var elem = element[0];
			if (scope.enableHover) {
				element.addClass('enable-hover');
			}
			
			scope.$on('update-map-' + elem.id, function(event, statesToHighlight) {
				statesToHighlight = normalizeSelectedStateParam(statesToHighlight);
				unhighlightAllStates(elem);
				highlightStates(elem, statesToHighlight);
			});

			elem.onclick = function(event) {
				var target = event.target;
				if (target.className && target.className.baseVal == 'state') {
					var state = RegionService.stateFromAbbreviation(target.id);
					scope.onselect()(state);
					elem.blur();
				}
			}

		
		},
		controller: ['$scope', function($scope) {
			$scope.selectState = function($event) {
				var target = $event.target;
				if (target.className && target.className.baseVal == 'state') {
					var state = RegionService.stateFromAbbreviation(target.id);
					$scope.onselect()(state);
				}
			}
		}]
	}
}]);