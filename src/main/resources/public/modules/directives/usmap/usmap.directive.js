angular.module('ctf').directive('usMap', function() {
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
			console.log(states[i]);
			document.querySelector('#' + element.id + ' #'+ states[i].abbreviation).setAttribute('class', 'state highlighted');
		}
	}
	
	return {
		restrict: 'E',
		templateUrl: 'modules/directives/usmap/usmap.svg',
		scope: {
			highlightStates: '=?highlightStates'
		},
		link: function(scope, element, attributes) {
			var states = normalizeSelectedStateParam(scope.highlightStates);
			highlightStates(element[0], states);
			
			var elem = element[0];
			scope.$on('update-map-' + elem.id, function(event, statesToHighlight) {
				statesToHighlight = normalizeSelectedStateParam(statesToHighlight);
				unhighlightAllStates(elem);
				highlightStates(elem, statesToHighlight);
			});
		}
	}
});