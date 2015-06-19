angular.module('ctf').directive('bigIcon', function() {
	return {
		restrict: 'E',
		transclude: true,
		replace: true,
		template: 
			'<div class="bp-bold-icon">' + 
            '  <a href="#" class="bp-{{color}} fa fa-{{icon}}"></a>' +
            '  <p data-ng-transclude=""></p>' + 
        	'</div>',
		scope: {
			icon: '=icon',
			color: '=color'
		}
	}
});