angular.module('ctf').directive('usMap', function() {
	
	function unhighlightAllStates(element) {
		document.querySelector('.state').setAttribute('class', 'state');
	}
	
	function highlightStates(element, states) {
		for (var i = 0; i < states.length; i++) {
			document.querySelector('#' + element.id + ' #'+ states[i]).setAttribute('class', 'state highlighted');
		}
	}
	
	return {
		restrict: 'E',
		templateUrl: 'modules/directives/usmap/usmap.svg',
		scope: {
			highlightStates: '=?highlightStates'
		},
		link: function(scope, element, attributes) {
			var states = scope.highlightStates;
			if (states != null) {
				console.log(element[0]);
				highlightStates(element[0], states);
			}
		}
	}
});