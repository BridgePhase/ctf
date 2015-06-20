angular.module('ctf').directive('bigIcon', ['$state', function($state) {
	return {
		restrict: 'E',
		transclude: true,
		replace: true,
		template: 
			'<div class="bp-big-icon">' + 
            '  <a data-ng-click="go()" class="bp-{{color}} fa fa-{{icon}}"></a>' +
            '  <p data-ng-transclude=""></p>' + 
        	'</div>',
		scope: {
			icon: '=icon',
			color: '=color',
			state: '=state',
			stateParams: '=stateParams'
		},
		controller: ['$scope', '$state', function($scope, $state) {
			$scope.go = function() {
				$state.go($scope.state, $scope.stateParams);
			}
		}]
	}
}]);